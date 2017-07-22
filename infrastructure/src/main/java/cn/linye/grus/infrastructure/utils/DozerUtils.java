package cn.linye.grus.infrastructure.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.stereotype.Component;

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
}
