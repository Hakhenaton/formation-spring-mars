package fr.sncf.d2d.up2dev.colibri2.colis.persistence;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.Page;
import fr.sncf.d2d.up2dev.colibri2.colis.models.PaginateColisParams;

@Repository
public class ColisRepository {

    private static final RowMapper<Colis> ROW_MAPPER = (resultSet, num) -> Colis.builder()
        .id(UUID.fromString(resultSet.getString("id")))
        .email(resultSet.getString("email"))
        .address(resultSet.getString("address"))
        .trackingCode(resultSet.getString("tracking_code"))
        .details(resultSet.getString("details"))
        .build();
 
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ColisRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Colis colis){
        final var sql = "INSERT INTO colis (id, address, email, details, tracking_code) VALUES (:id, :address, :email, :details, :trackingCode)";
        final var params = new BeanPropertySqlParameterSource(colis);
        final var result = this.jdbcTemplate.update(sql, params);
        assert result == 1;
    }

    public Page<Colis> paginate(PaginateColisParams params){
        final var selectItemsSql = "SELECT * FROM colis ORDER BY id LIMIT :limit OFFSET :offset";
        final var countItemsSql = "SELECT COUNT(*) FROM colis";

        final var items = this.jdbcTemplate.query(
            selectItemsSql, 
            Map.of(
                "limit", params.getItemsPerPage(),
                "offset", params.getPage() * params.getItemsPerPage()
            ), 
            ROW_MAPPER
        );

        final var count = this.jdbcTemplate.queryForObject(
            countItemsSql, 
            Collections.emptyMap(), 
            Long.class
        );

        return new Page<>(items, count);
    }
}
