package prueba.com.pruebaAgenda.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import prueba.com.pruebaAgenda.security.CustomUserDetailsService;
import prueba.com.pruebaAgenda.security.JwtSecurityService;
import prueba.com.pruebaAgenda.security.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtSecurityService jwtSecurityService;
	private final CustomUserDetailsService userDetailsService;
	
	public SecurityConfig(JwtSecurityService jwtSecurityService,
			CustomUserDetailsService userDetailsService) {
		this.jwtSecurityService = jwtSecurityService;
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtSecurityService, userDetailsService);

		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> 
				auth
	            .requestMatchers("/auth/login","/persona/create").permitAll()
	            .anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(Customizer.withDefaults())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); 
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
        config.setAllowedHeaders(List.of("Origin","Content-Type","Accept","Authorization"));
        config.setAllowCredentials(true);
        config.setExposedHeaders(List.of("Authorization","Content-Type"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source); 
    }
}
