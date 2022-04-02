package com.arsud.sdmc_spring_web_project.repository.template;

import com.arsud.sdmc_spring_web_project.entity.Genre;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GenreTemplateRepository implements ITemplateRepository<Genre>{

    private  final JdbcTemplate jdbcTemplate;

    public List<Genre> findByTitleId(
            Long title_id
    ){
        return jdbcTemplate.query(
                "select g.id, g.name, g.valid from title_has_genre thg " +
                        "inner join Title t on thg.title_id = t.id and t.valid = 1 " +
                        "inner join Genre g on thg.GENRE_id = g.id and g.valid = 1 " +
                        "where t.id = ?",
                rowMapper(),
                title_id
        );
    }

    @Override
    public RowMapper<Genre> rowMapper() {
        return (rs, rowNum) -> {
            return Genre.builder()
                    .id(rs.getLong(1))
                    .name(rs.getString(2))
                    .valid(rs.getBoolean(3))
                    .build();
        };
    }
}
