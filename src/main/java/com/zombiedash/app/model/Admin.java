package com.zombiedash.app.model;

public class Admin {
    private static Admin currentInstance = null;

    private Admin() {}

    public static Admin getCurrentInstance() {
        if (null == currentInstance) {
            currentInstance = new Admin();
        }
        return currentInstance;
    }

    public Boolean authenticate(String userName, char[] password) {
        boolean properUserName = userName.equals("admin");
        boolean properPassword = new String(password).equals("Welcome1");
        return (properUserName && properPassword);
    }
}