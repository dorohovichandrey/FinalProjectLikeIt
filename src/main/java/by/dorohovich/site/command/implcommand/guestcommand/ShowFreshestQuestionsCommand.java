package by.dorohovich.site.command.implcommand.guestcommand;

import by.dorohovich.site.command.AbstractGuestCommand;
import by.dorohovich.site.command.showquestions.ShowFreshestQuestionsLogic;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 29.01.2017.
 */
public class ShowFreshestQuestionsCommand extends AbstractGuestCommand {

    private ShowFreshestQuestionsLogic showFreshestQuestionsLogic;

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return showFreshestQuestionsLogic.tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with service, when trying to show freshest questions ", e);
        }
    }
}
