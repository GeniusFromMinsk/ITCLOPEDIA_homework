import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PetHouseDatabaseService petHouseDatabaseService = new PetHouseDatabaseService();
        PetHouseDatabase petHouseDatabase = petHouseDatabaseService.deserializePetHouseDatabase();
        CatService catService = new CatService();
        PetHouseService petHouseService = new PetHouseService();

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
                case 1 -> petHouseService.addNewPetHouse(scanner, petHouseDatabase);
                case 2 -> catService.addCatToPetHouse(scanner, petHouseDatabase);
                case 3 -> catService.removeCatFromPetHouse(scanner, petHouseDatabase);
                case 4 -> catService.transferCat(scanner, petHouseDatabase);
                case 5 -> petHouseService.getInfoAboutCatsInHousePat(scanner, petHouseDatabase);
                case 6 -> petHouseService.getInfoAboutHousePat(scanner, petHouseDatabase);
                case 7 -> catService.getInfoAboutCat(scanner, petHouseDatabase);
                case 8 -> {
                    petHouseDatabaseService.serializePetHouseDatabase(petHouseDatabase);
                    System.out.println("Программа завершена");
                    return;
                }
                default -> System.out.println("Пожалуйста, введите число от 1 до 8");
            }
        }
    }

}