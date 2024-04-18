package com.retail.e_com.cache;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;


public class CacheStorer<T> {

	private Cache<String, T>  cache;
	
public CacheStorer( int maxAge ) 
{
	this.cache= CacheBuilder.newBuilder()
	.expireAfterWrite(Duration.ofMinutes(maxAge))
	.concurrencyLevel(Runtime.getRuntime().availableProcessors())
	.build();
}


public void add(String Key,T value)
{
	cache.put(Key,value);
}
	
public T getdata(String key)
{	
return cache.getIfPresent(key);
}

public void remove(String key)
{
	cache.invalidate(key);
}

}