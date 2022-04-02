package com.arsud.sdmc_spring_web_project.repository.template;

import org.springframework.jdbc.core.RowMapper;

public interface ITemplateRepository<T> {

    RowMapper<T> rowMapper();

}
