import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "petHouses.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PetHouseDatabase petHouseDatabase = deserializePetHouseDatabase();

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить новый питомник");
            System.out.println("2. Добавить кота в питомник");
            System.out.println("3. Удалить кота из питомника");
            System.out.println("4. Перенести кота из одного питомника в другой");
            System.out.println("5. Просмотр информации о котах в питомнике");
            System.out.println("6. Просмотр информации о питомнике");
            System.out.println("7. Просмотр информации о конкретном коте");
            System.out.println("8. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addNewPetHouse(scanner, petHouseDatabase);
                case 2 -> addCatToPetHouse(scanner, petHouseDatabase);
                case 3 -> removeCatFromPetHouse(scanner, petHouseDatabase);
                case 4 -> transferCat(scanner, petHouseDatabase);
                case 5 -> getInfoAboutCatsInHousePat(scanner, petHouseDatabase);
                case 6 -> getInfoAboutHousePat(scanner, petHouseDatabase);
                case 7 -> getInfoAboutCat(scanner, petHouseDatabase);
                case 8 -> {
                    serializePetHouseDatabase(petHouseDatabase);
                    System.out.println("Программа завершена");
                    return;
                }
                default -> System.out.println("Пожалуйста, введите число от 1 до 8");
            }
        }
    }

    private static void addNewPetHouse(Scanner scanner, PetHouseDatabase petHouseDatabase) {
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

    private static void addCatToPetHouse(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите название питомника, куда вы хотите добавить кота:");
        String petHouseName = scanner.nextLine();
        PetHouse petHouse = petHouseDatabase.getPetHouse(petHouseName);
        if (petHouse == null) {
            System.out.println("Питомника с названием '" + petHouseName + "' не существует");
            return;
        }

        System.out.println("Введите имя кота:");
        String catName = scanner.nextLine();
        Cat newCat = new Cat(catName);
        petHouse.addCat(newCat);
        System.out.println("Кот " + catName + " добавлен в питомник " + petHouseName);
    }

    private static void removeCatFromPetHouse(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите название питомника, из которого вы хотите удалить кота:");
        String petHouseName = scanner.nextLine();
        PetHouse petHouse = petHouseDatabase.getPetHouse(petHouseName);
        if (petHouse == null) {
            System.out.println("Питомника с названием '" + petHouseName + "' не существует");
            return;
        }

        System.out.println("Введите имя кота, которого вы хотите удалить:");
        String catName = scanner.nextLine();
        Cat catToRemove = null;
        for (Cat cat : petHouse.getCats()) {
            if (cat.getName().equals(catName)) {
                catToRemove = cat;
                break;
            }
        }
        if (catToRemove != null) {
            petHouse.removeCat(catToRemove);
            System.out.println("Кот " + catName + " удален из питомника " + petHouseName);
        } else {
            System.out.println("Кот с таким именем не найден в питомнике " + petHouseName);
        }
    }

    private static void transferCat(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите имя кота:");
        String catName = scanner.nextLine();

        System.out.println("Введите название исходного питомника:");
        String sourcePetHouseName = scanner.nextLine();

        System.out.println("Введите название целевого питомника:");
        String destinationPetHouseName = scanner.nextLine();

        PetHouse sourcePetHouse = petHouseDatabase.getPetHouse(sourcePetHouseName);
        PetHouse destinationPetHouse = petHouseDatabase.getPetHouse(destinationPetHouseName);

        if (sourcePetHouse == null || destinationPetHouse == null) {
            System.out.println("Один из питомников не существует");
            return;
        }

        Cat catToTransfer = sourcePetHouse.findCatByName(catName);
        if (catToTransfer == null) {
            System.out.println("Кот с таким именем не найден в питомнике " + sourcePetHouseName);
            return;
        }

        destinationPetHouse.addCat(catToTransfer);
        sourcePetHouse.removeCat(catToTransfer);
        System.out.println("Кот " + catName + " перенесен из питомника " + sourcePetHouseName + " в питомник " + destinationPetHouseName);
    }

    private static void getInfoAboutCatsInHousePat(Scanner scanner, PetHouseDatabase petHouseDatabase) {
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

    private static void getInfoAboutHousePat(Scanner scanner, PetHouseDatabase petHouseDatabase) {
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

    private static void getInfoAboutCat(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите название питомника, из которого вы хотите просмотреть кота:");
        String petHouseName = scanner.nextLine();
        PetHouse petHouse = petHouseDatabase.getPetHouse(petHouseName);
        if (petHouse == null) {
            System.out.println("Питомника с названием '" + petHouseName + "' не существует");
            return;
        }

        System.out.println("Введите имя кота, информацию о котором вы хотите просмотреть:");
        String catName = scanner.nextLine();
        String catInfo = petHouse.getCatInfo(catName);
        System.out.println(catInfo);
    }

    private static void serializePetHouseDatabase(PetHouseDatabase petHouseDatabase) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(petHouseDatabase);
            System.out.println("Данные сохранены в файл: " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Данные не сохранены" + e.getMessage());
        }
    }

    private static PetHouseDatabase deserializePetHouseDatabase() {
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