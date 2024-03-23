import java.util.List;

public class Navigator {
    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        System.out.println(currentMenu.getName());
        List<MenuItem> menuItems = currentMenu.getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            System.out.println((i + 1) + ". " + menuItem.getTitle());
        }
    }

    public void navigate(int choice) {
        List<MenuItem> menuItems = currentMenu.getMenuItems();
        if (choice >= 1 && choice <= menuItems.size()) {
            MenuItem menuItem = menuItems.get(choice - 1);
            menuItem.doAction();
            Menu nextMenu = menuItem.getNextMenu();
            if (nextMenu != null) {
                currentMenu = nextMenu;
            }
        } else {
            System.out.println("Неправильный выбор, повторите попытку");
        }
    }
}
