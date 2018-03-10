package com.ujazdowski.SocialPortal.configuration;

import com.ujazdowski.SocialPortal.repository.UsersRepository;
import com.ujazdowski.SocialPortal.service.CustomUserDerailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = com.ujazdowski.SocialPortal.SocialPortalApplication.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    public SecurityConfiguration(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/home/**").hasRole("USER")
                .antMatchers("/register").anonymous()
                .antMatchers("/login**").anonymous()
                .anyRequest().permitAll()
                .and()
                .formLogin().usernameParameter("email").passwordParameter("password").loginPage("/login").loginProcessingUrl("/login").permitAll()
                .and()
                .rememberMe().rememberMeParameter("remember-me").tokenValiditySeconds(84000).key("SocialPortalKey")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }

    @Autowired
    protected  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new CustomUserDerailsService(usersRepository)).passwordEncoder(new BCryptPasswordEncoder());
    }
}
