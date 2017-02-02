package by.dorohovich.site.command.implcommand.authusercommand;

import by.dorohovich.site.command.AbstractAuthenticatedUserCommand;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.exception.CommandException;
import by.dorohovich.site.exception.ServiceException;
import by.dorohovich.site.exception.ValidationException;
import by.dorohovich.site.service.serviceimpl.AnswerService;
import by.dorohovich.site.utility.MappingManager;
import by.dorohovich.site.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 02.02.2017.
 */
public class CreateAnswerCommand extends AbstractAuthenticatedUserCommand{


    private static final String ANSWER_TEXT_PARAM = "answerText";

    private static final String USER_ATTR = "user";
    private static final String QUESTION_ID_ATTR = "questionIdAttr";

    private static final String KEY_FOR_PAGE = "page.infoSuccess";

    @Override
    protected String doLogic(HttpServletRequest request) throws CommandException {
        try {
            return tryDoLogic(request);
        } catch (ServiceException e){
            throw new CommandException("Exception when trying to create answer", e);
        } catch (ValidationException e){
            throw new CommandException("Exception when trying to create answer", e);
        }
    }

    private String tryDoLogic(HttpServletRequest request) throws ServiceException, ValidationException {
        HttpSession session = request.getSession(true);
        Integer questionId = (Integer) session.getAttribute(QUESTION_ID_ATTR);
        User user = (User)session.getAttribute(USER_ATTR);
        String text = request.getParameter(ANSWER_TEXT_PARAM);
        validate(text);
        AnswerService answerService = new AnswerService();
        answerService.createAnswer(questionId, user.getId(), text);
        String page = MappingManager.getProperty(KEY_FOR_PAGE);
        return page;
    }

    private void validate(String text) throws ValidationException {
        Validator validator = new Validator();
        validator.validateAnswerText(text);
    }
}
