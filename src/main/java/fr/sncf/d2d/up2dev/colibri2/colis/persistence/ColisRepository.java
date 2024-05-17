package fr.sncf.d2d.up2dev.colibri2.colis.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.ColisStatus;
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
        .status(ColisStatus.valueOf(resultSet.getString("status")))
        .build();
 
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ColisRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Colis> findById(UUID id){
        try {
            return Optional.of(this.jdbcTemplate
                .queryForObject(
                    "SELECT * FROM colis WHERE id = :id", 
                    Collections.singletonMap("id", id.toString()),
                    ROW_MAPPER 
                ));
        } catch (EmptyResultDataAccessException empty){
            return Optional.empty();
        }
    }

    // SPEL
    @PreAuthorize("@colisGuard.canCreate(#colis, principal)")
    public void insert(Colis colis){
        final var sql = "INSERT INTO colis (id, address, email, details, tracking_code, delivery_person_username) VALUES (:id, :address, :email, :details, :trackingCode, :deliveryPersonUsername)";
        final var result = this.jdbcTemplate.update(sql, new HashMap<>(){{
            put("id", colis.getId());
            put("address", colis.getAddress());
            put("email", colis.getEmail());
            put("details", colis.getDetails().orElse(null));
            put("trackingCode", colis.getTrackingCode());
            put("deliveryPersonUsername", colis.getDeliveryPersonUsername().orElse(null));
        }});
        assert result == 1;
    }

    public void update(Colis colis){
        final var sql = """
UPDATE colis SET \
address = :address, \
details = :details, \
delivery_person_username = :deliveryPersonUsername, \
email = :email, \
status = :status \
WHERE id = :id
    """;

        final var res = this.jdbcTemplate.update(
            sql,
            new HashMap<>(){{
                put("id", colis.getId());
                put("address", colis.getAddress());
                put("details", colis.getDetails().orElse(null));
                put("deliveryPersonUsername", colis.getDeliveryPersonUsername().orElse(null));
                put("email", colis.getEmail());
                put("status", colis.getStatus().name());
            }} 
        );

        assert res == 1;
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
