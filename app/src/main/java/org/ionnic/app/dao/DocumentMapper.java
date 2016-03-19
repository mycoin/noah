package org.ionnic.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.ionnic.app.model.Document;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author apple
 *
 */
@Repository
public class DocumentMapper<T> implements RowMapper<Document> {

    @Override
    public Document mapRow( ResultSet rs, int rowNum ) throws SQLException {
        Document doc = new Document();

        doc.setId(rs.getInt("id"));
        doc.setContent(rs.getString("content"));
        doc.setGuid(rs.getString("guid"));
        doc.setName(rs.getString("name"));

        return doc;
    }
}
