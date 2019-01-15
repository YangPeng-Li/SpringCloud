package com.lyp.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lyp.springcloud.entities.Dept;

/**
 * 每次修改API 都需要clear 和Install
 * 
 * 不想面向微服务编程，还是面向接口编程
 * API--公共接口
 * 
 * @deprecated:修改microservicecloud-api工程
 * 根据已经有的DeptClientService接口实现一个
 * 一个FallbackFactory接口的类DeptClientServiceFallbackFactory 
 * @FeignClient的fallbackFactory 的方法对应接口中方法如果都出现问题了都去找fallback
 * @author Lyp
 * @date 2019年1月11日
 * @version 1.0
 *
 */
//@FeignClient(value="MICROSERVICECLOUD-DEPT")

@FeignClient(value="MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
	
	@RequestMapping(value ="/dept/get/{id}",method=RequestMethod.GET)
	public Dept get (@PathVariable("id")long id);
	
	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	public List<Dept> list();
	
	@RequestMapping(value="/dept/add",method = RequestMethod.POST)
	public boolean add (Dept dept);
	
}