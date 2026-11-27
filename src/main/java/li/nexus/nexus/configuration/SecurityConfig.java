package li.nexus.nexus.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableConfigurationProperties(VendedorCredentialsProperties.class)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SecurityContextRepository securityContextRepository) throws Exception {
        http
                .cors(cors -> {
                })
                .csrf(csrf -> {
                })
                .securityContext(context -> context
                        .securityContextRepository(securityContextRepository))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(
                                SessionCreationPolicy.IF_REQUIRED))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/login",
                                "/auth/csrf",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api-docs/**")
                        .permitAll()
                        .requestMatchers("/auth/logout")
                        .authenticated()
                        .requestMatchers("/gerentes/**", "/solicitacoes/**")
                        .hasRole("GERENTE")
                        .requestMatchers(HttpMethod.DELETE,
                                "/clientes/**",
                                "/materiais/**",
                                "/pedidos/**",
                                "/pagamentos/**",
                                "/itens/**")
                        .hasRole("GERENTE")
                        .requestMatchers(HttpMethod.GET, "/materiais/**")
                        .hasAnyRole("GERENTE", "VENDEDOR")
                        .requestMatchers(
                                "/clientes/**",
                                "/pedidos/**",
                                "/pagamentos/**",
                                "/itens/**")
                        .hasAnyRole("GERENTE", "VENDEDOR")
                        .requestMatchers("/materiais/**")
                        .hasRole("GERENTE")
                        .anyRequest()
                        .hasRole("GERENTE"));

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}
