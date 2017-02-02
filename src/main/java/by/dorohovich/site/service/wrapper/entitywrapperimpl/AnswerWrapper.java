package by.dorohovich.site.service.wrapper.entitywrapperimpl;

import by.dorohovich.site.entity.Answer;
import by.dorohovich.site.entity.Question;
import by.dorohovich.site.entity.User;
import by.dorohovich.site.service.wrapper.EntityWrapper;

import java.sql.Timestamp;

/**
 * Created by User on 01.02.2017.
 */
public class AnswerWrapper implements EntityWrapper {
    Answer answer;
    Question question;
    User user;

    public AnswerWrapper(Answer answer, Question question, User user) {
        this.answer = answer;
        this.question = question;
        this.user = user;
    }

    public Integer getId() {
        return answer.getId();
    }

    public void setId(Integer id) {
        answer.setId(id);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
        answer.setQuestionId(question.getId());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        answer.setUserId(user.getId());
    }

    public Timestamp getDateAndTime() {
        return answer.getDateAndTime();
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        answer.setDateAndTime(dateAndTime);
    }

    public String getText() {
        return answer.getText();
    }

    public void setText(String text) {
        answer.setText(text);
    }

    public Integer getRating() {
        return answer.getRating();
    }

    public void setRating(Integer rating) {
        answer.setRating(rating);
    }
}
