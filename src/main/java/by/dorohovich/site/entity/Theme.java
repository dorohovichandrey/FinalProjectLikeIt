package by.dorohovich.site.entity;

/**
 * Created by User on 26.01.2017.
 */
public class Theme extends Entity<Integer> {
    private String name;

    public Theme(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Theme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
