package by.dorohovich.site.entity.wrapper;

import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.Theme;
import by.dorohovich.site.entity.User;

import java.sql.Timestamp;

/**
 * Created by User on 28.01.2017.
 */
public class QuestionWrapper {

    Question question;
    User user;
    Theme theme;

    public QuestionWrapper(Question question, User user, Theme theme) {
        this.question = question;
        this.user = user;
        this.theme = theme;
    }

    public Integer getId() {
        return question.getId();
    }

    public void setId(Integer id) {
        question.setId(id);
    }

    public User getOwner() {
        return question.getOwner();
    }

    public void setOwner(User owner) {
        question.setOwner(owner);
    }

    public Timestamp getDateAndTime() {
        return question.getDateAndTime();
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        question.setDateAndTime(dateAndTime);
    }

    public String getText() {
        return question.getText();
    }

    public void setText(String text) {
        question.setText(text);
    }

    public Theme getTheme() {
        return question.getTheme();
    }

    public void setTheme(Theme theme) {
        question.setTheme(theme);
    }

    public Integer getRating() {
        return question.getRating();
    }

    public void setRating(Integer rating) {
        question.setRating(rating);
    }

    public String getHeader() {
        return question.getHeader();
    }

    public void setHeader(String header) {
        question.setHeader(header);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
