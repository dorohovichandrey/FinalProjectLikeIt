package by.dorohovich.site.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by User on 26.01.2017.
 */
public class Question extends Entity<Integer> {

    private User owner;
    private Timestamp dateAndTime;
    private String text;
    private Theme theme;
    private Integer rating;
    private String header;

    public Question(User owner, String text, Theme theme, String header) {
        this.owner = owner;
        this.text = text;
        this.theme = theme;
        this.header = header;
        dateAndTime = Timestamp.valueOf(LocalDateTime.now());
        rating = 0;
    }

    public Question(Integer id, User owner, Timestamp dateAndTime, String text, Theme theme, Integer rating, String header) {
        super(id);
        this.owner = owner;
        this.dateAndTime = dateAndTime;
        this.text = text;
        this.theme = theme;
        this.rating = rating;
        this.header = header;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
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
