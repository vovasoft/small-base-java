[mysqld]

# Basic Settings
user=mysql
pid-file=/var/run/mysqld/mysqld.pid
socket=/var/run/mysqld/mysqld.sock
port=3306
basedir=/usr
datadir=/var/lib/mysql
tmpdir=/tmp
lc-messages-dir=/usr/share/mysql
explicit_defaults_for_timestamp

# Security Settings
skip-name-resolve
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES

# InnoDB Settings
default-storage-engine=innodb
innodb_buffer_pool_size=4G # 50-70% of system RAM
innodb_log_file_size=512M
innodb_file_per_table=1
innodb_flush_log_at_trx_commit=1

# Connection Settings
# 最大连接数。这个设置决定了MySQL服务器可以同时处理的最大客户端连接数。150是一个相对保守的设置，适用于中小型应用，可以根据服务器的CPU和内存资源适当调整。
max_connections=150
# 连接超时时间（秒）。如果一个连接在这段时间内没有成功建立，则服务器将关闭这个连接。设置为5秒是为了防止网络问题或恶意连接占用服务器资源。
connect_timeout=5
# 等待超时时间（秒）。如果一个连接在这段时间内没有活动，则服务器将关闭这个连接。600秒（即10分钟）是一个合理的设置，以释放长时间空闲的连接资源。
wait_timeout=600
# 允许的最大数据包大小。这个设置影响你可以发送给MySQL服务器的查询或者服务器可以发送给你的数据包的最大大小。16MB是一个适中的大小，适用于大多数应用。
max_allowed_packet=16M
# 线程缓存大小。这个参数决定了可以缓存的线程数，有助于减少为新连接创建线程的开销。128是一个适中的值，可以根据你的并发连接数进行调整。
thread_cache_size=128
# 排序缓冲区大小。每个线程进行排序操作时可用的内存缓冲区大小。4M为每个需要进行文件排序的操作分配了足够的内存，而不至于消耗过多内存。
sort_buffer_size=4M
# 批量插入缓冲区大小。当进行批量插入操作时，这个缓冲区用于优化插入速度。16M对于大多数批量插入操作来说是足够的，并且不会过度占用内存资源。
bulk_insert_buffer_size=16M
# 临时表大小。MySQL使用内存中的临时表来处理复杂的查询，如排序和子查询。32M设置为这些操作提供足够的内存空间，同时限制每个临时表的最大内存使用量。
tmp_table_size=32M
# 最大堆表大小。这个设置控制了MEMORY存储引擎表可以使用的最大内存量。32M限制了单个内存表的大小，有助于防止单个表消耗过多内存资源。
max_heap_table_size=32M


# Logging
log_error=/var/log/mysql/mysql-error.log
slow_query_log=1
slow_query_log_file=/var/log/mysql/mysql-slow.log
long_query_time=2
