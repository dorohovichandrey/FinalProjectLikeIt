package by.dorohovich.site.command.implcommand.definer;

import by.dorohovich.site.command.AbstractCommand;
import by.dorohovich.site.command.implcommand.authusercommand.*;
import by.dorohovich.site.command.implcommand.controllercommand.PrepareForCreatingAnswerCommand;
import by.dorohovich.site.command.implcommand.controllercommand.PrepareForCreatingQuestionCommand;
import by.dorohovich.site.command.implcommand.guestcommand.*;

/**
 * Created by User on 26.11.2016.
 */
public enum CommandEnum {

    LANGUAGE{
        {
            command = new ChangeLanguageCommand();
        }
    },

    REGISTRATION{
        {
            command = new RegistrationCommand();
        }
    },

    LOGIN{
        {
            command = new LogInCommand();
        }
    },

    LOGOUT{
        {
            command = new LogOutCommand();
        }
    },

    SHOW_USERS_TOP{
        {
            command = new ShowUsersTopCommand();
        }
    },

    CHANGE_PASSWORD{
        {
            command = new ChangePasswordCommand();
        }
    },

    CHANGE_EMAIL{
        {
            command = new ChangeEmailCommand();
        }
    },

    ASK_QUESTION{
        {
            command = new AskQuestionCommand();
        }
    },

    SHOW_FRESHEST_QUESTIONS{
        {
            command = new ShowFreshestQuestionsCommand();
        }
    },

    SHOW_TOP_RATED_QUESTIONS{
        {
            command = new ShowTopRatedQuestionsCommand();
        }
    },

    SHOW_QUESTIONS_BY_THEME{
        {
            command = new ShowQuestionsByThemeCommand();
        }
    },

    SHOW_MY_QUESTIONS{
        {
            command = new ShowMyQuestionsCommand();
        }
    },

    SHOW_UNANSWERED_QUESTIONS{
        {
            command = new ShowUnansweredQuestionsCommand();
        }
    },

    SHOW_QUESTIONS_ANSWERED_BY_ME{
        {
            command = new ShowQuestionsAnsweredByMeCommand();
        }
    },

    PREPARE_FOR_CREATING_QUESTION{
        {
            command = new PrepareForCreatingQuestionCommand();
        }
    },

    SHOW_QUESTION_PLUS_ANSWERS{
        {
           command = new ShowQuestionPlusAnswersCommand();
        }
    },

    CREATE_ANSWER_COMMAND{
        {
            command = new CreateAnswerCommand();
        }
    },

    PREPARE_FOR_CREATING_ANSWER{
        {
            command = new PrepareForCreatingAnswerCommand();
        }
    },

    CREATE_ANSWER{
        {
            command = new CreateAnswerCommand();
        }
    };


    AbstractCommand command;
    public AbstractCommand getCommand() {
        return command;
    }
}
