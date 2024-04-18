package com.retail.e_com.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.retail.e_com.entity.User;


@Configuration
public class CacheBeanConfig {

	@Bean
	CacheStorer<String> otpCache()
	{
		return new CacheStorer<String>(50);
	}
	@Bean
	CacheStorer<User>  usercache()
	{
		return new CacheStorer<User>(50);
	}
}
