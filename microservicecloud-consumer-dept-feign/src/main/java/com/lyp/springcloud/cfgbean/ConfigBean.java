package com.lyp.springcloud.cfgbean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;

/**
 * RestTemplate 提供了多种便捷访问远程Http服务的方法[客户端]
 * 是一种简单便捷的访问restful 服务模版类，是Spring 提供的用于访问Rest服务的客户端模版工具集
 * 
 * 使用：
 * 		使用restTemplate 访问 restful接口
 * 		（url、requestMap、ResponseBean.class）这三个参数代表
 * 		REST请求地址，请求参数，HTTP响应转换成对象类型
 * 
 * 
 * 
 * @author Lyp
 * @date 2018年12月29日
 * @version 1.0
 *
 */
@Configuration 
public class ConfigBean {  //boot ---->string applicationContext.xml ----@Configration 配置 configBean = application.xml

	@Bean
	@LoadBalanced//给客户端装个LB 自己实现负载均衡  SpringCloud Ribbon 是基于Netflix Ribbon 实现的一套客户端，负载均衡的工具
	public RestTemplate getRestTemplate ()
	{
		return new RestTemplate();
	}
	
	@Bean
	public IRule myRule ()
	{
//		return new RoundRobinRule();//选用随机算法替代默认的轮询算法
		return new RandomRule();//选用随机算法替代默认的轮询算法
	}
	
	
}
