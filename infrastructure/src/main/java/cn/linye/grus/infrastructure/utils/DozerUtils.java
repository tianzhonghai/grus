package cn.linye.grus.infrastructure.utils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Tim on 2017/7/22.
 */
@Component
public class DozerUtils {

    public static Mapper getDozerMapper() {
        DozerBeanMapper mapper =(DozerBeanMapper)SpringUtils.getBean("dozerBean");
//        Mapper mapper = null;
//        try {
//            mapper = dozerBeanMapperFactoryBean.getObject();
//        }catch (Exception ex){
//            throw new RuntimeException("获取dozerMapper异常",ex);
//        }
        return mapper;
    }

    public static <From,To> List<To> mapList(List<From> fromList,final Class<To> toClass){
        return Lists.transform(fromList, new Function<From, To>() {

            @Override
            public To apply(From from){
                return DozerUtils.getDozerMapper().map(from,toClass);
            }

        });
    }
}
