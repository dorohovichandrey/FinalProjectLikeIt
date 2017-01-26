package by.dorohovich.site.entity;

/**
 * Created by User on 26.01.2017.
 */
public class Theme extends Entity<Integer> {
    private String themeName;

    public Theme(Integer id, String themeName) {
        super(id);
        this.themeName = themeName;
    }

    public Theme(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
