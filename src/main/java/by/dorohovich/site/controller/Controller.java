package by.dorohovich.site.controller;


import by.dorohovich.site.command.AbstractCommand;
import by.dorohovich.site.command.implcommand.definer.CommandDefiner;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.connectionpool.ConnectionPool;
import by.dorohovich.site.exception.LikeItControllerException;
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
            tryProcessRequest(request, response);
        } catch (CommandException e) {
            LOGGER.error("Command failed", e);
            throw new LikeItControllerException("Problem in LikeIt controller",e);
        }
    }

    private void tryProcessRequest(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        String page = null;
        CommandDefiner commandDefiner = new CommandDefiner();
        AbstractCommand command = commandDefiner.define(request);
        page = command.execute(request);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closeAll();
        super.destroy();
    }
}
