package by.dorohovich.site.command.implcommand.guestcommand;


import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.command.showquestionslogic.ShowUnansweredQuestionsLogic;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 30.01.2017.
 */
public class ShowUnansweredQuestionsCommand extends AbstractGuestCommand {

    private ShowUnansweredQuestionsLogic showUnansweredQuestionsLogic = new ShowUnansweredQuestionsLogic();

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return showUnansweredQuestionsLogic.tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with service, when trying to show freshest questions ", e);
        }
    }
}
