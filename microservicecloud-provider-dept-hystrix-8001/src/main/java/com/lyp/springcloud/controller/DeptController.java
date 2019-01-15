package com.lyp.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lyp.springcloud.entities.Dept;
import com.lyp.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

/**
 * Hystrix 模拟返回抛出异常
 * 
 * @author Lyp
 * @date 2019年1月11日
 * @version 1.0
 *
 */
@RestController
public class DeptController {

	@Autowired
	private DeptService service;


	
	@RequestMapping(value="dept/get/{id}",method=RequestMethod.GET)
	//一旦调用服务失败抛出了错误信息后，会自动调用@HystrixCommand标注好后的fallbackMethod调用类中的指定方法返回指定的方法
	@HystrixCommand(fallbackMethod="processHystrix_Get")//向调用方返回 ，为Get擦屁股的方法
	public Dept get (@PathVariable Long id)
	{
		Dept dept = this.service.get(id); 
		if (dept == null)
		{
			throw new RuntimeException ("该ID:"+id+"没有对应的信息");
		}
		return dept;
	} 
	public Dept processHystrix_Get (@PathVariable("id") long id) 
	{
		return new Dept().setDeptno(id).setDname("该ID"+id+"没有对应信息，null--@HystrixCommand").setDb_source("no this database in MySQL");
	}
	
	/*
	 * 下面还有 add list update 这样对应的fallback 则也会有同样的处理，容易造成方法膨胀
	 * 我们把主干 的业务逻辑和处理异常的切面整合在一起他们产生的高耦合，实现一种方法业务处理和异常方法不出现高度耦合
	 * 只希望主干还是干干净净的业务方法 实现解耦和分离
	 */
	
}
