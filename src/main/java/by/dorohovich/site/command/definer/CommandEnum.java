package by.dorohovich.site.command.definer;

import by.dorohovich.site.command.AbstractCommand;
import by.dorohovich.site.command.authusercommand.AskQuestionCommand;
import by.dorohovich.site.command.authusercommand.ChangeEmailCommand;
import by.dorohovich.site.command.authusercommand.ChangePasswordCommand;
import by.dorohovich.site.command.authusercommand.LogOutCommand;
import by.dorohovich.site.command.guestcommand.*;

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
    };

    AbstractCommand command;
    public AbstractCommand getCommand() {
        return command;
    }
}
