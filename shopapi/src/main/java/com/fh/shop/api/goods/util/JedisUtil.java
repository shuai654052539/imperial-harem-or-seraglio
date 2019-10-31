package com.fh.shop.api.goods.util;

import redis.clients.jedis.Jedis;

public class JedisUtil {

    public static void set(String key,String value){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getJedis();
            jedis.set(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    public static String get(String key){
        Jedis jedis =null;
        String value =null;
        try {
            jedis = RedisPool.getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return value;
    }

    public static Long del(String key){
        Jedis jedis = null;
        Long delcount = 0L;
        try {
            jedis = RedisPool.getJedis();
            delcount = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return delcount;
    }

    public static void setex(String key, int seconds, String value){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getJedis();
            jedis.setex(key,seconds,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
    //
    public static boolean exists(String key){
        Jedis jedis =null;
        Boolean exists = false;
         try {
            jedis = RedisPool.getJedis();
            exists = jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return exists;
    }

    public static String hget(String key,String field){
        Jedis jedis =null;
        String value= null;
        try {
            jedis = RedisPool.getJedis();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }
        return value;
    }

    public static void hset(String key,String field,String value){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getJedis();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    public static void hdel(String key,String field){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getJedis();
            jedis.hdel(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
}
