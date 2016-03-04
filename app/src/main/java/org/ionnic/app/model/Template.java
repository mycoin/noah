package org.ionnic.app.model;

/**
 * @author apple
 *
 */
public class Template {

    private int id;

    private String content;

    private String name;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}