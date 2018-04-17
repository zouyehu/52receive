package com.winnerlook.framework.redis.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.winnerlook.framework.redis.dao.RedisBaseDao;

/**
 * redis 实现层
 *
 * @author Hu
 */
public class RedisBaseDaoImpl implements RedisBaseDao {
    @SuppressWarnings("rawtypes")
    private RedisTemplate redisTemplate;

    @SuppressWarnings("rawtypes")
    private StringRedisTemplate stringRedisTemplate;

    /** ===============Redis-String数据结构接口START=============== */
    /**
     * 设置字符串
     *
     * @param key   key
     * @param value 字符串值
     */
    @SuppressWarnings("unchecked")
    @Override
    public void set(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setStr(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    /**
     * 设置字符串(含过期时间)
     *
     * @param key     key
     * @param value   字符串值
     * @param timeout 过期时间，单位：秒
     */
    @SuppressWarnings("unchecked")
    @Override
    public void set(String key, String value, long timeout) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, timeout, TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean setIfAbsent(String key, String value, long timeout) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        boolean isAbsent = valueOperations.setIfAbsent(key, value);
        if (isAbsent) {
            this.expire(key, timeout);
        }
        return isAbsent;
    }

    /**
     * 设置多个字符串
     *
     * @param paramMap 字符串Map
     */
    @SuppressWarnings("unchecked")
    @Override
    public void mset(Map<String, String> paramMap) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.multiSet(paramMap);
    }

    /**
     * 获取字符串
     *
     * @param key key
     * @return String        key对应的字符串值
     */
    @SuppressWarnings("unchecked")
    @Override
    public String get(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }


    /**
     * 获取字符串
     *
     * @param key key
     * @return String        key对应的字符串值
     */
    @SuppressWarnings("unchecked")
    @Override
    public String getStr(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 根据key列表获取字符串列表
     *
     * @param keys key列表
     * @return List<String>		key列表对应的字符串列表
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> mget(List<String> keys) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.multiGet(keys);
    }

    /**
     * 设置对象(采用Redis的String存储)
     *
     * @param key key
     * @param T   对象
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> void setObject(String key, T T) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, T);
    }

    /**
     * 设置对象(采用Redis的String存储，含过期时间)
     *
     * @param key     key
     * @param T       对象
     * @param timeout 过期时间，单位：秒
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> void setObject(String key, T T, long timeout) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, T, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置多key的同一类型对象(采用Redis的String存储)
     *
     * @param paramMap 参数Map
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> void msetObject(Map<String, T> paramMap) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        valueOperations.multiSet(paramMap);
    }

    /**
     * 根据key获取对象(对象采用Redis的String存储)
     *
     * @param key key
     * @return T        对象
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> T getObject(String key) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * 根据key列表获取同一对象列表(列表中的对象采用Redis的String存储)
     *
     * @param keys keys列表
     * @return List<T>		对象列表
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> List<T> mgetObject(List<String> keys) {
        ValueOperations<String, T> valueOperations = redisTemplate.opsForValue();
        return valueOperations.multiGet(keys);
    }
    /** ===============Redis-String数据结构接口END=============== */

    /** ===============Redis-HASH数据结构接口START=============== */
    /**
     * Hash设置,可用于保存对象或者保存对象的单个field
     *
     * @param key   Hash表的key
     * @param field Hash表中的域field
     * @param T     对象
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> void hset(String key, String field, T T) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.put(key, field, T);
    }

    /**
     * Hash批量设置,可用于保存多个对象或者保存单个对象的多个field
     *
     * @param key      Hash表的key
     * @param paramMap Hash表的field和Value组成的Map
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> void hmset(String key, Map<String, T> paramMap) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, paramMap);
    }

    /**
     * 根据Hash表的key和域Field获取对应的Value(可用于获取对象或者获取对象的单个field)
     * @param key   Hash表的key
     * @param field Hash表中的域field
     * @return T                Hash的key和域Field获取对应的Value
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> T hget(String key, String field) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(key, field);
    }

    /**
     * 根据Hash表的key和域Field列表获取对应的Value列表(可用于获取多个对象或者对象的多个属性)
     *
     * @param key    Hash表的key
     * @param fields 域Field列表
     * @return List<T>		Hash的key和域Field列表获取对应的Value列表
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> List<T> hmget(String key, List<String> fields) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.multiGet(key, fields);
    }

    /**
     * 根据Hash表的key获取对应的所有对象(可用于获取多个对象)
     *
     * @param key Hash表的key
     * @return Map<String, T>		Hash表的key对应的所有对象Map
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> Map<String, T> hgetAll(String key) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        return hashOperations.entries(key);
    }

    /**
     * 根据Hash表的key和域Field进行删除
     *
     * @param <T>
     * @param key    Hash表的key
     * @param fields Hash表对应域Field数组
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> void hDel(String key, Object... fields) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        hashOperations.delete(key, fields);
    }
    /** ===============Redis-HASH数据结构接口END=============== */

    /** ===============Redis接口START=============== */
    /**
     * 设置key在多少秒后过期
     *
     * @param key     key
     * @param timeout 过期时间
     * @return boolean        是否设置成功
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean expire(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取自增索引
     *
     * @param key
     * @param count (0：索引值置0， 1：递增1)
     */
    @Override
    public Integer incrementAndGet(String key, int count) {
        if (StringUtils.isNotEmpty(key)) {
            RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, stringRedisTemplate.getConnectionFactory());
            if (count == 0) {
                return Integer.parseInt(String.valueOf(entityIdCounter.getAndSet((long) count)));
            } else {
                return Integer.parseInt(String.valueOf(entityIdCounter.addAndGet((long) count)));
            }
        } else {
            return 0;
        }
    }

    /**
     * 设置key在固定的某个时刻后过期
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 根据key删除单个对象
     */
    @SuppressWarnings("unchecked")
    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 根据key列表删除多个对象
     */
    @SuppressWarnings("unchecked")
    @Override
    public void deleteAll(List<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 模糊查找redis中的key，使用通配符，如*
     */
    @SuppressWarnings("unchecked")
    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * ===============Redis接口END===============
     */

    @SuppressWarnings("rawtypes")
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }


    @SuppressWarnings("rawtypes")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * @说明：队列消息 发送
     */
    public void sendMsg(String channel, Object message) {
        //	redisTemplate.opsForList().leftPush(channel, message);
        stringRedisTemplate.opsForList().rightPush(channel, JSON.toJSONString(message));
    }

    /**
     * @说明：接收队列消息
     */
    @Override
    public Object getMsg(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * @说明：获取队列缓冲值
     */
    @Override
    public Long getMQLength(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /**
     * @说明：redis发布订阅
     */
    @Override
    public void publish(String channel, Object obj) {
        stringRedisTemplate.convertAndSend(channel, obj);
    }
}
