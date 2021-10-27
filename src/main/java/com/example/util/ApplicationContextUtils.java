package com.example.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/10/26 22:12
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    //获得工厂对象
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    //根据bean的名字获取工厂中指定的bean 对象
    public static Object getBean(String BeanName){
        return context.getBean(BeanName);
    }
}
