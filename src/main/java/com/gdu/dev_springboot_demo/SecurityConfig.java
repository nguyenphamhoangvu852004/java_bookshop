package com.gdu.dev_springboot_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable) // TẮT CSRF để tránh lỗi 403
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/**").permitAll()
                                                .anyRequest().permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout") // Khi truy cập URL này sẽ logout
                                                .logoutSuccessUrl("/auth/formLogin") // Sau khi logout thì chuyển hướng
                                                .invalidateHttpSession(true) // Xóa session
                                                .deleteCookies("JSESSIONID") // Xóa cookie session
                                );
                return http.build();
        }

}
