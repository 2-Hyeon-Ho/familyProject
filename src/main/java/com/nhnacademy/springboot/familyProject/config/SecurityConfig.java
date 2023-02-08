package com.nhnacademy.springboot.familyProject.config;

import com.nhnacademy.springboot.familyProject.auth.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                    .antMatchers("/family/**").authenticated()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .usernameParameter("id")
                    .passwordParameter("pwd")
                    .loginPage("/loginPage")    // 사용자 정의 로그인 페이지, default: /login
                    .loginProcessingUrl("/login")   // 로그인 Form Action Url, default: /login
                    .successHandler(loginSuccessHandler())
                    .and()
                .oauth2Login()
                    .loginPage("/loginPage")
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .authorizedClientService(authorizedClientService())
                    .and()
                .logout()
                    .deleteCookies("JSESSEIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/loginPage")
                    .and()
                .csrf()
                    .disable()
//                    .and()
                .sessionManagement()
                    .sessionFixation()  //로그인을 하면 세션이 바뀌는데 이때 로그인 할때마다 세션의 아이디를 바꾸는 설정
                        .none()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/error/403") //접근이 제한된 페이지 보여주기
                    .and()
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(github());
    }

    private ClientRegistration github() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .userNameAttributeName("name")
                .clientId("5621754e6f5a1329b6e9")
                .clientSecret("34be43e77159a15e2606c04a985e431502fcb4fd")
                .build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
