package com.ujazdowski.SocialPortal;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {com.ujazdowski.SocialPortal.controller.HomeController.class,
										com.ujazdowski.SocialPortal.configuration.SecurityConfiguration.class,
										com.ujazdowski.SocialPortal.service.UserService.class,
										com.ujazdowski.SocialPortal.repository.UsersRepository.class})
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
