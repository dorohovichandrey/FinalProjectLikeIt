package by.dorohovich.site.command.factory;

import by.dorohovich.site.command.Command;
import by.dorohovich.site.command.impl.*;

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
    };

    Command command;
    public Command getCommand() {
        return command;
    }
}
