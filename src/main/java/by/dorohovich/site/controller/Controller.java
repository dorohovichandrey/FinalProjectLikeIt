package by.dorohovich.site.controller;


import by.dorohovich.site.command.Command;
import by.dorohovich.site.command.factory.CommandFactory;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.pool.ConnectionPool;
import by.dorohovich.site.utility.MappingManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();


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

        try {
            String page = null;

            CommandFactory factory = new CommandFactory();
            Command command = factory.defineCommand(request);

            page = command.execute(request);

            if (page != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                page = MappingManager.getProperty("path.page.index");

                response.sendRedirect(request.getContextPath() + page);
            }
        } catch (CommandException e) {
            LOGGER.error("Command was not executed correct", e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeAll();
        super.destroy();
    }
}
