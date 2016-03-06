package org.ionnic.app.dao;

import java.util.List;

import org.ionnic.app.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private DocumentMapper<Document> mapper;

    /**
     * @param document
     */
    public void save(Document document) {
        if (document == null) {
            return;
        } else {
            String sql = "INSERT INTO `TEMPLATE` (`id`, `guid`, `name`, `content`) VALUES (null, ?, ?, ?);";
            template.update(sql, document.getGuid(), document.getName(), document.getContent());
        }
    }

    /**
     * @param id
     * @return
     */
    public List<Document> query(int id) {
        String sql = "SELECT * FROM `TEMPLATE` WHERE `ID` = ?";
        return template.query(sql, mapper, id);
    }

    /**
     * @return
     */
    public List<Document> query() {
        String sql = "SELECT * FROM `TEMPLATE`;";
        return template.query(sql, mapper);
    }
}
