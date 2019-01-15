package com.lyp.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MySelfRulr_LYP {
	
	@Bean
	public IRule myRule ()
	{
		return new MySelfRule();//自定义算法
	}

}
