package online.pangge.user.controller;

import com.alibaba.fastjson.JSONObject;
import online.pangge.user.dao.mapper.PersonMapper;
import online.pangge.user.domain.PersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MongoController {
    @Autowired
    private MongoTemplate template;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private PersonMapper personMapper;

    private static final String mongoKey = "mogno";
    private static final String redisKey = "redis";
    private static final String mysqlKey = "mysql";


    @RequestMapping("aaa.do")
    public Map<String,Object> test(){
        Map<String,Object> map = new HashMap<>();
//        Long key = redisTemplate.opsForValue().increment(mongoKey);
        PersonDO personDO = new PersonDO();
        personDO.setAge(23);
        personDO.setId(123);
        personDO.setUsername("asdfasdf");
        template.insert(personDO,"a");
        map.put("aaa",template.findAll(HashMap.class,"a"));
        return map;
    }

    @RequestMapping("bbb.do")
    public Map<String,Object> bbb(){
        Map<String,Object> map = new HashMap<>();
        ValueOperations<String,String> values = redisTemplate.opsForValue();
        Integer key = Math.toIntExact(redisTemplate.opsForValue().increment(redisKey));
        PersonDO personDO = new PersonDO();
        personDO.setId(key);
        personDO.setAge(45);
        personDO.setUsername("four");
        values.set("pueee", JSONObject.toJSONString(personDO));
        map.put("redis",values.get("pueee"));
        return map;
    }

    @RequestMapping("/ccc.do")
    public Map<String, Object> ccc() {
        Map<String, Object> map = new HashMap<>();
        PersonDO personDO = new PersonDO();
        personDO.setId(112);
        personDO.setAge(45);
        personDO.setUsername("four");
        personMapper.insert(personDO);
        map.put("redis", JSONObject.toJSONString(personMapper.selectAll()));
        return map;
    }
}
