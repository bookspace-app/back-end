package com.example.bookspace.beans;

import java.io.IOException;

import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bookspace.Inputs.UserInput;
import com.example.bookspace.models.CustomOAuth2User;
import com.example.bookspace.models.User;
import com.example.bookspace.repositories.UserRepository;
import com.example.bookspace.services.CustomOAuth2UserService;
import com.example.bookspace.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import net.bytebuddy.utility.RandomString;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and().csrf().disable()
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

                            // String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
                            // int length = 10;
                            // Random random = null;
                            // try {
                            //     random = SecureRandom.getInstanceStrong();
                            // } catch (NoSuchAlgorithmException e1) {
                            //     e1.printStackTrace();
                            // }
                            // StringBuilder sb = new StringBuilder(length);
                            // for (int i = 0; i < length; i++) {
                            //     int indexRandom = random.nextInt( symbols.length );
                            //     sb.append( symbols[indexRandom] );
                            // }

                            String password = RandomString.make();
                            UserInput userDetails = new UserInput(oauthUser.getEmail(), oauthUser.getName(), tempUsername, password, LocalDate.now(), null, null);
                            User user = new User();
                            try {
                                if (!userRepository.existsUserByEmail(email)) {
                                    userService.postUser(userDetails);
                                    SimpleMailMessage mail = new SimpleMailMessage();
                                    mail.setTo(email);
                                    mail.setFrom("bookspace.application@gmail.com");
                                    mail.setSubject("¡Enhorabuena!");
                                    mail.setText("Hola " + userDetails.getName() + ", \n ¡Enhorabuena! Te has registrado correctamente en Book Space. \n Aquí tienes tu contraseña de BookSpace: " + userDetails.getPassword());
                                
                                    javaMailSender.send(mail);

                                    String text = "¡Enhorabuena! Te has registrado correctamente en Book Space. Podras encontrar tu contraseña en tu mail.";

                                    response.setContentType("text/plain");
                                    response.setCharacterEncoding("UTF-8");
                                    response.getWriter().write(text);
                                }
                                else {
                                    user = userRepository.getUserByEmail(email);
                                    userDetails.setPassword(user.getPassword());

                                    String text = "Enter using the Login of the application with your username and password.";

                                    response.setContentType("text/plain");
                                    response.setCharacterEncoding("UTF-8");
                                    response.getWriter().write(text);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                });
            }
                        
        
    
     
    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;
}