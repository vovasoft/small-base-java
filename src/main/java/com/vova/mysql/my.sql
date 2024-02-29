-- 修改最大连接数（全局生效）
SET GLOBAL max_connections = 200;

-- 修改连接超时时间（全局生效）
SET GLOBAL connect_timeout = 10;

-- 修改等待超时时间（全局生效）
SET GLOBAL wait_timeout = 1200;

-- 修改最大允许的数据包大小（全局生效）
SET GLOBAL max_allowed_packet = 32 * 1024 * 1024; -- 32MB

-- 修改线程缓存大小（全局生效）
SET GLOBAL thread_cache_size = 100;

-- 注意：不是所有参数都可以像上述那样动态更改。有些参数更改需要重启MySQL服务才能生效。
