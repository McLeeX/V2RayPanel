package me.mclee.v2ray.panel.config;

import me.mclee.v2ray.panel.security.filter.CustomAuthenticationFilter;
import me.mclee.v2ray.panel.security.handler.LoginFailHandler;
import me.mclee.v2ray.panel.security.handler.LoginSuccessHandler;
import me.mclee.v2ray.panel.security.handler.LogoutSuccessHandler;
import me.mclee.v2ray.panel.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configurable
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/database/**", "/database", "/login", "/signup", "/static/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/doLogin")
                .and().logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler()).deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and().exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();
        http.cors();
        http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
    }

    @Bean
    public LoginFailHandler authFailHandler() {
        return new LoginFailHandler("/login/failure");
    }

    @Bean
    public LoginSuccessHandler authSuccessHandler() {
        return new LoginSuccessHandler("/login/success");
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.userDetailsService(userService);
    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authSuccessHandler());
        filter.setAuthenticationFailureHandler(authFailHandler());
        return filter;
    }
}
