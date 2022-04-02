package com.arsud.sdmc_spring_web_project.repository.template;

import com.arsud.sdmc_spring_web_project.entity.Company;
import com.arsud.sdmc_spring_web_project.entity.Title;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TitleTemplateRepository implements ITemplateRepository<Title>{

    private final JdbcTemplate jdbcTemplate;

    public Title findByTitleId(
        Long title_id
    ){
        return jdbcTemplate.query(
                "select t.id, t.picture, t.release_date, t.kor_name, t.org_name, t.hook_code, t.etc, t.valid, " +
                        "c.id, c.kor_name, c.org_name, c.valid, " +
                        "s.kor_name " +
                        "from title t " +
                        "inner join company c on c.id = t.COMPANY_id and c.valid = 1 " +
                        "left join series s on s.id\t = t.SERIES_id and s.valid = 1 " +
                        "where t.id = ?",
                rowMapper(),
                title_id
        ).get(0);
    }

    @Override
    public RowMapper<Title> rowMapper() {
        return (rs, rowNum) -> {
            return Title.builder()
                    .id(rs.getLong(1))
                    .picture(rs.getBytes(2))
                    .releaseDate(rs.getDate(3))
                    .korName(rs.getString(4))
                    .orgName(rs.getString(5))
                    .hookCode(rs.getString(6))
                    .etc(rs.getString(7))
                    .valid(rs.getBoolean(8))
                    .company(
                            Company.builder()
                                    .id(rs.getLong(9))
                                    .korName(rs.getString(10))
                                    .orgName(rs.getString(11))
                                    .valid(rs.getBoolean(12))
                                    .build()
                    )
                    .series_name(rs.getString(13))
                    .build();
        };
    }
}
