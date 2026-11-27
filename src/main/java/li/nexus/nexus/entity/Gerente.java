package li.nexus.nexus.entity;

import org.hibernate.annotations.SoftDelete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import li.nexus.nexus.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SoftDelete(columnName = "inativo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gerente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, max = 100)
    @Column(unique = true)
    private String nome;
    @Email
    private String email;
    @Pattern(regexp = "\\d{11}")
    private String telefone;
    @Pattern(regexp = "\\d{11}")
    private String cpf;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String senhaHash;
}