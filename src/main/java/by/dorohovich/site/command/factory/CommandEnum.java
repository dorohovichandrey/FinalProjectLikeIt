package by.dorohovich.site.command.factory;

import by.dorohovich.site.command.ActionCommand;
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
    };

    ActionCommand command;
    public ActionCommand getCommand() {
        return command;
    }
}
