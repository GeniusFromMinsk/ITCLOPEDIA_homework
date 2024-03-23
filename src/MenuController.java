import java.util.Scanner;

public class MenuController {
    private final Builder builder;
    private final Navigator navigator;

    public MenuController(Builder builder) {
        this.builder = builder;
        this.navigator = new Navigator(builder.getRootMenu());
    }

    public void run() {
        navigator.printMenu();
        while (true) {
            System.out.print("Выберите пункт меню: ");
            int choice = new Scanner(System.in).nextInt();
            navigator.navigate(choice);
            navigator.printMenu();
        }
    }
}
