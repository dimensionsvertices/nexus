package li.nexus.nexus.security;

import java.util.Locale;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import li.nexus.nexus.configuration.VendedorCredentialsProperties;
import li.nexus.nexus.enums.UserRole;
import li.nexus.nexus.repository.GerenteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final GerenteRepository gerenteRepository;
    private final VendedorCredentialsProperties vendedorCredentials;

    @Override
    public UserDetails loadUserByUsername(String email) {
        String emailNormalizado = email.trim().toLowerCase(Locale.ROOT);

        if (vendedorCredentials.email() != null
                && !vendedorCredentials.email().isBlank()
                && vendedorCredentials.senhaHash() != null
                && !vendedorCredentials.senhaHash().isBlank()
                && vendedorCredentials.email().equalsIgnoreCase(emailNormalizado)) {
            return User.withUsername(emailNormalizado)
                    .password(vendedorCredentials.senhaHash())
                    .roles(UserRole.VENDEDOR.name())
                    .build();
        }

        return gerenteRepository.findByEmailIgnoreCase(emailNormalizado)
                .filter(gerente -> gerente.getRole() != null)
                .filter(gerente -> gerente.getSenhaHash() != null)
                .map(gerente -> User.withUsername(gerente.getEmail())
                        .password(gerente.getSenhaHash())
                        .roles(gerente.getRole().name())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Credenciais inválidas."));
    }
}
