package com.estaine.flyxster.dto;

/**
 * Created by AndreyRykhalsky on 12.02.15.
 */
public class PasswordChange {
    private String oldPassword="";
    private String newPassword="";

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
