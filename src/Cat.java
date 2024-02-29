import java.io.Serial;
import java.io.Serializable;

public class Cat implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}
