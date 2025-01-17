package pt.com.estetica.hm.service;

import pt.com.estetica.hm.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RecordCustomRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RecordCustomRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Page<Record> findPaginated(String service, String customer, String location, LocalDate initialDate, LocalDate finalDate, Boolean canceled, Boolean done, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("dateTime").descending());
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        String query = "SELECT * FROM record WHERE 1=1";

        if (service != null) {
            query += " AND service = :service";
            parameterSource.addValue("service", service);
        }
        if (customer != null) {
            query += " AND customer = :customer";
            parameterSource.addValue("customer", customer);
        }
        if (location != null) {
            query += " AND location = :location";
            parameterSource.addValue("location", location);
        }
        if (canceled != null) {
            query += " AND canceled = :canceled";
            parameterSource.addValue("canceled", canceled);
        }
        if (done != null) {
            query += " AND done = :done";
            parameterSource.addValue("done", done);
        }

        List<Record> records = jdbcTemplate.query(query, parameterSource, new BeanPropertyRowMapper<>(Record.class));
        return new PageImpl<>(records, pageRequest, records.size());
    }
}
