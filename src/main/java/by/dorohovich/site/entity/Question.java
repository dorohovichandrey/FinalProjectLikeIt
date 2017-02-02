package by.dorohovich.site.entity;

import by.dorohovich.site.service.wrapper.Wrappable;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by User on 26.01.2017.
 */
public class Question extends Entity<Integer> implements Wrappable {

    private Integer userId;
    private Timestamp dateAndTime;
    private String text;
    private Integer themeId;
    private Integer rating;
    private String header;

    public Question(Integer userId, String text, Integer themeId, String header) {
        this.userId = userId;
        this.text = text;
        this.themeId = themeId;
        this.header = header;
        dateAndTime = Timestamp.valueOf(LocalDateTime.now());
        rating = 0;
    }

    public Question(Integer id, Integer userId, Timestamp dateAndTime, String text, Integer themeId, Integer rating, String header) {
        super(id);
        this.userId = userId;
        this.dateAndTime = dateAndTime;
        this.text = text;
        this.themeId = themeId;
        this.rating = rating;
        this.header = header;
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

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

}
