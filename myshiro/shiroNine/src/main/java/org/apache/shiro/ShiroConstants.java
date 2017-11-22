package org.apache.shiro;

public interface ShiroConstants {
    /**
     * 当前会话状态
     */
    String ONLINE_SESSION="online_session";

    /**
     * 仅清空本地缓存 不情况数据库的
     */
    String ONLY_CLEAR_CACHE = "online_session_only_clear_cache";
}
