package cn.linye.grus.infrastructure.caching;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author tianzhonghai
 * @Date 2017/7/19.
 */
@Component
public class GuavaCache {
    private Cache<String, Object> cache;
    public  GuavaCache(){
           cache = CacheBuilder.newBuilder().maximumSize(10000)
                   .expireAfterWrite(3600, TimeUnit.SECONDS)
               .build(); // look Ma, no CacheLoader
    }

    public Cache<String, Object> getCache(){
        return  cache;
    }
}
