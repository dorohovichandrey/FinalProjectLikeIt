package by.dorohovich.site.command.implcommand.definer;

import by.dorohovich.site.command.AbstractCommand;
import by.dorohovich.site.command.implcommand.authusercommand.*;
import by.dorohovich.site.command.implcommand.guestcommand.*;

/**
 * Created by User on 26.11.2016.
 */
public enum CommandEnum {
    PARSING{
        {
            command = new ParsingCommand();
        }
    },

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
    };


    AbstractCommand command;
    public AbstractCommand getCommand() {
        return command;
    }
}
