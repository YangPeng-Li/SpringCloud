	package com.lyp.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lyp.springcloud.entities.Dept;

/**
 * 我是消费者，不需要关心生产者的（Service）
 * 
 * 
 *  * 使用：
 * 		使用restTemplate 访问 restful接口
 * 		（url、requestMap、ResponseBean.class）这三个参数代表
 * 		REST请求地址，请求参数，HTTP响应转换成对象类型
 * 
 * @author Lyp
 * @date 2018年12月29日
 * @version 1.0
 *
 *
 *
 *
 */
@RestController
public class DeptController_Consumer 
{

//	private static final String REST_URL_CONSUMER_PREFIX = "http://localhost:8001";
	//7001 7002 7003 8001 80 Ribbon 和Eureka整合后Consumer可以直接访问服务而不用再关心地址和端口
	private static final String REST_URL_CONSUMER_PREFIX = "MICROSERVICECLOUD-DEPT"; //微服务名
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping(value="consumer/dept/add")
	public  boolean add (Dept dept)
	{
		/*
		 * （url、requestMap、ResponseBean.class）这三个参数代表
		 * REST请求地址，请求参数，HTTP响应转换成对象类型
		 */
		//return restTemplate.postForEntity(url, request, responseType)
		return restTemplate.postForObject(REST_URL_CONSUMER_PREFIX+"dept/add", dept, Boolean.class);
	}
	
	@RequestMapping(value="consumer/dept/get/{id}")
	public Dept get (@PathVariable Long id)
	{
		return restTemplate.getForObject(REST_URL_CONSUMER_PREFIX+"dept/get/"+id, Dept.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="consumer/dept/list")
	public List<Dept> list ()
	{
		return restTemplate.getForObject(REST_URL_CONSUMER_PREFIX+"dept/list", List.class);
	}
	
	//测试@EnableDiscoveryClient 消费可以调用服务发现
	@RequestMapping(value="/consumer/dept/discovery")
	public Object discovery ()
	{
		return restTemplate.getForObject(REST_URL_CONSUMER_PREFIX+"/dept/discovery", Object.class);
	}
	
}
