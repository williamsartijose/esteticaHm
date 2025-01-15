package pt.com.estetica.hm.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pt.com.estetica.hm.controller.dto.RecordDTO;
import pt.com.estetica.hm.service.RecordService;

import java.time.LocalDate;

@RestController
@RequestMapping("/record")
@CrossOrigin("*")
public class RecordController {

    private final RecordService recordService;
    private final RecordDTOConverter converter;

    public RecordController(RecordService recordService,
                            RecordDTOConverter converter) {
        this.recordService = recordService;
        this.converter = converter;
    }

    // Endpoint para salvar um registro
    @PostMapping
    public RecordDTO save(@RequestBody @Valid RecordDTO recordDTO) {
        return converter.convert(recordService.save(converter.convert(recordDTO)));
    }

    // Endpoint para buscar registros paginados
    @GetMapping
    public Page<RecordDTO> findPaginated(
            @RequestParam(required = false) String name, // Nome do cliente
            @RequestParam(required = false) String phone, // Telefone do cliente
            @RequestParam(required = false) String email, // Email do cliente
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate, // Data inicial
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate, // Data final
            @RequestParam(required = false) Boolean done, // Status concluído
            @RequestParam(required = false) Boolean canceled, // Status cancelado
            @RequestParam(defaultValue = "0") int page, // Número da página
            @RequestParam(defaultValue = "10") int size // Tamanho da página
    ) {
        return recordService.findPaginated(name, phone, email, initialDate, finalDate, canceled, done, page, size)
                .map(converter::convert);
    }

    // Endpoint para buscar registro por ID
    @GetMapping("/{id}")
    public RecordDTO findById(@PathVariable Long id) {
        return recordService.findById(id)
                .map(converter::convert)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    // Endpoint para deletar registro por ID
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        recordService.deleteById(id);
    }
}
