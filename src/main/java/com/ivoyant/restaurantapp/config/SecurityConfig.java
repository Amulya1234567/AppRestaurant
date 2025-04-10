package com.ivoyant.restaurantapp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

       @Bean
       public UserDetailsManager userDetailsManager(DataSource dataSource){
           return  new JdbcUserDetailsManager(dataSource);
       }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                    http.authorizeHttpRequests(auth->auth
                    // Allow swagger
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                    // Get method accessible to both user and admin
                    .requestMatchers( "/restaurant/getFoodById/**", "/restaurant/getFoodByName/**")
                                    .hasAnyRole("USER", "ADMIN")

                    // Delete only for user
                    .requestMatchers("/restaurant/deleteById/**").hasRole("USER")

                    // PUT only for ADMIN
                    .requestMatchers("/restaurant/Update/**").hasRole("ADMIN")

                    // POST (adding food) only admin can add
                    .requestMatchers("/restaurant/food").hasRole("ADMIN")

                    // Everything else must be authenticated
                    .anyRequest().authenticated()
                    );
                    http.csrf(AbstractHttpConfigurer::disable);
                    http.httpBasic(Customizer.withDefaults());

                   return http.build();
        }

//        @Bean
//        public UserDetailsService userDetailsService() {
//            UserDetails user1 = User.withUsername("user1")
//                    .password("{noop}user1234")
//                    .roles("USER")
//                    .build();
//
//            UserDetails admin = User.withUsername("admin")
//                    .password("{noop}admin1234")
//                    .roles("ADMIN")
//                    .build();
//
//            return new InMemoryUserDetailsManager(user1, admin);
//        }

}


