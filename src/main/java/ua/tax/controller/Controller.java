package ua.tax.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReguest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReguest(req, resp);
    }

    private void processReguest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        // определение команды пришедшей из jsp
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        /*
        * вызов реализованного метода execute() и передача параметров классу-щбработчику
        * конкретной команды
        * */

        page = command.execute(request);

        // метод возвращает страницу ответа page = null поэксперементировать

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            //вызов страницы ответа на запрос
            dispatcher.forward(request, response);
        } else {
            //установка страницы с сообщением об ошибки
            page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
