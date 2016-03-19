package org.ionnic.app.dao;

import java.util.List;
import java.util.UUID;

import org.ionnic.app.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class DocumentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DocumentMapper<Document> mapper;

    /**
     * @param id
     * @return
     */
    public boolean delete( String id ) {
        String sql = "DELETE FROM `TEMPLATE` WHERE `guid` = ?;";
        int count = jdbcTemplate.update(sql, id);
        return count == 1;
    }

    /**
     * @param document
     * @return
     */
    public boolean insert( Document document ) {
        String sql;
        if (document == null) {
            return false;
        } else {
            String guid = UUID.randomUUID().toString();
            sql = "INSERT INTO `TEMPLATE` (`guid`, `name`, `content`) VALUES (?, ?, ?);";
            int count = jdbcTemplate.update(sql, guid, document.getName(), document.getContent());
            return count == 1;
        }
    }

    /**
     * @return
     */
    public List<Document> select() {
        return select("1 = ?", 1);
    }

    /**
     * @param guid
     * @return
     */
    public Document select( String id ) {
        List<Document> result = select("`id` = ? OR `guid` = ?", id, id);
        if (result == null || result.size() < 1) {
            return null;
        } else {
            return result.get(0);
        }
    }

    /**
     * @param where
     * @param values
     * @return
     */
    private List<Document> select( String where, Object... values ) {
        String sql = "SELECT * FROM `TEMPLATE` WHERE " + where + ";";
        return jdbcTemplate.query(sql, mapper, values);
    }

    /**
     * @param guid
     * @param document
     */
    public boolean update( String guid, Document document ) {
        if (document == null || !StringUtils.hasText(guid)) {
            return false;
        } else {
            String sql = "UPDATE `TEMPLATE` SET `name` = ?, `content` = ? WHERE `guid` = ?;";
            int count = jdbcTemplate.update(sql, document.getName(), document.getContent(), guid);
            return count == 1;
        }
    }
}
