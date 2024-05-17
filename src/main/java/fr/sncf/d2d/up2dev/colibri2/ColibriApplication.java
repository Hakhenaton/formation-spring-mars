package fr.sncf.d2d.up2dev.colibri2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class ColibriApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColibriApplication.class, args);
	}

}
