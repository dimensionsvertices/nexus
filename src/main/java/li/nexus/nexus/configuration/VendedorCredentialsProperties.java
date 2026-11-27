package li.nexus.nexus.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security.vendedor")
public record VendedorCredentialsProperties(
        String email,
        String senhaHash) {
}
