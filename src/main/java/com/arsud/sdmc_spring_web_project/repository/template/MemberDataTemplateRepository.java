package com.arsud.sdmc_spring_web_project.repository.template;

import com.arsud.sdmc_spring_web_project.entity.Characters;
import com.arsud.sdmc_spring_web_project.entity.Member;
import com.arsud.sdmc_spring_web_project.entity.MemberData;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class MemberDataTemplateRepository implements ITemplateRepository<MemberData> {

    private final JdbcTemplate jdbcTemplate;

    public List<MemberData> findByTitleIdAndMemberId(
            Long title_id,
            String username
    ){
        return jdbcTemplate.query(
                "select if(md.id is null, -1, md.id), md.etc, if(md.progress is null, '미진행', md.progress), " +
                        "md.valid, c.id, c.picture, c.kor_name, c.org_name, c.strategy, c.valid  from characters c " +
                        "inner join title t on t.id = c.TITLE_id and c.TITLE_id = ? " +
                        "left join member_data md on md.CHARACTER_TITLE_id = t.id and c.id = md.CHARACTER_id and md.valid = 1 " +
                        "left join member m on m.id = md.member_id and md.valid = 1 " +
                        "where m.username = ? or m.username is null",
                rowMapper(),
                title_id,
                username
        );
    }

    public void updateMemberData(
            Long memberDataId,
            String progress
    ){
        jdbcTemplate.update(
                "update member_data set progress = ? where id = ?",
                progress,
                memberDataId
        );
    }

    public long memberDataId(
            Long memberId,
            Long characterId
    ){
        return jdbcTemplate.query(
                "select if(max(id) is null, -1, id) from member_data where member_id = ? and character_id = ?",
                rowMapperId(),
                memberId,
                characterId
        ).get(0);
    }

    public void insertMemberData(
        Long memberId,
        Long characterId,
        Long characterTitleId,
        String progress
    ){
        jdbcTemplate.update(
                "insert into member_data values(0, ?, ?, ?, ?,?, 1, ?, ?)",
                memberId,
                characterId,
                characterTitleId,
                "",
                progress,
                new Date(),
                new Date()
        );
    }

    private RowMapper<Long> rowMapperId(){
        return ((rs, rowNum) -> {
            return rs.getLong(1);
        });
    }

    @Override
    public RowMapper<MemberData> rowMapper() {

        return ((rs, rowNum) -> {
            return MemberData.builder()
                    .id(rs.getLong(1))
                    .etc(rs.getString(2))
                    .progress(rs.getString(3))
                    .valid(rs.getBoolean(4))
                    .characters(
                            Characters.builder()
                                    .id(rs.getLong(5))
                                    .picture(rs.getBytes(6))
                                    .korName(rs.getString(7))
                                    .orgName(rs.getString(8))
                                    .strategy(rs.getString(9))
                                    .valid(rs.getBoolean(10))
                                    .build()
                    )
                    .build();
        });

    }
}
