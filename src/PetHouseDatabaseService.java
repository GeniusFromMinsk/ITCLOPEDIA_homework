import java.io.*;

public class PetHouseDatabaseService {

    private static final String FILE_NAME = "petHouses.txt";

    public void serializePetHouseDatabase(PetHouseDatabase petHouseDatabase) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(petHouseDatabase);
            System.out.println("Данные сохранены в файл: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Данные не сохранены" + e.getMessage());
        }
    }

    public PetHouseDatabase deserializePetHouseDatabase() {
        PetHouseDatabase petHouseDatabase;
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            petHouseDatabase = (PetHouseDatabase) ois.readObject();
            System.out.println("Данные загружены из файла: " + FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Файл не найден, создан новый файл");
            petHouseDatabase = new PetHouseDatabase();
        }
        return petHouseDatabase;
    }

}
