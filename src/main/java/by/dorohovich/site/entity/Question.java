package by.dorohovich.site.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by User on 26.01.2017.
 */
public class Question extends Entity<Integer> {

    private Integer ownerId;
    private Timestamp dateAndTime;
    private String text;
    private Integer themeId;
    private Integer rating;
    private String header;

    public Question(Integer ownerId, String text, Integer themeId, String header) {
        this.ownerId = ownerId;
        this.text = text;
        this.themeId = themeId;
        this.header = header;
        dateAndTime = Timestamp.valueOf(LocalDateTime.now());
        rating = 0;
    }

    public Question(Integer id, Integer ownerId, Timestamp dateAndTime, String text, Integer themeId, Integer rating, String header) {
        super(id);
        this.ownerId = ownerId;
        this.dateAndTime = dateAndTime;
        this.text = text;
        this.themeId = themeId;
        this.rating = rating;
        this.header = header;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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
