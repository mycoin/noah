package com.breakidea.lotus.shared.param.user;

import java.util.Date;

import com.breakidea.lotus.shared.param.BaseParam;

public class UserParam extends BaseParam {

    private static final long serialVersionUID = -2859563426021667683L;

    private Date gmtCreated;

    private Date gmtModified;

    private Long id;

    private String password;

    private String status;

    private String userName;

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public void setGmtCreated( Date gmtCreated ) {
        this.gmtCreated = gmtCreated;
    }

    public void setGmtModified( Date gmtModified ) {
        this.gmtModified = gmtModified;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
    }
}
