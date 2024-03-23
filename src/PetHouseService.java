import java.util.List;
import java.util.Scanner;

public class PetHouseService {

    private final ConfigService configService = new ConfigService();

    public void addNewPetHouse(Scanner scanner, PetHouseDatabase petHouseDatabase) {

        int maxCapacity = configService.getMaxCapacity();
        if (petHouseDatabase.getPetHouseCount() >= maxCapacity) {
            System.out.println("Достигнуто максимальное количество питомников: " + maxCapacity);
            return;
        }

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

    public void editPetHouseInfo(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите название питомника, информацию о котором вы хотите редактировать:");
        String petHouseName = scanner.nextLine();
        PetHouse petHouse = petHouseDatabase.getPetHouse(petHouseName);
        if (petHouse == null) {
            System.out.println("Питомника с названием " + petHouseName + " не существует");
            return;
        }

        System.out.println("Введите новый адрес для питомника " + petHouseName + ":");
        String newAddress = scanner.nextLine();
        petHouse.setAddress(newAddress);

        System.out.println("Введите новый год основания для питомника " + petHouseName + ":");
        int newYear = scanner.nextInt();
        petHouse.setYear(newYear);

        System.out.println("Информация о питомнике успешно изменена");
    }

    public void deletePetHouse(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        if (!configService.isAllowDeletePetHouse()) {
            System.out.println("Удаление питомников запрещено в конфигурации");
            return;
        }

        System.out.println("Введите название питомника, который вы хотите удалить:");
        String petHouseName = scanner.nextLine();
        PetHouse petHouseToRemove = petHouseDatabase.getPetHouse(petHouseName);
        if (petHouseToRemove == null) {
            System.out.println("Питомника с названием " + petHouseName + " не существует");
            return;
        }

        System.out.println("Введите название питомника, в который нужно перенести котов:");
        String destinationPetHouseName = scanner.nextLine();
        PetHouse destinationPetHouse = petHouseDatabase.getPetHouse(destinationPetHouseName);
        if (destinationPetHouse == null) {
            System.out.println("Питомника с названием " + destinationPetHouseName + " не существует");
            return;
        }

        destinationPetHouse.getCats().addAll(petHouseToRemove.getCats());
        petHouseDatabase.removePetHouse(petHouseName);
        System.out.println("Питомник " + petHouseName + " удален, коты перенесены в питомник " + destinationPetHouseName);
    }

}
