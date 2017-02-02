package by.dorohovich.site.validator;

import by.dorohovich.site.exception.ValidationException;

import java.util.regex.Pattern;

/**
 * Created by User on 02.02.2017.
 */
public class Validator {

    public void validateLogin(String login) throws ValidationException{
        if(!login.matches("[A-z0-9_]{4,29}")){
            throw new ValidationException("Login is not valid");
        }
    }

    public void validateEmail(String email) throws ValidationException{
        Pattern p = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+");
        if(!p.matcher(email).matches()){
            throw new ValidationException("Email is not valid");
        }
    }

    public void validatePassword(String pass) throws ValidationException{
        if(pass.length()<6 ||pass.length()>25 || !pass.matches(".*[0-9].*") || !pass.matches(".*[a-z].*") || !pass.matches(".*[A-Z].*")){
            throw new ValidationException("Password is not valid");
        }
    }

    public void validatePassConfirm(String pass, String passConfirm) throws ValidationException{
        if(!pass.equals(passConfirm)){
            throw new ValidationException("Password confirmationis failed");
        }
    }

    public void validateQuestionHeader(String header) throws ValidationException{
        if(header.length()<10 || header.length() > 100 ){
            throw new ValidationException("Text is not valid");
        }
    }

    public void validateQuestionText(String text) throws ValidationException{
        if(text.length()<10 || text.length() > 1000 ){
            throw new ValidationException("Text is not valid");
        }
    }

    public void validateAnswerText(String text) throws ValidationException{
        if(text.length()<10 || text.length() > 1000 ){
            throw new ValidationException("Text is not valid");
        }
    }

}
