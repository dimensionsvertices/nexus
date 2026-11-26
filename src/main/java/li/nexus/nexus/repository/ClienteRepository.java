package li.nexus.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import li.nexus.nexus.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCnpj(String cnpj);

    boolean existsByNome(String nome);

    Cliente findByCnpj(String cnpj);

    Cliente findByNome(String nomeCliente);
}
