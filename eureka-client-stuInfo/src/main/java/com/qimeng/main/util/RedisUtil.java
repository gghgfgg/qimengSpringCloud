package com.qimeng.main.util;

import java.util.List;  
import java.util.Map;  
import java.util.Set;  
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.qimeng.main.entity.SchoolRecycleCount;
import com.qimeng.main.entity.StudentData;  
  
  
/** 
 *  
 * @author QLQ
 * 基于spring和redis的redisTemplate工具类 
 * 针对所有的hash 都是以h开头的方法 
 * 针对所有的Set 都是以s开头的方法                    不含通用方法 
 * 针对所有的List 都是以l开头的方法 
 */  
@Component//交给Spring管理(在需要缓存的地方自动注入即可使用)
public class RedisUtil {  
  
    @Autowired//(自动注入redisTemplet)
    private RedisTemplate<String, Object> redisTemplate;  
      
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
    //=============================common============================  
    /** 
     * 指定缓存失效时间 
     * @param key 键 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean expire(String key,long time){  
        try {  
            if(time>0){  
                redisTemplate.expire(key, time, TimeUnit.SECONDS);  
            }  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 根据key 获取过期时间 
     * @param key 键 不能为null 
     * @return 时间(秒) 返回0代表为永久有效 
     */  
    public long getExpire(String key){  
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);  
    }  
      
    /** 
     * 判断key是否存在 
     * @param key 键 
     * @return true 存在 false不存在 
     */  
    public boolean hasKey(String key){  
        try {  
            return redisTemplate.hasKey(key);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 删除缓存 
     * @param key 可以传一个值 或多个 
     */  
    @SuppressWarnings("unchecked")  
    public void del(String ... key){  
        if(key!=null&&key.length>0){  
            if(key.length==1){  
                redisTemplate.delete(key[0]);  
            }else{  
                redisTemplate.delete(CollectionUtils.arrayToList(key));  
            }  
        }  
    }  
      
    //============================String=============================  
    /** 
     * 普通缓存获取 
     * @param key 键 
     * @return 值 
     */  
    public Object get(String key){  
        return key==null?null:redisTemplate.opsForValue().get(key);  
    }  
      
    /** 
     * 普通缓存放入 
     * @param key 键 
     * @param value 值 
     * @return true成功 false失败 
     */  
    public boolean set(String key,Object value) {  
         try {  
            redisTemplate.opsForValue().set(key,  value);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
          
    }  
      
    /** 
     * 普通缓存放入并设置时间 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期 
     * @return true成功 false 失败 
     */  
    public boolean set(String key,Object value,long time){  
        try {  
            if(time>0){  
                redisTemplate.opsForValue().set(key,  value, time, TimeUnit.SECONDS);  
            }else{  
                set(key, value);  
            }  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  

   public boolean sethours(String key,Object value,long time){  
       try {  
           if(time>0){  
               redisTemplate.opsForValue().set(key,  value, time, TimeUnit.HOURS);  
           }else{  
               set(key, value);  
           }  
           return true;  
       } catch (Exception e) {  
           e.printStackTrace();  
           return false;  
       }  
   }  
    /** 
     * 递增 
     * @param key 键 
     * @param by 要增加几(大于0) 
     * @return 
     */  
    public long incr(String key, long delta){    
        if(delta<0){  
            throw new RuntimeException("递增因子必须大于0");  
        }  
        return redisTemplate.opsForValue().increment(key, delta);  
    }  
      
    /** 
     * 递减 
     * @param key 键 
     * @param by 要减少几(小于0) 
     * @return 
     */  
    public long decr(String key, long delta){    
        if(delta<0){  
            throw new RuntimeException("递减因子必须大于0");  
        }  
        return redisTemplate.opsForValue().increment(key, -delta);    
    }    
      
    //================================Map=================================  
    /** 
     * HashGet 
     * @param key 键 不能为null 
     * @param item 项 不能为null 
     * @return 值 
     */  
    public Object hget(String key,String item){  
        return redisTemplate.opsForHash().get(key, item);  
    }  
      
    /** 
     * 获取hashKey对应的所有键值 
     * @param key 键 
     * @return 对应的多个键值 
     */  
    public Map<Object,Object> hmget(String key){  
        return redisTemplate.opsForHash().entries(key);  
    }  
      
    /** 
     * HashSet 
     * @param key 键 
     * @param map 对应多个键值 
     * @return true 成功 false 失败 
     */  
    public boolean hmset(String key, Map<String,Object> map){    
        try {  
            redisTemplate.opsForHash().putAll(key, map);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * HashSet 并设置时间 
     * @param key 键 
     * @param map 对应多个键值 
     * @param time 时间(秒) 
     * @return true成功 false失败 
     */  
    public boolean hmset(String key, Map<String,Object> map, long time){    
        try {  
            redisTemplate.opsForHash().putAll(key, map);  
            if(time>0){  
                expire(key, time);  
            }  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 向一张hash表中放入数据,如果不存在将创建 
     * @param key 键 
     * @param item 项 
     * @param value 值 
     * @return true 成功 false失败 
     */  
    public boolean hset(String key,String item,Object value) {  
         try {  
            redisTemplate.opsForHash().put(key, item, value);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 向一张hash表中放入数据,如果不存在将创建 
     * @param key 键 
     * @param item 项 
     * @param value 值 
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间 
     * @return true 成功 false失败 
     */  
    public boolean hset(String key,String item,Object value,long time) {  
         try {  
            redisTemplate.opsForHash().put(key, item, value);  
            if(time>0){  
                expire(key, time);  
            }  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 删除hash表中的值 
     * @param key 键 不能为null 
     * @param item 项 可以使多个 不能为null 
     */  
    public void hdel(String key, Object... item){    
        redisTemplate.opsForHash().delete(key,item);  
    }   
      
    /** 
     * 判断hash表中是否有该项的值 
     * @param key 键 不能为null 
     * @param item 项 不能为null 
     * @return true 存在 false不存在 
     */  
    public boolean hHasKey(String key, String item){  
        return redisTemplate.opsForHash().hasKey(key, item);  
    }   
      
    /** 
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回 
     * @param key 键 
     * @param item 项 
     * @param by 要增加几(大于0) 
     * @return 
     */  
    public double hincr(String key, String item,double by){    
        return redisTemplate.opsForHash().increment(key, item, by);  
    }  
      
    /** 
     * hash递减 
     * @param key 键 
     * @param item 项 
     * @param by 要减少记(小于0) 
     * @return 
     */  
    public double hdecr(String key, String item,double by){    
        return redisTemplate.opsForHash().increment(key, item,-by);    
    }    
      
    //============================set=============================  
    /** 
     * 根据key获取Set中的所有值 
     * @param key 键 
     * @return 
     */  
    public Set<Object> sGet(String key){  
        try {  
            return redisTemplate.opsForSet().members(key);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    /** 
     * 根据value从一个set中查询,是否存在 
     * @param key 键 
     * @param value 值 
     * @return true 存在 false不存在 
     */  
    public boolean sHasKey(String key,Object value){  
        try {  
            return redisTemplate.opsForSet().isMember(key, value);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 将数据放入set缓存 
     * @param key 键 
     * @param values 值 可以是多个 
     * @return 成功个数 
     */  
    public long sSet(String key, Object...values) {  
        try {  
            return redisTemplate.opsForSet().add(key, values);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }  
      
    /** 
     * 将set数据放入缓存 
     * @param key 键 
     * @param time 时间(秒) 
     * @param values 值 可以是多个 
     * @return 成功个数 
     */  
    public long sSetAndTime(String key,long time,Object...values) {  
        try {  
            Long count = redisTemplate.opsForSet().add(key, values);  
            if(time>0) {
            	expire(key, time);  
            }
            return count;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }  
      
    /** 
     * 获取set缓存的长度 
     * @param key 键 
     * @return 
     */  
    public long sGetSetSize(String key){  
        try {  
            return redisTemplate.opsForSet().size(key);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }  
      
    /** 
     * 移除值为value的 
     * @param key 键 
     * @param values 值 可以是多个 
     * @return 移除的个数 
     */  
    public long setRemove(String key, Object ...values) {  
        try {  
            Long count = redisTemplate.opsForSet().remove(key, values);  
            return count;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }  
    //===============================list=================================  
      
    /** 
     * 获取list缓存的内容 
     * @param key 键 
     * @param start 开始 
     * @param end 结束  0 到 -1代表所有值 
     * @return 
     */  
    public List<Object> lGet(String key,long start, long end){  
        try {  
            return redisTemplate.opsForList().range(key, start, end);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    /** 
     * 获取list缓存的长度 
     * @param key 键 
     * @return 
     */  
    public long lGetListSize(String key){  
        try {  
            return redisTemplate.opsForList().size(key);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }  
      
    /** 
     * 通过索引 获取list中的值 
     * @param key 键 
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推 
     * @return 
     */  
    public Object lGetIndex(String key,long index){  
        try {  
            return redisTemplate.opsForList().index(key, index);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean lSet(String key, Object value) {  
        try {  
            redisTemplate.opsForList().rightPush(key,  value);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean lSet(String key, Object value, long time) {  
        try {  
            redisTemplate.opsForList().rightPush(key,  value);  
            if (time > 0) {
            	expire(key, time);  
            }
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean lSet(String key, List<Object> value) {  
        try {  
            redisTemplate.opsForList().rightPushAll(key, value);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 将list放入缓存 
     * @param key 键 
     * @param value 值 
     * @param time 时间(秒) 
     * @return 
     */  
    public boolean lSet(String key, List<Object> value, long time) {  
        try {  
            redisTemplate.opsForList().rightPushAll(key, value);  
            if (time > 0) {
            	expire(key, time);  
            }
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
      
    /** 
     * 根据索引修改list中的某条数据 
     * @param key 键 
     * @param index 索引 
     * @param value 值 
     * @return 
     */  
    public boolean lUpdateIndex(String key, long index,Object value) {  
        try {  
            redisTemplate.opsForList().set(key, index,  value);  
            return true;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }   
      
    /** 
     * 移除N个值为value  
     * @param key 键 
     * @param count 移除多少个 
     * @param value 值 
     * @return 移除的个数 
     */  
    public long lRemove(String key,long count,Object value) {  
        try {  
            Long remove = redisTemplate.opsForList().remove(key, count, value);  
            return remove;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }  
    
  //============================zset=============================  
    /** 
     * 将数据放入set缓存 
     * @param key 键 
     * @param values 值 可以是多个 
     * @return 成功个数 
     */  
    public Boolean zsSet(String key, Object values, Double score) {  
        try {  
            return redisTemplate.opsForZSet().add(key, values,score);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
    public Boolean zsSet(String key, Object values, Integer score) {  
        try {  
            return redisTemplate.opsForZSet().add(key, values,Double.valueOf(score));  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
    public Boolean zsSet(String key, Object values, Long score) {  
        try {  
            return redisTemplate.opsForZSet().add(key, values,Double.valueOf(score));  
        } catch (Exception e) {  
            e.printStackTrace();  
            return false;  
        }  
    }  
    /** 
     * 获取set缓存的长度 
     * @param key 键 
     * @return 
     */  
    public long zsGetSetSize(String key){  
        try {  
            return redisTemplate.opsForZSet().size(key);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }  
    /**
     * 查询集合中指定顺序的值， 0 -1 表示获取全部的集合内容  zrange
     *
     * 返回有序的集合，score小的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<Object> zsrange(String key, long start, long end) {
    	try {  
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
    
    public Set<Object> zsrerange(String key, long start, long end) {
    	try {  
            return redisTemplate.opsForZSet().reverseRange(key, start, end);
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
    
    public Set<Object>	zrangeByScore(String key, double min, double max){
    	try {  
            return redisTemplate.opsForZSet().rangeByScore(key, min, max);
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
    public Set<Object>	zrangeByScore(String key,Integer min,Integer max){
    	try {  
            return redisTemplate.opsForZSet().rangeByScore(key, Double.valueOf(min), Double.valueOf(max));
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
    
    public Double incrementScore(String key, Object values, double delta) {
    	try {  
    		return redisTemplate.opsForZSet().incrementScore(key,values,delta);
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0.0;  
        }  
    }
    
    public long intersectAndStore(String key, String otherKey, String destKey) {
    	try {  
    		return redisTemplate.opsForZSet().intersectAndStore(key,otherKey,destKey);
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }
    
    public long zsCard(String key) {
    	try {  
    		return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }
   
    
    public long zsCount(String key,double min,double max) {
    	try {  
    		return redisTemplate.opsForZSet().count(key, min, max);
        } catch (Exception e) {  
            e.printStackTrace();  
            return 0;  
        }  
    }
    public long zsCount(String key,Long min,Long max) {
    	return redisTemplate.opsForZSet().count(key, Double.valueOf(min), Double.valueOf(max));
    }
 	/**
 	 * 批量导入数据
 	 * @param key
 	 * @param list
 	 * @return
 	 */
 	public Object zSetList(String key, List<StudentData> list) {
 		final StringRedisSerializer stringRedisSerializer = (StringRedisSerializer) redisTemplate.getKeySerializer();
 		final RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
 		final byte[] rawKey = stringRedisSerializer.serialize(key);
            redisTemplate.executePipelined(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    for (StudentData item : list) {
                        byte[] rawStr =valueSerializer.serialize(item.getUuid());
                        //在set中添加数据
                        connection.zAdd(rawKey,-Double.valueOf(item.getTotalPoints()),rawStr);
                    }
                    connection.closePipeline();
                    return null;
                }
            });
            return null;      
        }
    
    
 	public Object zSetCountList(String key, List<StudentData> list) {
 		final StringRedisSerializer stringRedisSerializer = (StringRedisSerializer) redisTemplate.getKeySerializer();
 		final RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
 		final byte[] rawKey = stringRedisSerializer.serialize(key);
            redisTemplate.executePipelined(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    for (StudentData item : list) {
                        byte[] rawStr =valueSerializer.serialize(item.getUuid());
                        //在set中添加数据
                        connection.zAdd(rawKey,Double.valueOf(item.getSchoolCode()),rawStr);
                    }
                    connection.closePipeline();
                    return null;
                }
            });
            return null;      
        }
 	
 	public Object zIncrementScoreList(String key, List<SchoolRecycleCount> list) {
 		final StringRedisSerializer stringRedisSerializer = (StringRedisSerializer) redisTemplate.getKeySerializer();
 		final RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
 		final byte[] rawKey = stringRedisSerializer.serialize(key);
            redisTemplate.executePipelined(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection)
                        throws DataAccessException {
                    for (SchoolRecycleCount item : list) {
                        byte[] rawStr =valueSerializer.serialize(item.getSchoolCode());
                        //在set中添加数据
                        connection.zIncrBy(rawKey,-Double.valueOf(item.getPoints()), rawStr);
                    }
                    connection.closePipeline();
                    return null;
                }
            });
            return null;      
        }
 	
 	
	// 封装为方法
	public Object sSet(String key, List<String> list) {
		final StringRedisSerializer stringRedisSerializer = (StringRedisSerializer) redisTemplate.getKeySerializer();
		final RedisSerializer<String> valueSerializer = (RedisSerializer<String>) redisTemplate.getValueSerializer();
		final byte[] rawKey = stringRedisSerializer.serialize(key);
           redisTemplate.executePipelined(new RedisCallback<Object>() {
               @Override
               public Object doInRedis(RedisConnection connection)
                       throws DataAccessException {
                   for (String str : list) {
                       byte[] rawStr =valueSerializer.serialize(str);
                       //在set中添加数据
                       connection.sAdd(rawKey,rawStr);
                   }
                   connection.closePipeline();
                   return null;
               }
           });
           return null;      
       }
}
