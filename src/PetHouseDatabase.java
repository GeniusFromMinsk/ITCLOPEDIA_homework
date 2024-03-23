import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetHouseDatabase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Map<String, PetHouse> petHouses;

    public PetHouseDatabase() {
        petHouses = new HashMap<>();
    }

    public void addPetHouse(String name, PetHouse petHouse) {
        petHouses.put(name, petHouse);
    }

    public PetHouse getPetHouse(String name) {
        return petHouses.get(name);
    }

    public void removePetHouse(String name) {
        petHouses.remove(name);
    }
    public int getPetHouseCount() {
        return petHouses.size();
    }

}
