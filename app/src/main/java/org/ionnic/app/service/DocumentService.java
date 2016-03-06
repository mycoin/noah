/**
 *
 */
package org.ionnic.app.service;

import java.util.List;
import java.util.UUID;

import org.ionnic.app.dao.DocumentDao;
import org.ionnic.app.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author apple
 *
 */
@Service("DocumentService")
@Transactional
public class DocumentService {

    @Autowired
    private DocumentDao dao;

    /**
     * @param document
     * @return
     */
    public Object save(Document document) {

        document.setGuid(UUID.randomUUID() + "");
        dao.save(document);

        return null;
    }

    /**
     * @param id
     * @return
     */
    public List<Document> query(int id) {
        if (id > 0) {
            return dao.query(id);
        } else {
            return dao.query();
        }
    }

}
