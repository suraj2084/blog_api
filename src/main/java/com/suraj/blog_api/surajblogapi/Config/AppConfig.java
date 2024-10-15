package com.suraj.blog_api.surajblogapi.Config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.suraj.blog_api.surajblogapi.Filter.JwtFilter;
import com.suraj.blog_api.surajblogapi.Services.CategoryService;
import com.suraj.blog_api.surajblogapi.Services.CommentService;
import com.suraj.blog_api.surajblogapi.Services.FileService;
import com.suraj.blog_api.surajblogapi.Services.PostService;
import com.suraj.blog_api.surajblogapi.Services.UserService;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.CategoryServiceImpl;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.CommentServiceImpl;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.CustomUserDetailsService;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.FileServiceImpl;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.PostServiceImpl;
import com.suraj.blog_api.surajblogapi.Services.UserServiceImpl.UserServiceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.*;

@Configuration
public class AppConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }

    @Bean
    public PostService postService() {
        return new PostServiceImpl();
    }

    @Bean
    public FileService fileService() {
        return new FileServiceImpl();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceImpl();

    }

    // @Bean
    // public CustomUserDetailsService customUserDetailsService() {
    // return userDetailsService;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(cust -> cust.disable())
                .formLogin(withDefaults())// For Browser Login Form
                .httpBasic(withDefaults())// For PostMan
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sessions
                /*
                 * SessionCreationPolicy.ALWAYS:
                 * This is the default behavior. A session will always be created for each user,
                 * regardless of whether one already exists or not. This is suitable for
                 * applications that rely heavily on sessions for stateful interactions.
                 * SessionCreationPolicy.IF_REQUIRED:
                 * A session will be created only if it is required by the application. If the
                 * application requires a session (e.g., to store user details), one will be
                 * created. Otherwise, if a session is not needed, it will not be created. This
                 * is a balance between stateful and stateless.
                 * SessionCreationPolicy.NEVER:
                 * This policy prevents the creation of a session entirely. If an attempt is
                 * made to create a session, it will throw an exception. This is useful in
                 * scenarios where you want to ensure that no session is ever established, often
                 * in RESTful APIs.
                 * SessionCreationPolicy.STATELESS:
                 * With this policy, the application will never create a session and will not
                 * store any session information. This is particularly useful for RESTful APIs
                 * where each request should be independent and can be authenticated using
                 * tokens (like JWT) instead of sessions. It enhances scalability since each
                 * request can be processed without needing to manage session state on the
                 * server.
                 */
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                        // Here we only permit login and register other One is authenticated
                        .requestMatchers("/api/users/login", "/api/users/register").permitAll()
                        .anyRequest().authenticated()

                );

        return http.build(); // http build is use for build a SecurityFilterChain
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // It's
        // deprecated not use in production
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

    }

}
