import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PetHouse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Cat> cats;
    private String address;
    private int year;

    public PetHouse(String address, int foundationYear) {
        this.address = address;
        this.year = foundationYear;
        cats = new ArrayList<>();
    }

    public void addCat(Cat cat) {
        cats.add(cat);
    }

    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    public List<Cat> getCats() {
        return cats;
    }

    public String getAddress() {
        return address;
    }

    public int getFoundationYear() {
        return year;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Cat findCatByName(String catName) {
        for (Cat cat : cats) {
            if (cat.getName().equals(catName)) {
                return cat;
            }
        }
        return null;
    }

    public String getCatInfo(String catName) {
        for (Cat cat : cats) {
            if (cat.getName().equals(catName)) {
                return cat.toString();
            }
        }
        return "Кот с именем '" + catName + "' не найден в этом питомнике";
    }

    @Override
    public String toString() {
        return "PetHouse{" +
                "cats=" + cats +
                ", address='" + address + '\'' +
                ", year=" + year +
                '}';
    }

}
