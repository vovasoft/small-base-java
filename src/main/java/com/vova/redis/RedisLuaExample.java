package com.vova.redis;

/**
 * @author WangYang - vova
 * @version Create in 14:25 2024/2/29
 * 使用 Lua 脚本可以保证在 Redis 中执行的操作是原子化的。当你在 Redis 中执行一个 Lua 脚本时，这个脚本会作为一个单独的执行单元被处理，
 * 脚本中的所有命令都会在脚本执行期间连续执行，期间不会有其他 Redis 命令被插入执行。这意味着，Lua 脚本提供了一种方式来执行复杂的逻辑，同时保证了数据的一致性和操作的原子性。
 *
 * 原子性是指脚本中的操作要么全部执行，要么全部不执行，不会出现只执行了部分命令的情况。这对于需要在多个步骤中更新数据且中间不允许其他命令影响这些步骤的情况非常有用。
 *
 * 例如，如果你需要读取一个键的值，基于这个值做一些计算，然后更新这个键和其他相关键，使用 Lua 脚本可以确保这些操作作为一个整体原子地执行。
 */

import redis.clients.jedis.Jedis;

public class RedisLuaExample {
    public static void main(String[] args) {
        // 创建 Jedis 实例以连接到 Redis
        Jedis jedis = new Jedis("localhost", 6379); // 假设 Redis 服务器运行在本地，默认端口

        // Lua 脚本，以字符串形式嵌入
        String luaScript =
                "local exists = redis.call('EXISTS', KEYS[1])\n" +
                        "local prevValue = nil\n" +
                        "local counter = 0\n" +
                        "if exists == 0 then\n" +
                        "    redis.call('SET', KEYS[1], ARGV[1])\n" +
                        "    redis.call('SET', KEYS[2], 1)\n" +
                        "    prevValue = nil\n" +
                        "    counter = 1\n" +
                        "else\n" +
                        "    prevValue = redis.call('GET', KEYS[1])\n" +
                        "    redis.call('SET', KEYS[1], ARGV[1])\n" +
                        "    counter = redis.call('INCR', KEYS[2])\n" +
                        "end\n" +
                        "return {prevValue, counter}";

        // 执行 Lua 脚本
        // 这里我们试图设置键 "myKey" 的值为 "myValue"
        // KEYS[1] 对应 "myKey", ARGV[1] 对应 "myValue"
        Object result = jedis.eval(luaScript, 2, "vova1", "myCounter", "newValue");

        System.out.println(result);

        jedis.close();
    }
}
