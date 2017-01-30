package by.dorohovich.site.command.implcommand.guestcommand;

import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.command.showquestionslogic.ShowQuestionsByThemeLogic;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 30.01.2017.
 */
public class ShowQuestionsByThemeCommand extends AbstractGuestCommand {

    private ShowQuestionsByThemeLogic showQuestionsByThemeLogic = new ShowQuestionsByThemeLogic();

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return showQuestionsByThemeLogic.tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with service, when trying to showQuestionsByThemeLogic", e);
        }
    }
}
