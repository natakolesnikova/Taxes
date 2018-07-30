package ua.tax.controller;

/*
* здесь реалзовать проверку данных из базу, сейчас пока заглуша
* */


public class LoginLogic {
    private final static String ADMIN_LOGIN = "admin";
    private final static String ADMIN_PASS = "qwerty";
    public static boolean checkLogin(String enterLogin, String enterPass) {
        return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASS.equals(enterPass);
    }
}
