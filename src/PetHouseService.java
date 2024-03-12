import java.util.List;
import java.util.Scanner;

public class PetHouseService {

    public void addNewPetHouse(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите название нового питомника:");
        String petHouseName = scanner.nextLine();

        System.out.println("Введите адрес нового питомника:");
        String address = scanner.nextLine();

        System.out.println("Введите год основания нового питомника:");
        int year = scanner.nextInt();
        scanner.nextLine();

        PetHouse newPetHouse = new PetHouse(address, year);
        petHouseDatabase.addPetHouse(petHouseName, newPetHouse);
        System.out.println("Новый питомник добавлен: " + petHouseName);
    }

    public void getInfoAboutCatsInHousePat(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите название питомника, информацию о котах в котором вы хотите просмотреть:");
        String petHouseName = scanner.nextLine();
        PetHouse petHouse = petHouseDatabase.getPetHouse(petHouseName);
        if (petHouse == null) {
            System.out.println("Питомника с названием '" + petHouseName + "' не существует");
            return;
        }

        List<Cat> cats = petHouse.getCats();
        if (cats.isEmpty()) {
            System.out.println("В питомнике " + petHouseName + " нет котов");
        } else {
            System.out.println("Список котов в питомнике " + petHouseName + ":");
            for (Cat cat : cats) {
                System.out.println(cat);
            }
        }
    }

    public void getInfoAboutHousePat(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите название питомника, информацию о котором вы хотите просмотреть:");
        String petHouseName = scanner.nextLine();
        PetHouse petHouse = petHouseDatabase.getPetHouse(petHouseName);
        if (petHouse == null) {
            System.out.println("Питомника с названием '" + petHouseName + "' не существует");
        } else {
            System.out.println("Информация о питомнике '" + petHouseName + "':");
            System.out.println("Адрес: " + petHouse.getAddress());
            System.out.println("Год основания: " + petHouse.getFoundationYear());
        }
    }

}
