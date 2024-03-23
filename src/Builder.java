import java.util.Scanner;

public class Builder {
    private final Menu rootMenu;
    private final PetHouseDatabase petHouseDatabase;
    public Builder() {
        this.petHouseDatabase = deserializePetHouseDatabase();
        this.rootMenu = buildMenu();
    }

    private PetHouseDatabase deserializePetHouseDatabase() {
        PetHouseDatabaseService petHouseDatabaseService = new PetHouseDatabaseService();
        return petHouseDatabaseService.deserializePetHouseDatabase();
    }
    public Menu buildMenu() {
        Menu mainMenu = new Menu("Главное меню");
        Menu petHouseMenu = new Menu("Меню питомников");
        Scanner scanner = new Scanner(System.in);
        mainMenu.addMenuItem(new MenuItem("Меню питомников", null, petHouseMenu));

        petHouseMenu.addMenuItem(new MenuItem(MenuAction.ADD_PET_HOUSE.getDescription(), () -> new PetHouseService().addNewPetHouse(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.ADD_CAT_TO_HOUSE.getDescription(), () -> new CatService().addCatToPetHouse(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.REMOVE_CAT_FROM_HOUSE.getDescription(), () -> new CatService().removeCatFromPetHouse(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.TRANSFER_CAT.getDescription(), () -> new CatService().transferCat(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.VIEW_CATS_INFO.getDescription(), () -> new PetHouseService().getInfoAboutCatsInHousePat(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.VIEW_HOUSE_INFO.getDescription(), () -> new PetHouseService().getInfoAboutHousePat(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.VIEW_CAT_INFO.getDescription(), () -> new CatService().getInfoAboutCat(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.EDIT_CAT_INFO.getDescription(), () -> new CatService().editCatInfo(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.EDIT_HOUSE_INFO.getDescription(), () -> new PetHouseService().editPetHouseInfo(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.DELETE_PET_HOUSE.getDescription(), () -> new PetHouseService().deletePetHouse(scanner, petHouseDatabase), null));
        petHouseMenu.addMenuItem(new MenuItem(MenuAction.EXIT.getDescription(), () -> {
            new PetHouseDatabaseService().serializePetHouseDatabase(petHouseDatabase);
            System.exit(0);
        }));
        return mainMenu;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
