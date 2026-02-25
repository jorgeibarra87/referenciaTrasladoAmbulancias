package husj.referencia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                            // ENDPOINTS PÚBLICOS (para pruebas)
                            authorizeRequests
                                    .requestMatchers(
                                            "/traslados-completos/**",
                                            "/traslados/**",
                                            "/facturaciones/**",
                                            "/cuentas-medicas/**",
                                            "/error"
                                    ).permitAll();

                            // Autenticación / swagger
                            authorizeRequests
                                    .requestMatchers(AUTH_WHITLIST).permitAll();

                            // Resto de tus reglas antiguas (si quieres mantenerlas)
                            authorizeRequests
                                    .requestMatchers(HttpMethod.GET, "/datos/**")
                                    .hasAnyRole("ADMINISTRADOR", "REFERENCIA_EXPORTAR_DATA", "REFERENCIA_FORMULARIO", "REFERENCIA_MODIFICAR_DATA","REFERENCIA_COMENTARIO_TRIAGE");

                            authorizeRequests
                                    .requestMatchers(HttpMethod.PUT, "/datos/actualizar-ingresos")
                                    .hasAnyRole("ADMINISTRADOR", "REFERENCIA_EXPORTAR_DATA", "REFERENCIA_MODIFICAR_DATA","REFERENCIA_COMENTARIO_TRIAGE");

                            authorizeRequests
                                    .requestMatchers(HttpMethod.PUT, "/datos/observacion-triage/**")
                                    .hasAnyRole("ADMINISTRADOR", "REFERENCIA_COMENTARIO_TRIAGE");

                            authorizeRequests
                                    .requestMatchers(HttpMethod.PUT, "/datos/comentario")
                                    .hasAnyRole("ADMINISTRADOR", "REFERENCIA_MODIFICAR_DATA");

                            authorizeRequests
                                    .requestMatchers(HttpMethod.POST, "/datos/**")
                                    .hasAnyRole("ADMINISTRADOR", "REFERENCIA_FORMULARIO");

                            // CUALQUIER OTRO ENDPOINT REQUIERE AUTENTICACIÓN
                            authorizeRequests.anyRequest().authenticated();
                        }
                )
                // Si quieres que /api/** sea completamente libre mientras pruebas,
                // puedes comentar temporalmente el filtro JWT:
                // .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private static final String[] AUTH_WHITLIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };
}
