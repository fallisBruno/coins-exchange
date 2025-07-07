package com.exchange.coins;

import com.exchange.coins.domain.Exchange;
import com.exchange.coins.persist.CoinsCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinsApplication.class, args);
		CoinsCache.startCache();
	}

}
