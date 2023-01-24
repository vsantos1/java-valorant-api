package com.vsantos1.dtos;

import java.util.Date;

public class TokenDTO {

    private String accessToken;

    private Boolean isValidToken;

    private Date issuedAt;

    private Date expiresAt;


    public TokenDTO(String accessToken, Boolean isValidToken, Date issuedAt, Date expiresAt) {
        this.accessToken = accessToken;
        this.isValidToken = isValidToken;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Boolean getValidToken() {
        return isValidToken;
    }

    public void setValidToken(Boolean validToken) {
        isValidToken = validToken;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
