package com.scrip.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF común en APIs REST stateless
                .authorizeExchange(exchanges -> exchanges
                        // Permitir libre acceso a las rutas de registro, login y descubrimiento
                        .pathMatchers("/api/v1/users/register", "/login**", "/oauth2/**", "/.well-known/**").permitAll()
                        // CUALQUIER otra petición al Gateway requerirá obligatoriamente el JWT
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {})); // Activar validación de JWTs

        return http.build();
    }
}
