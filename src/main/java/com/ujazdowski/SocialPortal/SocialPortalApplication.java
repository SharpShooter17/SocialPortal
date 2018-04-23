/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <phk@FreeBSD.ORG> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Poul-Henning Kamp
 * ----------------------------------------------------------------------------
 */

package com.ujazdowski.SocialPortal;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = { 	com.ujazdowski.SocialPortal.configuration.SecurityConfiguration.class,
										com.ujazdowski.SocialPortal.service.UserService.class,
										com.ujazdowski.SocialPortal.repository.UsersRepository.class,
										com.ujazdowski.SocialPortal.controller.HomeController.class				})
public class SocialPortalApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SocialPortalApplication.class);
	}

	public static void main(String[] args) {
			BasicConfigurator.configure();
			SpringApplication.run(SocialPortalApplication.class, args);
	}
}
