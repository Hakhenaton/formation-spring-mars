package fr.sncf.d2d.up2dev.colibri2.security.configuration;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import fr.sncf.d2d.up2dev.colibri2.security.models.ApplicationUserDetails;
import fr.sncf.d2d.up2dev.colibri2.users.models.Role;
import fr.sncf.d2d.up2dev.colibri2.users.persistence.UsersRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, UsersRepository usersRepository) throws Exception {
        http
            .formLogin(formLogin -> formLogin.disable())
            .csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults())
            .headers(headers -> headers.disable())
            .userDetailsService(username -> usersRepository.findByUsername(username)
                .map(ApplicationUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s was not found", username)))
            )
            .exceptionHandling(exceptions -> exceptions
                .accessDeniedHandler((req, res, exception) -> res.setStatus(HttpStatus.FORBIDDEN.value()))
                .authenticationEntryPoint(new BasicAuthenticationEntryPoint())
            )
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/colis").fullyAuthenticated()
                .anyRequest().denyAll()
            );
        
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecureRandom secureRandom() throws NoSuchAlgorithmException {
        return SecureRandom.getInstanceStrong();
    }
}
