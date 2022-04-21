package net.cnam;

public class App {
    public static void main(String[] args) {
        System.out.println("En développement...");
    }

    // Méthode pour effacer la console
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
