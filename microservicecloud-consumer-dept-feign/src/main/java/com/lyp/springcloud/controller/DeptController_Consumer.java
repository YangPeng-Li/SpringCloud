package com.lyp.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lyp.springcloud.entities.Dept;
import com.lyp.springcloud.service.DeptClientService;

/**
 * 我是消费者，不需要关心生产者的（Service）
 * 
 * 
 * * 使用： 使用restTemplate 访问 restful接口 （url、requestMap、ResponseBean.class）这三个参数代表
 * REST请求地址，请求参数，HTTP响应转换成对象类型
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
public class DeptController_Consumer {
	@Autowired
	private DeptClientService service;

	@RequestMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return this.service.get(id);
	}

	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list() {
		return this.service.list();
	}

	@RequestMapping(value = "/consumer/dept/add")
	public Object add(Dept dept) {
		return this.service.add(dept);
	}

}
