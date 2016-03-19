package org.ionnic.app.model;

import java.io.Serializable;

/**
 * @author apple
 *
 */
public class Document implements Serializable {

    private static final long serialVersionUID = -5791961737566154899L;

    private int id;

    private String name;

    private String content;

    private String guid;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param content the content to set
     */
    public void setContent( String content ) {
        this.content = content;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid( String guid ) {
        this.guid = guid;
    }

    /**
     * @param id the id to set
     */
    public void setId( int id ) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name ) {
        this.name = name;
    }

}
