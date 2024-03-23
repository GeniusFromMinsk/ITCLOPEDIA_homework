import java.util.Scanner;

public class CatService {

    public void addCatToPetHouse(Scanner scanner, PetHouseDatabase petHouseDatabase) {
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

    public void removeCatFromPetHouse(Scanner scanner, PetHouseDatabase petHouseDatabase) {
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

    public void transferCat(Scanner scanner, PetHouseDatabase petHouseDatabase) {
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

    public void getInfoAboutCat(Scanner scanner, PetHouseDatabase petHouseDatabase) {
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

    public void editCatInfo(Scanner scanner, PetHouseDatabase petHouseDatabase) {
        System.out.println("Введите название питомника, из которого вы хотите редактировать информацию о коте:");
        String petHouseName = scanner.nextLine();
        PetHouse petHouse = petHouseDatabase.getPetHouse(petHouseName);
        if (petHouse == null) {
            System.out.println("Питомника с названием '" + petHouseName + "' не существует");
            return;
        }

        System.out.println("Введите имя кота, информацию о котором вы хотите редактировать:");
        String catName = scanner.nextLine();
        Cat catToEdit = petHouse.findCatByName(catName);
        if (catToEdit == null) {
            System.out.println("Кот с именем '" + catName + "' не найден в питомнике '" + petHouseName + "'");
            return;
        }

        System.out.println("Введите новое имя для кота:");
        String newCatName = scanner.nextLine();
        catToEdit.setName(newCatName);
        System.out.println("Имя кота успешно изменено на '" + newCatName + "'");
    }

}
