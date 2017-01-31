package by.dorohovich.site.entity;

/**
 * Created by User on 28.12.2016.
 */
public abstract class Entity<T> {
    protected T id;

    public Entity(T id) {
        this.id = id;
    }

    public Entity() {
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity<?> entity = (Entity<?>) o;

        return id.equals(entity.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
