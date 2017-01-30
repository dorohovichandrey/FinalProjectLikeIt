package by.dorohovich.site.command.implcommand.authusercommand;

import by.dorohovich.site.command.AbstractAuthenticatedUserCommand;
import by.dorohovich.site.command.showquestionslogic.ShowQuestionsAnsweredByMeLogic;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by User on 31.01.2017.
 */
public class ShowQuestionsAnsweredByMeCommand extends AbstractAuthenticatedUserCommand {
    private ShowQuestionsAnsweredByMeLogic logic = new ShowQuestionsAnsweredByMeLogic();

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return logic.tryDoLogic(request);
        } catch (ServiceException e) {
            throw new CommandException("Problem with service, when trying to showMyQuestionsLogic", e);
        }
    }
}
