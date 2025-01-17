package pt.com.estetica.hm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String service; // Nome do serviço
    private String customer; // Nome do cliente
    private String location; // Local do atendimento
    private LocalDateTime dateTime; // Data e hora do atendimento
    private boolean done; // Status de conclusão
    private boolean canceled; // Status de cancelamento

    private String name; // Nome completo do cliente
    private LocalDate dateOfBirth; // Data de nascimento
    private String gender; // Sexo (M/F/O)
    private String phone; // Telefone ou WhatsApp
    private String email; // E-mail do cliente
    private String address; // Endereço completo
    private String notes; // Informações adicionais
    private LocalDateTime registrationDate; // Data e hora do cadastro
}
