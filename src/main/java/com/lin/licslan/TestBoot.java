package com.lin.licslan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author licslan
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.lin.licslan.mysql.mapper"})
public class TestBoot {

	public static void main(String[] args) {
		SpringApplication.run(TestBoot.class, args);
	}



}
