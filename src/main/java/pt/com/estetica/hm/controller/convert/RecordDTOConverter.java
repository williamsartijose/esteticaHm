package pt.com.estetica.hm.controller.convert;

import org.springframework.stereotype.Component;
import pt.com.estetica.hm.controller.dto.RecordDTO;

import java.util.Optional;

@Component
public class RecordDTOConverter {

    // Converte a entidade Record para RecordDTO
    public RecordDTO convert(Record record) {
        return Optional.ofNullable(record)
                .map(source -> new RecordDTO(
                        source.getId(),
                        source.getName(), // Nome do cliente
                        source.getBirthDate(), // Data de nascimento
                        source.getGender(), // Sexo (M/F/O)
                        source.getPhone(), // Telefone
                        source.getEmail(), // Email
                        source.getAddress(), // Endereço
                        source.getNotes(), // Observações
                        source.getRegistrationDate() // Data de cadastro
                ))
                .orElse(null);
    }

    // Converte o RecordDTO para a entidade Record
    public Record convert(RecordDTO recordDTO) {
        return Optional.ofNullable(recordDTO)
                .map(source -> {
                    Record record = new Record();
                    record.setId(source.id()); // ID
                    record.setName(source.name()); // Nome do cliente
                    record.setBirthDate(source.birthDate()); // Data de nascimento
                    record.setGender(source.gender()); // Sexo (M/F/O)
                    record.setPhone(source.phone()); // Telefone
                    record.setEmail(source.email()); // Email
                    record.setAddress(source.address()); // Endereço
                    record.setNotes(source.notes()); // Observações
                    record.setRegistrationDate(source.registrationDate()); // Data de cadastro
                    return record;
                })
                .orElse(null);
    }
}