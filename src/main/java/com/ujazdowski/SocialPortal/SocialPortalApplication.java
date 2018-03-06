package com.ujazdowski.SocialPortal;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SocialPortalApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SocialPortalApplication.class);
	}

	public static void main(String[] args) {
			BasicConfigurator.configure();
			SpringApplication.run(SocialPortalApplication.class, args
		);
	}
}
