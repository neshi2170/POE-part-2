/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickchat.chatapp2;

/**
 *
 * @author RC_Student_lab
 */
class Registration {
   

   

    public boolean isValidUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    public boolean isValidPassword(String password) {
        if (password.length() != 8) return false;

        boolean hasNumber = false, hasUppercase = false, hasSpecialChar = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) hasNumber = true;
            if (Character.isUpperCase(ch)) hasUppercase = true;
            if (!Character.isLetterOrDigit(ch)) hasSpecialChar = true;
        }

        return hasNumber && hasUppercase && hasSpecialChar;
    }

    public boolean isValidPhoneNumber(String phone) {
        return phone.matches("^\\+27\\d{9}$");
    }
}

