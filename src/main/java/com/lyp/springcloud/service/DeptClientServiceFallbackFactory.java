package com.lyp.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lyp.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

/**
 * 与主干业务逻辑分离
 * 此方法实现与Controller 的解耦
 * 
 * 
 * 
 * 
 * 
 * @author Lyp
 * @date 2019年1月11日
 * @version 1.0
 *
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	
	@Override
	public DeptClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new  DeptClientService() {

			@Override
			public Dept get(long id) {
				// TODO Auto-generated method stub
				return new Dept().setDeptno(id).setDname("该ID"+id+"没有对应的信息，Consumer客户端提供降级信息，此刻服务Provider已经关闭").setDb_source("No this database in MySQL");
			}

			@Override
			public List<Dept> list() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean add(Dept dept) {
				// TODO Auto-generated method stub
				return false;
			}};
	}

}
