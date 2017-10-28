package com.newhome.util.redis;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

/**
 * Jedis连接池配置
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/10/22
 */
public class JedisPoolUtil extends JedisPool {

    public JedisPoolUtil(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
                         final String password) {
        super(poolConfig, host, port, timeout, StringUtils.isNotEmpty(password) ? password : null,
                Protocol.DEFAULT_DATABASE, null);
    }

    public JedisPoolUtil(final GenericObjectPoolConfig poolConfig, final String host, int port, int timeout,
                         final String password, final int database) {
        super(poolConfig, host, port, timeout, StringUtils.isNotEmpty(password) ? password : null, database, null);
    }

}
