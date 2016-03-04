package org.ionnic.app.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.ionnic.app.model.Template;
import org.ionnic.app.service.TemplateService;
import org.ionnic.common.util.DBUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author apple
 *
 */
@Service
@Scope("prototype")
public class DefaultTemplateService implements TemplateService {

    @Autowired
    private DataSource dataSource;

    @Override
    public Template query(int id) throws Exception {
        // TODO Auto-generated method stub
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM `TEMPLATE` WHERE ID = ?;");
        ps.setInt(1, id);

        ResultSet result = ps.executeQuery();
        Template template = DBUtils.toBean(result, Template.class);

        ps.close();
        return template;
    }

    @Override
    public boolean add(Template template) throws Exception {
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO `TEMPLATE` (`name`, `content`) VALUES (?, ?);");
        ps.setString(1, template.getName());
        ps.setString(2, template.getContent());

        return ps.execute();
    }

}
