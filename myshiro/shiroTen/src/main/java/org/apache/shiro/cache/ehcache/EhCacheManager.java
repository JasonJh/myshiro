package org.apache.shiro.cache.ehcache;

import org.apache.shiro.ShiroException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.util.Destroyable;
import org.apache.shiro.util.Initializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class EhCacheManager implements CacheManager, Initializable, Destroyable {

    private Logger log= LoggerFactory.getLogger(EhCacheManager.class);

    protected net.sf.ehcache.CacheManager manager;

    private boolean cacheManagerImplicitlyCreated=false;

    private String cacheManagerConfigFile="classpath:org/apache/shiro/cache/ehcache/ehcache.xml";

    public EhCacheManager() {
    }

    public net.sf.ehcache.CacheManager getCacheManager() {
        return manager;
    }

    public void setCacheManager(net.sf.ehcache.CacheManager manager) {
        this.manager = manager;
    }

    public String getCacheManagerConfigFile() {
        return this.cacheManagerConfigFile;
    }

    public void setCacheManagerConfigFile(String classpathLocation) {
        this.cacheManagerConfigFile = classpathLocation;
    }


    protected InputStream getCacheManagerConfigFileInputStream() {
        String configFile = getCacheManagerConfigFile();
        try {
            return ResourceUtils.getInputStreamForPath(configFile);
        } catch (IOException e) {
            throw new ConfigurationException("Unable to obtain input stream for cacheManagerConfigFile [" +
                    configFile + "]", e);
        }
    }

    public final  <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if (log.isTraceEnabled()) {
            log.trace("Acquiring EhCache instance named [" + name + "]");
        }

        try {
            net.sf.ehcache.Ehcache cache = ensureCacheManager().getEhcache(name);
            if (cache == null) {
                if (log.isInfoEnabled()) {
                    log.info("Cache with name '{}' does not yet exist.  Creating now.", name);
                }
                this.manager.addCache(name);

                cache = manager.getCache(name);

                if (log.isInfoEnabled()) {
                    log.info("Added EhCache named [" + name + "]");
                }
            } else {
                if (log.isInfoEnabled()) {
                    log.info("Using existing EHCache named [" + cache.getName() + "]");
                }
            }
            return new EhCache<K, V>(cache);
        } catch (net.sf.ehcache.CacheException e) {
            throw new CacheException(e);
        }
    }

    public void destroy() throws Exception {
        if (cacheManagerImplicitlyCreated) {
            try {
                net.sf.ehcache.CacheManager cacheMgr = getCacheManager();
                cacheMgr.shutdown();
            } catch (Exception e) {
                if (log.isWarnEnabled()) {
                    log.warn("Unable to cleanly shutdown implicitly created CacheManager instance.  " +
                            "Ignoring (shutting down)...");
                }
            }
            cacheManagerImplicitlyCreated = false;
        }
    }


    public final void init() throws CacheException {
        ensureCacheManager();
    }

    private net.sf.ehcache.CacheManager ensureCacheManager() {
        try {
            if (this.manager == null) {
                if (log.isDebugEnabled()) {
                    log.debug("cacheManager property not set.  Constructing CacheManager instance... ");
                }

                this.manager = net.sf.ehcache.CacheManager.create(getCacheManagerConfigFileInputStream());
                if (log.isTraceEnabled()) {
                    log.trace("instantiated Ehcache CacheManager instance.");
                }
                cacheManagerImplicitlyCreated = true;
                if (log.isDebugEnabled()) {
                    log.debug("implicit cacheManager created successfully.");
                }
            }
            return this.manager;
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }
}
