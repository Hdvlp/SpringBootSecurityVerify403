package com.springbootsecurityverify403.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.springbootsecurityverify403.demo.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;

    SecurityConfig(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean

    @Order(100)
    public SecurityFilterChain securityFilterChainStaticFiles(HttpSecurity http) throws Exception{
        String[] matchedPaths = { 
            "/js/**",
            "/css/**",
            "/images/**"
        };
        
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(matchedPaths)
            .authorizeHttpRequests(request -> 

                request
                .requestMatchers(matchedPaths)
                .permitAll()

            )
            .build();
    }

    @Order(200)
    public SecurityFilterChain securityFilterChainAlive(HttpSecurity http) throws Exception{
        String[] matchedPaths = { 
            "/alive", 
            "/alive/alive", 
            "/alive/**" 
        };
        
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(matchedPaths)
            .authorizeHttpRequests(request -> 

                request
                .requestMatchers(matchedPaths)
                .permitAll()

            )
            .build();
    }


    @Bean
    @Order(300)
    public SecurityFilterChain securityFilterChainRegister(HttpSecurity http) throws Exception{
        String[] matchedPaths = { 
            "/register", 
            "/userRegister"
        };
        
        return http
            .csrf(customizer -> customizer.disable())
            .securityMatcher(matchedPaths)
            .authorizeHttpRequests(request -> 

                request
                .requestMatchers(matchedPaths)
                .permitAll()

            )
            .build();
    }

    @Bean
    @Order(400)
    public SecurityFilterChain securityFilterChainUser(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/user", 
            "/user/**"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .hasAnyRole("ADMIN", "EDITOR", "USER")
                    .anyRequest()
                    .authenticated()
            )

            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }


    @Bean
    @Order(500)
    public SecurityFilterChain securityFilterChainUserDeny(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/user", 
            "/user/**"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .denyAll()
            )

            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }

    @Bean
    @Order(600)
    public SecurityFilterChain securityFilterChainEditor(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/editor", 
            "/editor/**"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .hasAnyRole("ADMIN", "EDITOR")
                    .anyRequest()
                    .authenticated()
            )

            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }

    @Bean
    @Order(700)
    public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/admin", 
            "/admin/**"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .hasAnyRole("ADMIN")
                    .anyRequest()
                    .authenticated()
            )

            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }


    @Bean
    @Order(800)
    public SecurityFilterChain securityFilterChainAdminAndEditor(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/admin", 
            "/admin/**",
            "/editor",
            "/editor/**"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .denyAll()
            )

            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }

    
    @Bean
    @Order(900)
    public SecurityFilterChain securityFilterChainLoginPath(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/", 
            "/login",
            "/setStatus"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .permitAll()
            )
            .formLogin(
                formLogin ->

                formLogin
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(roleBasedSuccessHandler())
            )
            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }

    @Bean
    @Order(1000)
    public SecurityFilterChain securityFilterChainAuthLogin(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/auth/login**"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )

            // If you want roles of "ADMIN", "EDITOR" or "USER" to enter...
            
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .hasAnyRole("ADMIN", "EDITOR", "USER")
                    .anyRequest()
                    .authenticated()
            )

            // If you want anyone to enter...

            // .authorizeHttpRequests(request -> 
            //     request
            //         .requestMatchers(matchedPaths)
            //         .permitAll()
            // )

            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }

    @Bean
    @Order(1100)
    public SecurityFilterChain securityFilterChainAuthLoginDenyAll(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/auth/login**"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .denyAll()
            )

            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout.logoutUrl("/logout"));
            
        return http.build();
    }

    @Bean
    @Order(1200)
    public SecurityFilterChain securityFilterChainAuthLogout(HttpSecurity http) throws Exception{
        
        String[] matchedPaths = { 
            "/logout"
        };
        
        http
            .csrf(csrf -> csrf.disable())
            .securityMatcher(
                matchedPaths
            )
            .authorizeHttpRequests(request -> 
                request
                    .requestMatchers(matchedPaths)
                    .permitAll()
            )
            .sessionManagement(session -> session
                .sessionConcurrency((concurrency) -> concurrency
								.maximumSessions(1)
								.maxSessionsPreventsLogin(true)
						)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .invalidateHttpSession(true) 
                .deleteCookies("JSESSIONID"));
            
        return http.build();
    }

    private CustomLogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }

    private RoleBasedSuccessHandler roleBasedSuccessHandler(){
        return new RoleBasedSuccessHandler(customUserDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
