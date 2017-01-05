package by.dorohovich.site.entity;

/**
 * Created by User on 28.12.2016.
 */
public enum Role {
    USER(0), ADMIN(1);

    private Integer bit;

    Role(Integer bit) {
        this.bit = bit;
    }

    public static Role getRole(int bit) {
        return  (bit == 1) ? ADMIN : USER;
    }

    public Integer getBit() {
        return bit;
    }

}
