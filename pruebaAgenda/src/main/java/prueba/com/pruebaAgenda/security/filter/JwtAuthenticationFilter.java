package prueba.com.pruebaAgenda.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import prueba.com.pruebaAgenda.security.CustomUserDetailsService;
import prueba.com.pruebaAgenda.security.JwtSecurityService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private static final List<String> WHITELIST_URLS = Arrays.asList(
			"/persona/create",
	        "/auth/login"
	    );
	private String AUTHORIZATION_="Authorization";
	private String BEARER="Bearer ";
	private String pathAuth="/api/v1/auth/login";
	
	 private final JwtSecurityService jwtSecurityService;
	 private final CustomUserDetailsService userDetailsService;
	 private final ObjectMapper objectMapper = new ObjectMapper();
	
	public JwtAuthenticationFilter(JwtSecurityService jwtSecurityService, CustomUserDetailsService userDetailsService) {
		this.jwtSecurityService = jwtSecurityService;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String pathUrl=request.getServletPath();
		if (isPublicUrl(pathUrl)) {
			filterChain.doFilter(request, response);
			return;
		}
		String authHeader = request.getHeader(AUTHORIZATION_);
		String jwt;
		String claveUser;
		List<Claims> perfiles= new ArrayList<>();
		if (authHeader == null || !authHeader.startsWith(BEARER)) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt=authHeader.substring(BEARER.length());
		try {
			claveUser = jwtSecurityService.getUsernameFromToken(jwt);
			Claims claim=jwtSecurityService.extractAllClaims(jwt);
			if (claveUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(claveUser);
				if (jwtSecurityService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}else {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			        Map<String, Object> errorDetails = new HashMap<>();
			        errorDetails.put("status", HttpServletResponse.SC_FORBIDDEN);
			        errorDetails.put("error", "Forbidden");
			        errorDetails.put("message", "El JWT ha expirado o es inválido.");
			        objectMapper.writeValue(response.getOutputStream(), errorDetails);
			        return;
				}
			}
		}catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        Map<String, Object> errorDetails = new HashMap<>();
	        errorDetails.put("status", HttpServletResponse.SC_FORBIDDEN);
	        errorDetails.put("error", "Forbidden");
	        errorDetails.put("message", "El JWT ha expirado o es inválido.");
	        objectMapper.writeValue(response.getOutputStream(), errorDetails);
	        return;
		}
		filterChain.doFilter(request, response);
		
	}
	
	private boolean isPublicUrl(String url) {
        return WHITELIST_URLS.stream().anyMatch(url::equals);
    }
}
