package git.jotaassis.sistema_de_cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import git.jotaassis.sistema_de_cadastro.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByEmail(String email);
}