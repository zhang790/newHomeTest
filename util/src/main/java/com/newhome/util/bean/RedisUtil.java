package com.newhome.util.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * redis工具
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/10/21
 */
public class RedisUtil {

    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 连接池工具
     */
    private JedisPool jedisPool;

    /**
     * 默认数据库0
     */
    private static final int DEFAULT_DATABASE = RedisDataBaseEnum.DATABASE_0.getId();

    /**
     * 获取redis连接
     */
    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     * 插入数据到HASH表中.
     *
     * @param tableName
     *            hash表名.
     * @param key
     *            字段名.
     * @param object
     *            存入的对象.
     */
    public boolean hput(String tableName, String key, Object object) {

        return hput(tableName, key, object, DEFAULT_DATABASE) ;
    }

    /**
     * 插入数据到HASH表中.
     *
     * @param tableName
     *            hash表名.
     * @param key
     *            字段名.
     * @param object
     *            存入的对象.
     * @param dataBase 数据库标示.
     */
    public boolean hput(String tableName, String key, Object object, int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            jedis.hset(tableName.getBytes(), key.getBytes(),
                    SerializeUtil.serialize(object));
        } catch (Exception exception) {
            logger.error(tableName + "哈希表put操作产生异常：" , exception);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

    /**
     * 获取HASH表中指定key的数据.
     *
     * @param tableName
     *            HASH表名.
     * @param key
     *            要查看的字段.
     */

    public Object hget(String tableName, String key) {
        return hget(tableName, key , DEFAULT_DATABASE) ;
    }

    /**
     * 获取HASH表中指定key的数据.
     *
     * @param tableName
     *            HASH表名.
     * @param key
     *            要查看的字段.
     * @param dataBase 数据库标示.
     */
    public Object hget(String tableName, String key , int dataBase) {
        Jedis jedis = null;
        Object obj = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            obj = SerializeUtil.deserialize(jedis.hget(tableName.getBytes(),
                    key.getBytes()));
        } catch (Exception exception) {
            logger.error(tableName + "哈希表get操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return obj;
    }



    public void hdel(String tableName, String key) {
        hdel(tableName, key , DEFAULT_DATABASE) ;
    }

    /**
     * 删除HASH表中指定key的数据.
     *
     * @param tableName
     *            HASH表名.
     * @param key
     *            要删除的字段.
     * @param dataBase 数据库标示.
     */
    public void hdel(String tableName, String key , int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            jedis.hdel(tableName.getBytes(), key.getBytes());
        } catch (Exception exception) {
            logger.error(tableName + "哈希表del操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    /**
     * 根据关键词得到Hash的内容.
     * @param tableName
     *            hash表名.
     */
    public Map<String, Object> hgetAll(String tableName) {
        return hgetAll(tableName, DEFAULT_DATABASE) ;
    }


    /**
     * 根据关键词得到Hash的内容.
     * @param tableName
     *            hash表名.
     *  @param dataBase 数据库标示.
     */
    public Map<String, Object> hgetAll(String tableName, int dataBase) {
        Jedis jedis = null;
        Map<String, Object> map = new Hashtable<String, Object>();
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            Map<byte[], byte[]> map1 = jedis.hgetAll(tableName.getBytes());
            for (Map.Entry<byte[], byte[]> entry : map1.entrySet()) {
                map.put(new String(entry.getKey()),
                        SerializeUtil.deserialize(entry.getValue()));
            }
        } catch (Exception exception) {
            logger.error(tableName + "哈希表getAll操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return map;
    }


    /**
     * 通过HASH表名获取数量.
     *
     * @param tableName
     *            hash表名.
     */
    public Long getHashCount(String tableName) {
        return getHashCount(tableName , DEFAULT_DATABASE);
    }

    /**
     * 通过HASH表名获取数量.
     *
     * @param tableName
     *            hash表名.
     * @param dataBase 数据库标示.
     */
    public Long getHashCount(String tableName, int dataBase) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            result = jedis.hlen(tableName);
        } catch (Exception exception) {
            logger.error(tableName + "哈希表len操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 获取制定队列名称的数量.
     *
     * @param tableName
     *            队列名称.
     * @return 队列数据数量.
     */
    public long llen(String tableName) {
        return llen(tableName, DEFAULT_DATABASE) ;
    }

    /**
     * 获取制定队列名称的数量.
     *
     * @param tableName
     *            队列名称.
     * @param dataBase 数据库标示.
     * @return 队列数据数量.
     */
    public long llen(String tableName, int dataBase) {
        Jedis jedis = null;
        long re = 0L;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            re = jedis.llen(tableName.getBytes());
        } catch (Exception exception) {
            logger.error(tableName + "队列len操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return re;
    }

    /**
     * 存入队列.
     *
     * @param tableName
     *            list名称.
     * @param object
     *            存入的对象.
     */
    public boolean lput(String tableName, Object object) {

        return lput(tableName, object , DEFAULT_DATABASE);
    }

    /**
     * 存入队列.
     *
     * @param tableName
     *            list名称.
     * @param object
     *            存入的对象.
     * @param dataBase 数据库标示.
     */
    public boolean lput(String tableName, Object object , int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            jedis.lpush(tableName.getBytes(), SerializeUtil.serialize(object));
        } catch (Exception exception) {
            logger.error(tableName + "队列put的操作产生异常：" , exception);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

    /**
     * 根据关键词key得到对象.
     *
     * @param key
     *            要查询的关键词.
     * @return 对象结果.
     */
    public Object get(String key) {

        return get(key, DEFAULT_DATABASE);
    }

    /**
     * 根据关键词key得到对象.
     *
     * @param key
     *            要查询的关键词.
     * @param dataBase 数据库标示
     * @return 对象结果.
     */
    public Object get(String key, int dataBase) {
        Jedis jedis = null;
        Object obj = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            obj = SerializeUtil.deserialize(jedis.get(key.getBytes()));
        } catch (Exception exception) {
            logger.error("根据" + key + "获取对象的操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return obj;
    }

    /**
     * 根据关键词key得到字串.
     *
     * @param key
     *            要查询的关键词.
     * @return 对象结果.
     * @Edit_Description:
     * @Create_Version:shebao-framelib 1.0
     */
    public String getString(String key) {
        return getString(key , DEFAULT_DATABASE);
    }

    /**
     * 根据关键词key得到字串.
     *
     * @param key
     *            要查询的关键词.
     * @return 对象结果.
     * @Edit_Description:
     * @Create_Version:shebao-framelib 1.0
     */
    public String getString(String key, int dataBase) {
        Jedis jedis = null;
        String obj = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            obj = jedis.get(key);
        } catch (Exception exception) {
            logger.error("根据" + key + "获取对象的操作产生异常：", exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return obj;
    }


    /**
     * 插入数据.
     * @param key
     *            关键词key.
     * @param object
     *            插入的对象.
     */
    public boolean set(String key, Object object) {
        return set(key, object, 0, DEFAULT_DATABASE);
    }

    /**
     * 插入数据.
     * @param key
     *            关键词key.
     * @param object
     *            插入的对象.
     * @param expire
     *            过期时间 零或负数:不设置过期时间(单位：秒).
     * @param dataBase 数据库标示
     */
    public boolean set(String key, Object object, int expire, int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            jedis.set(key.getBytes(), SerializeUtil.serialize(object));
            if (expire > 0) {
                jedis.expire(key.getBytes(), expire);
            }
        } catch (Exception exception) {
            logger.error("插入key为" + key + "的操作产生异常：" , exception);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }


    /**
     * 插入数据.
     * @param key
     *            关键词key.
     * @param object
     *            插入的对象.
     * @param expire
     *            过期时间 零或负数:不设置过期时间(单位：秒).
     */

    public boolean set(String key, Object object, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(DEFAULT_DATABASE);
            jedis.set(key.getBytes(), SerializeUtil.serialize(object));
            if (expire > 0) {
                jedis.expire(key.getBytes(), expire);
            }
        } catch (Exception exception) {
            logger.error("插入key为" + key + "的操作产生异常：" , exception);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

    /**
     * 插入数据.
     * @param key
     *            关键词key.
     * @param str
     *            插入的对象.
     * @param dataBase 数据库标示
     *
     */

    public void setStringByDataBase(String key, String str, int dataBase) {
        setString(key, str, 0, dataBase) ;
    }

    /**
     * 插入数据.
     * @param key
     *            关键词key.
     * @param str
     *            插入的对象.
     */
    public boolean setString(String key, String str) {
        return setString(key, str, 0, DEFAULT_DATABASE);
    }

    /**
     * 插入数据.
     * @param key
     *            关键词key.
     * @param str
     *            插入的对象.
     * @param expire
     *            过期时间 零或负数:不设置过期时间(单位：秒).
     */

    public boolean setString(String key, String str, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(DEFAULT_DATABASE);
            jedis.set(key, str);
            if (expire > 0) {
                jedis.expire(key, expire);
            }
        } catch (Exception exception) {
            logger.error("插入key为" + key + "的操作产生异常：" , exception);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

    /**
     * 插入数据.
     * @param key 关键词key
     * @param str 插入的对象.
     * @param expire 过期时间 零或负数:不设置过期时间(单位：秒).
     * @param dataBase 数据库标示
     * @return 插入成功与否
     * @Edit_Description:
     * @Create_Version:shebao-framelib 1.0
     */
    public boolean setString(String key, String str, int expire, int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            jedis.set(key, str);
            if (expire > 0) {
                jedis.expire(key.getBytes(), expire);
            }
        } catch (Exception exception) {
            logger.error("插入key为" + key + "的操作产生异常：" , exception);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }


    /**
     * 插入数据.
     * @param key
     *            关键词key.
     * @param object
     *            插入的对象.
     * @param dataBase 数据库标示
     *
     */

    public void setByDataBase(String key, Object object, int dataBase) {
        set(key, object, 0, dataBase) ;
    }




    /**
     * 获得满足正则pre的所有redis键.
     * @param pre
     *            要匹配的正则.
     */

    public Set<String> keys(String pre) {
        return keys(pre , DEFAULT_DATABASE);
    }

    /**
     * 获得满足正则pre的所有redis键.
     * @param pre
     *            要匹配的正则.
     * @param dataBase 数据库标示
     */

    public Set<String> keys(String pre, int dataBase) {
        Jedis jedis = null;
        Set<String> re = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            re = jedis.keys(pre);
        } catch (Exception exception) {
            logger.error("获取正则符合" + pre + "的所有redis键操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return re;
    }


    /**
     * 删除缓存.
     * @param key
     *            关键词key.
     */
    public boolean delete(String key) {
        return delete(key, DEFAULT_DATABASE);
    }

    /**
     * 删除缓存.
     * @param key
     *            关键词key.
     * @param dataBase 数据库标示
     */
    public boolean delete(String key , int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            jedis.del(key.getBytes());
        } catch (Exception exception) {
            logger.error("删除key为" + key + "的操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }

    /**
     * 键值自增长.
     *
     * @param key
     *            返回增长后的值.
     * */
    public Long incr(String key) {

        return incr(key, DEFAULT_DATABASE);
    }

    /**
     * 键值自增长.
     *
     * @param key
     *            返回增长后的值.
     * @param dataBase 数据库标示
     * */
    public Long incr(String key , int dataBase) {
        Jedis jedis = null;
        Long incr = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            incr = jedis.incr(key.getBytes());
        } catch (Exception exception) {
            logger.error("执行key为" + key + "的自增长操作产生异常：" , exception);
            return new Long(0);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return incr;
    }

    /**
     * 设置key的生存周期.
     *
     * @param key
     *            key名称.
     * @param expire
     *            生存周期，以秒为单位.
     * @return 成功返回1，失败放回0.
     */
    public Long setKeyExpireTime(String key, int expire) {
        return setKeyExpireTime(key, expire, DEFAULT_DATABASE);
    }

    /**
     * 设置key的生存周期.
     *
     * @param key
     *            key名称.
     * @param expire
     *            生存周期，以秒为单位.
     * @param dataBase 数据库标示
     * @return 成功返回1，失败放回0.
     */
    public Long setKeyExpireTime(String key, int expire, int dataBase) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            result = jedis.expire(key, expire);
        } catch (Exception exception) {
            logger.error("设置key为" + key + "的expire操作产生异常：" , exception);
            return new Long(0);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 获取key的还有多久被删除.
     *
     * @param key
     *            key名称.
     * @return 成功返回生存时间，失败放回0.
     */
    public Long getKeyExpireTime(String key) {
        return getKeyExpireTime(key, DEFAULT_DATABASE);
    }

    /**
     * 获取key的还有多久被删除.
     *
     * @param key
     *            key名称.
     * @param dataBase 数据库标示
     * @return 成功返回生存时间，失败放回0.
     */
    public Long getKeyExpireTime(String key, int dataBase) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            result = jedis.ttl(key);
        } catch (Exception exception) {
            logger.error("获取key为" + key + "的操作产生异常：" , exception);
            return new Long(0);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * hash计数器.
     *
     * @param tableName
     *            hash表名.
     * @param key
     *            存储的key.
     * @param number
     *            增长数.
     */
    public void hashcounter(String tableName, String key, int number) {
        hashcounter(tableName, key, number, number);
    }


    /**
     * hash计数器.
     *
     * @param tableName
     *            hash表名.
     * @param key
     *            存储的key.
     * @param number
     *            增长数.
     * @param dataBase 数据库标示.
     *
     */
    public void hashcounter(String tableName, String key, int number, int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            jedis.hincrBy(tableName.getBytes(), key.getBytes(), number);
        } catch (Exception exception) {
            logger.error(tableName + "哈希表计数操作产生异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 释放同步锁.
     *
     * @param key
     *            键.
     */
    public void delLock(String key) {
        delete(key, DEFAULT_DATABASE);
    }

    /**
     * 释放同步锁.
     * @param key 键.
     * @param dataBase 数据库标示.
     */
    public void delLock(String key, int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            long oldtime = Long.parseLong(jedis.get(key));
            long current = new Date().getTime();
            if (current < oldtime) {
                jedis.del(key);
            }
        } catch (Exception exception) {
            logger.error( "key:" + key + " 释放同步锁异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获得同步锁.
     * @param key 键.
     * @return 超时时间.
     */
    public int getLock(String key, long timeout) {

        return getLock(key, timeout, DEFAULT_DATABASE);
    }

    /**
     * 获得同步锁.
     * @param key 键.
     * @param dataBase 数据库标示.
     * @return 超时时间.
     */
    public int getLock(String key, long timeout , int dataBase) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            long now = new Date().getTime();
            long timestamp = now + timeout;
            long lock = jedis.setnx(key, String.valueOf(timestamp));
            if (lock == 1
                    || (now > Long.parseLong(jedis.get(key)) && now > Long
                    .parseLong(jedis.getSet(key,
                            String.valueOf(timestamp))))) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            logger.error( "key:" + key + " 获得同步锁异常：" , exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }

    /**
     * 获取HASH表中所有key名称.
     *
     * @param tableName
     *            HASH表名.
     */
    public Set<String> hkeys(String tableName) {

        return hkeys(tableName, DEFAULT_DATABASE);
    }

    /**
     * 获取HASH表中所有key名称.
     *
     * @param tableName
     *            HASH表名.
     * @param dataBase 数据库标示.
     */
    public Set<String> hkeys(String tableName , int dataBase) {
        Jedis jedis = null;
        Set<String> keys = new HashSet<String>();
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            Set<byte[]> keysbytes = jedis.hkeys(tableName.getBytes());
            for (byte[] b : keysbytes) {
                if (b.length != 0) {
                    keys.add(new String(b));
                }
            }
        } catch (Exception exception) {
            logger.error(tableName + "哈希表del操作产生异常", exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return keys;
    }

    /**
     * 判断key是否存在.
     *
     * @param key
     *            key关键字.
     * @return 存在返回true, 不存在返回false.
     */
    public boolean exists(String key) {
        return exists(key, DEFAULT_DATABASE);
    }

    /**
     * 判断key是否存在.
     *
     * @param key
     *            key关键字.
     * @param dataBase 数据库标示.
     * @return 存在返回true, 不存在返回false.
     */
    public boolean exists(String key , int dataBase) {
        Jedis jedis = null;
        boolean flag = false;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            flag = jedis.exists(key);
        } catch (Exception exception) {
            logger.error("判断key是否存在操作产生异常", exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return flag;
    }

    /**
     * 将 key 所储存的值原子性的加上增量 increment 。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行
     * INCRBY 命令。 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误.
     * @return : Long
     */
    public Long incrBy(String key, long integer) {
        return incrBy(key, integer, DEFAULT_DATABASE);
    }

    /**
     * 将 key 所储存的值原子性的加上增量 increment 。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行
     * INCRBY 命令。 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误.
     * @return : Long
     */
    public Long incrBy(String key, long integer , int dataBase) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = getJedis();
            jedis.select(dataBase);
            result = jedis.incrBy(key, integer);
            return result;
        } catch (Exception exception) {
            logger.error("类型错误", exception);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

}
