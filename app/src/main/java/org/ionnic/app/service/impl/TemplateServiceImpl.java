package org.ionnic.app.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.sql.DataSource;

import org.ionnic.app.model.Template;
import org.ionnic.app.service.TemplateService;
import org.ionnic.common.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author apple
 *
 */
@Repository
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Template query(int id) throws Exception {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `TEMPLATE` WHERE ID = ?;");
        DBUtils.format(ps, id);
        ResultSet result = ps.executeQuery();
        Template template = DBUtils.toBean(result, Template.class);

        ps.close();
        conn.close();
        return template;
    }

    @Override
    public int add(Template template) throws Exception {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO `TEMPLATE` (`name`, `content`, `guid`) VALUES (?, ?, ?);");
        DBUtils.format(ps, template.getName(), template.getContent(), UUID.randomUUID() + "");

        ps.execute();

        ps.close();
        conn.close();

        return 1;
    }
}
