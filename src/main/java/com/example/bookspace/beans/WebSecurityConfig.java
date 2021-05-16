package com.example.bookspace.beans;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.models.CustomOAuth2User;
import com.example.bookspace.services.CustomOAuth2UserService;
import com.example.bookspace.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .and()
            .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                    .userService(oauthUserService);
        
        http.oauth2Login()
            .loginPage("/login")
            .userInfoEndpoint()
                .userService(oauthUserService)
            .and()
            .successHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                        org.springframework.security.core.Authentication authentication)
                        throws IOException, ServletException {

                            CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                            String tempUsername = "";
                            String email = oauthUser.getEmail();

                            for (int i = 0; i < email.length(); i++) {
                                if (email.toCharArray()[i] != '@') tempUsername = tempUsername + email.toCharArray()[i];
                                else break;
                            }

                            String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
                            int length = 10;
                            Random random = null;
                            try {
                                random = SecureRandom.getInstanceStrong();
                            } catch (NoSuchAlgorithmException e1) {
                                e1.printStackTrace();
                            }
                            StringBuilder sb = new StringBuilder(length);
                            for (int i = 0; i < length; i++) {
                                int indexRandom = random.nextInt( symbols.length );
                                sb.append( symbols[indexRandom] );
                            }
                            String password = sb.toString();

                            UserInput userDetails = new UserInput(oauthUser.getEmail(), oauthUser.getName(), tempUsername, password, LocalDate.now(), null, null);
                            userDetails.setAuthProvider("GOOGLE");
                            try {
                                userService.postUser(userDetails);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            response.sendRedirect("/");
                }
            });
    }
     
    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private UserService userService;
}