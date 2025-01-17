package pt.com.estetica.hm.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pt.com.estetica.hm.controller.convert.RecordDTOConverter;
import pt.com.estetica.hm.controller.dto.RecordDTO;
import pt.com.estetica.hm.model.Record;
import pt.com.estetica.hm.service.RecordService;

import java.time.LocalDate;
@RestController
@RequestMapping("/record")
@CrossOrigin("*")
public class RecordController {

    private final RecordService recordService;
    private final RecordDTOConverter converter;

    public RecordController(RecordService recordService, RecordDTOConverter converter) {
        this.recordService = recordService;
        this.converter = converter;
    }

    @PostMapping
    public RecordDTO save(@RequestBody @Valid RecordDTO record) {
        return converter.convert(recordService.save(converter.convert(record)));
    }

    @GetMapping
    public Page<RecordDTO> findPaginated(
            @RequestParam(required = false) String service,
            @RequestParam(required = false) String customer,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate initialDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate finalDate,
            @RequestParam(required = false) Boolean done,
            @RequestParam(required = false) Boolean canceled,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return recordService.findPaginated(service, customer, location, initialDate, finalDate, canceled, done, page, size)
                .map(converter::convert);
    }
}
