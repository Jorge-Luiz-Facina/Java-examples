package com.spi.service.two.client.dto;

public class InfoResponseDTO {
    private String login;
    private String detail;

    public InfoResponseDTO() {

    }

    public InfoResponseDTO(String login, String detail) {
        this.login = login;
        this.detail = detail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
