/**
 *
 */
package org.ionnic.app.service;

import java.util.List;

import org.ionnic.app.dao.DocumentDao;
import org.ionnic.app.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author apple
 *
 */
@Service("DocumentService")
public class DocumentService {

    @Autowired
    private DocumentDao dao;

    /**
     * @param document
     * @return
     */
    public boolean save( Document document ) {
        String id = document.getGuid();

        if (!StringUtils.hasText(id)) {
            return dao.insert(document);
        } else {
            return dao.update(id, document);
        }
    }

    /**
     * @param id
     * @return
     */
    public List<Document> query() {
        return dao.select();
    }

    /**
     * @param id
     * @return
     */
    public Document query( String id ) {
        return dao.select(id);
    }

    /**
     * @param id
     * @return
     */
    public boolean delete( String id ) {
        return dao.delete(id);
    }

}
