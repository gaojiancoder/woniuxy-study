package io.gao.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

public class JwtTest {

    public static void main(String[] args) {
        //定义盐值，项目中会通过算法产生
        String secret = "sakhfiahsdkabsdhufah@#$@!@!#sdaff";
        //使用盐值产生一个key
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        //创建map用于向payload中保存数据
        HashMap<String, Object> map = new HashMap<>();
        //保存的数据
        map.put("name", "gaojian");
        //使用jjwt生成令牌
        String token = Jwts.builder()
                .setClaims(map)//向payload中保存数据
                //过期时间必须大于系统时间
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60)))
                .signWith(key)//加盐
                .compact();//生成令牌
        System.out.println(token);


        //解析令牌 通过Jwts.parserBuilder()方法创建一个解析令牌对象
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key)//使用指定的盐
                .build()//创建解析令牌对象
                .parseClaimsJws(token)//通过token解析对象
                .getBody();//获取解析对象的payload中保存的内容

        //body是map
        System.out.println(body.get("name"));
    }
}
