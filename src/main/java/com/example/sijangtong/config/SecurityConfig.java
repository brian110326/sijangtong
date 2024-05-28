package com.example.sijangtong.config;

import com.example.sijangtong.handler.CustomeAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize ->
      authorize
        // 로그인 전 허용 화면 및 static 폴더
        .requestMatchers(
          "/",
          "/assets/**",
          "/css/**",
          "/image/**",
          "/js/**",
          "/lib/**"
        )
        .permitAll()
        .requestMatchers(
          "/shop/list",
          "/shop/read",
          "/shop/home",
          "/shop/storeDetail",
          "/shop/insert",
          "/shop/contact",
          "/auth"
        )
        .permitAll()
        // UploadController : 이미지 보여주기
        .requestMatchers("/upload/display")
        .permitAll()
        .requestMatchers("/member/register")
        .permitAll()
        .requestMatchers("/upload/remove")
        .permitAll()
        .requestMatchers("/upload/uploadAjax")
        .permitAll()
        .requestMatchers("/rider/riderList")
        .permitAll()
        .anyRequest()
        .authenticated()
    );

    http.formLogin(login ->
      login
        .loginPage("/member/login")
        .permitAll()
        .defaultSuccessUrl("/shop/list", true)
    );

    http.logout(logout ->
      logout
        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
        .logoutSuccessUrl("/")
    );

    http.sessionManagement(session ->
      session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
    );

    http.exceptionHandling(exception ->
      exception.accessDeniedHandler(customeAccessDeniedHandler())
    );

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  CustomeAccessDeniedHandler customeAccessDeniedHandler() {
    return new CustomeAccessDeniedHandler();
  }
}
