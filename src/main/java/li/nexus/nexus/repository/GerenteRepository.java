package li.nexus.nexus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import li.nexus.nexus.entity.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    boolean existsByCpf(String cpf);

    boolean existsByNome(String nome);

    Gerente findByCpf(String cpf);

    Gerente findByNome(String nome);

    Optional<Gerente> findByEmailIgnoreCase(String email);
}
