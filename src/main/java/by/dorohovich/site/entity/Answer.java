package by.dorohovich.site.entity;

import by.dorohovich.site.service.wrapper.Wrappable;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by User on 01.02.2017.
 */
public class Answer extends Entity<Integer> implements Wrappable {

    private Integer questionId;
    private Integer userId;
    private Timestamp dateAndTime;
    private String text;
    private Integer rating;



    public Answer(Integer id, Integer questionId, Integer userId, Timestamp dateAndTime, String text, Integer rating) {
        super(id);
        this.questionId = questionId;
        this.userId = userId;
        this.dateAndTime = dateAndTime;
        this.text = text;
        this.rating = rating;
    }

    public Answer(Integer questionId, Integer userId, String text) {
        this.questionId = questionId;
        this.userId = userId;
        this.dateAndTime = Timestamp.valueOf(LocalDateTime.now());
        this.text = text;
        this.rating = 0;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
