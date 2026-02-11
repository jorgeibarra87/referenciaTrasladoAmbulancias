package husj.referencia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable()) // <-- Deshabilita la autenticación Basic
                .formLogin(formLogin -> formLogin.disable()) // <-- Deshabilita la autenticación de formulario
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                            authorizeRequests.requestMatchers(AUTH_WHITLIST).permitAll();
                            authorizeRequests.requestMatchers(HttpMethod.GET, "/datos/**").hasAnyRole("ADMINISTRADOR", "REFERENCIA_EXPORTAR_DATA", "REFERENCIA_FORMULARIO", "REFERENCIA_MODIFICAR_DATA","REFERENCIA_COMENTARIO_TRIAGE");
                            authorizeRequests.requestMatchers(HttpMethod.PUT, "/datos/actualizar-ingresos").hasAnyRole("ADMINISTRADOR", "REFERENCIA_EXPORTAR_DATA", "REFERENCIA_MODIFICAR_DATA","REFERENCIA_COMENTARIO_TRIAGE");
                            authorizeRequests.requestMatchers(HttpMethod.PUT, "/datos/observacion-triage/**").hasAnyRole("ADMINISTRADOR", "REFERENCIA_COMENTARIO_TRIAGE");
                            authorizeRequests.requestMatchers(HttpMethod.PUT, "/datos/comentario").hasAnyRole("ADMINISTRADOR", "REFERENCIA_MODIFICAR_DATA");
                            authorizeRequests.requestMatchers(HttpMethod.POST, "/datos/**").hasAnyRole("ADMINISTRADOR", "REFERENCIA_FORMULARIO");
                            authorizeRequests.anyRequest().authenticated();
                        }
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private static final String[] AUTH_WHITLIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> null; // Esto desactiva el UserDetailsService por defecto
    }

}