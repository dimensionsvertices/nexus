package li.nexus.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import li.nexus.nexus.entity.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long> {
    boolean existsByCpf(String cpf);

    Gerente findByCpf(String cpf);

    Gerente findByNome(String nome);
}
