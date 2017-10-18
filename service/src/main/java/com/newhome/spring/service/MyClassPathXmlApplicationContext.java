package com.newhome.spring.service;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 扩展spring环境准备
 *
 * @author zhangjiayu zhangjiayu
 * @create 2017/10/18
 */
public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext{



    @Override
    protected void initPropertySources() {
        //用于覆盖   添加初始化环境的一些特殊需求   比如一些环境参数的校验  没有这些环境参数  则不能运行程序

        super.initPropertySources();
    }

    @Override
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
        //定制个性化beanFactory

        super.customizeBeanFactory(beanFactory);
        //是否允许覆盖
        super.setAllowBeanDefinitionOverriding(false);
        //是否允许依赖
        super.setAllowCircularReferences(false);

    }
}
