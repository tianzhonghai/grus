package cn.linye.grus.infrastructure.utils;

import org.dozer.Mapper;
import org.dozer.spring.DozerBeanMapperFactoryBean;

/**
 * Created by Tim on 2017/7/22.
 */
public class DozerUtils {

    private Mapper getDozerMapper() {
        DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean =(DozerBeanMapperFactoryBean)SpringUtils.getBean("dozerBean");
        Mapper mapper = null;
        try {
            mapper = dozerBeanMapperFactoryBean.getObject();
        }catch (Exception ex){
            throw new RuntimeException("获取dozerMapper异常",ex);
        }
        return mapper;
    }
}
