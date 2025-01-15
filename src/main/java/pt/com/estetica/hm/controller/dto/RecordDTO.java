package pt.com.estetica.hm.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RecordDTO(
    Long id, // Identificador único do cliente
    String name, // Nome completo do cliente
    LocalDate birthDate, // Data de nascimento
    char gender, // Sexo (M/F/O)
    String phone, // Telefone ou WhatsApp
    String email, // E-mail do cliente
    String address, // Endereço completo
    String notes, // Informações adicionais
    LocalDateTime registrationDate // Data e hora do cadastro
) {}
