package org.terasoluna.tourreservation.app.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class LoginRequest implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String customerCode;

    private String password;

    public LoginRequest() {
        super();
    }

    public LoginRequest(String customerCode, String password) {
        super();
        this.customerCode = customerCode;
        this.password = password;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
