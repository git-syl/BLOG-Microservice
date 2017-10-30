package cn.syl.blogmain.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

/**
 * REDIS 集群配置类
 *
 * @author: syl  Date: 2017/10/30 Email:nerosyl@live.com
 */
@Configuration //相当于定义个配置文件applicationContext.xml
public class RedisClusterConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Bean//bean
    public JedisCluster getJedisCluster() {
        //解析字符串
        Set<HostAndPort> nodes = new HashSet<>();
        try {
            String[] cNodes = clusterNodes.split(",");
            for (String cNode : cNodes) {
                //192.168.123.1:7001
                String[] hp = cNode.split(":");
                nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));

            }
            return new JedisCluster(nodes);
        } catch (ArrayIndexOutOfBoundsException | PatternSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;


    }

//    @Test
//    public void t1() {
//        String s = "1,2,3";
//        String[] split = s.split(",");
//        String[] split1 = ",".split(s);
//        System.out.println(split[1]);
//        System.out.println(split1[1]);
//    }

}
