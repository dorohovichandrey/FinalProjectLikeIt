package by.dorohovich.site.command.implcommand.definer;

import by.dorohovich.site.command.AbstractCommand;
import by.dorohovich.site.command.implcommand.authusercommand.AskQuestionCommand;
import by.dorohovich.site.command.implcommand.authusercommand.ChangeEmailCommand;
import by.dorohovich.site.command.implcommand.authusercommand.ChangePasswordCommand;
import by.dorohovich.site.command.implcommand.authusercommand.LogOutCommand;
import by.dorohovich.site.command.implcommand.guestcommand.*;

/**
 * Created by User on 26.11.2016.
 */
public enum CommandEnum {
    PARSING{
        {
            this.command = new ParsingCommand();
        }
    },

    LANGUAGE{
        {
            this.command = new ChangeLanguageCommand();
        }
    },

    REGISTRATION{
        {
            this.command = new RegistrationCommand();
        }
    },

    LOGIN{
        {
            this.command = new LogInCommand();
        }
    },

    LOGOUT{
        {
            this.command = new LogOutCommand();
        }
    },

    SHOW_USERS_TOP{
        {
            this.command = new ShowUsersTopCommand();
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
    };

    AbstractCommand command;
    public AbstractCommand getCommand() {
        return command;
    }
}
