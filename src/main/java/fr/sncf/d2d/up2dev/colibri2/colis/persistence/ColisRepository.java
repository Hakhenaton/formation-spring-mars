package fr.sncf.d2d.up2dev.colibri2.colis.persistence;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;

@Repository
public class ColisRepository {
 
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
}
