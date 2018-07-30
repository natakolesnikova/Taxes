package ua.tax.controller;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        //извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        //
        if (LoginLogic.checkLogin(login, pass)) {
            request.setAttribute("user", login);
            //щпределение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
