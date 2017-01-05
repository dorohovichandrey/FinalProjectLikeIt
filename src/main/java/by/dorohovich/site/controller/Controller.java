package by.dorohovich.site.controller;


import by.dorohovich.site.command.ActionCommand;
import by.dorohovich.site.command.ActionFactory;
import by.dorohovich.site.pool.ConnectionPool;
import by.dorohovich.site.utility.ConfigurationManager;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String page = null;

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);

            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.index");

            response.sendRedirect(request.getContextPath() + page);
        }

    }

    @Override
    public void destroy() {
        super.destroy();

        ConnectionPool.getInstance().closeAll();
    }
}
