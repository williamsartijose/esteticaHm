package pt.com.estetica.hm.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pt.com.estetica.hm.repository.RecordRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Log4j2
public class RecordService {

    private final RecordRepository repository;
    private final RecordCustomRepository customRepository;

    private static final String SAVE_ERROR = "Error during save record.";
    private static final String FIND_ERROR = "Error during find records.";
    private static final String DELETE_ERROR = "Error during delete record.";
    private static final String NOT_FOUND_ERROR = "Record not found.";

    public RecordService(RecordRepository repository,
                         RecordCustomRepository customRepository) {
        this.repository = repository;
        this.customRepository = customRepository;
    }

    // Salvar registro
    public Record save(Record record) {
        try {
            return repository.save(record);
        } catch (Exception ex) {
            log.error(SAVE_ERROR, ex);
            throw new GenericException(SAVE_ERROR);
        }
    }

    // Encontrar registros paginados
    public Page<Record> findPaginated(String name, String phone, String email, LocalDate initialDate, LocalDate finalDate, Boolean canceled, Boolean done, int page, int size) {
        try {
            return customRepository.findPaginated(name, phone, email, initialDate, finalDate, canceled, done, page, size);
        } catch (Exception ex) {
            log.error(FIND_ERROR, ex);
            throw new GenericException(FIND_ERROR);
        }
    }

    // Encontrar registro por ID
    public Optional<Record> findById(Long id) {
        try {
            return repository.findById(id);
        } catch (Exception ex) {
            log.error(NOT_FOUND_ERROR, ex);
            throw new GenericException(NOT_FOUND_ERROR);
        }
    }

    // Excluir registro por ID
    public void deleteById(Long id) {
        try {
            if (!repository.existsById(id)) {
                throw new GenericException(NOT_FOUND_ERROR);
            }
            repository.deleteById(id);
        } catch (Exception ex) {
            log.error(DELETE_ERROR, ex);
            throw new GenericException(DELETE_ERROR);
        }
    }
}