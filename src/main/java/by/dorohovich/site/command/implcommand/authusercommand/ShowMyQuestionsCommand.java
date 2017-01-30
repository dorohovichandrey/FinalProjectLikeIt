package by.dorohovich.site.command.implcommand.authusercommand;

import by.dorohovich.site.command.AbstractAuthenticatedUserCommand;
import by.dorohovich.site.command.showquestions.ShowMyQuestionsLogic;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 30.01.2017.
 */
public class ShowMyQuestionsCommand extends AbstractAuthenticatedUserCommand {

    private ShowMyQuestionsLogic showMyQuestionsLogic = new ShowMyQuestionsLogic();

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return showMyQuestionsLogic.tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with service, when trying to showMyQuestionsLogic", e);
        }
    }
}
