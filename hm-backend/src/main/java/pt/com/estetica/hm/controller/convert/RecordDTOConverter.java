package pt.com.estetica.hm.controller.convert;

import org.springframework.stereotype.Component;
import pt.com.estetica.hm.controller.dto.RecordDTO;
import pt.com.estetica.hm.model.Record;

import java.util.Optional;

@Component
public class RecordDTOConverter {
    public RecordDTO convert(Record record) {
        return Optional.ofNullable(record)
                .map(source -> new RecordDTO(
                        source.getId(),
                        source.getService(),
                        source.getCustomer(),
                        source.getLocation(),
                        source.getDateTime(),
                        source.isDone(),
                        source.isCanceled(),
                        source.getName(),
                        source.getDateOfBirth(),
                        source.getGender(),
                        source.getPhone(),
                        source.getEmail(),
                        source.getAddress(),
                        source.getNotes(),
                        source.getRegistrationDate())
                )
                .orElse(null);
    }

    public Record convert(RecordDTO record) {
        return Optional.ofNullable(record)
                .map(source -> Record.builder()
                        .id(source.id())
                        .service(source.service())
                        .customer(source.customer())
                        .location(source.location())
                        .dateTime(source.dateTime())
                        .done(source.done())
                        .canceled(source.canceled())
                        .name(source.name())
                        .dateOfBirth(source.dateOfBirth())
                        .gender(source.gender())
                        .phone(source.phone())
                        .email(source.email())
                        .address(source.address())
                        .notes(source.notes())
                        .registrationDate(source.registrationDate())
                        .build()
                )
                .orElse(null);
    }
}
