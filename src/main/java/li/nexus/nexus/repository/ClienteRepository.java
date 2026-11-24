package li.nexus.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import li.nexus.nexus.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);

    boolean existsByCnpj(String cnpj);

    Cliente findByCnpj(String cnpj);
}
