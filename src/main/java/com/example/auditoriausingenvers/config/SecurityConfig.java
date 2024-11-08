
package com.example.auditoriausingenvers.config;

import com.example.auditoriausingenvers.outros.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private UsuarioService userService;

    
    /*protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin().disable()
            .csrf().disable()
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers(HttpMethod.POST).hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
            .anyRequest().authenticated();
    }*/
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .formLogin(login -> login.disable())
        .authorizeHttpRequests(requests ->
            requests
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        )
        .httpBasic(withDefaults()) // Use a configuração padrão do httpBasic
        .csrf(csrf -> csrf.disable()); // Desabilita o CSRF
        
        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
