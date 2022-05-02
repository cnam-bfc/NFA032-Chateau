package net.cnam;

import net.cnam.console.Console;

public class App {

    public static void main(String[] args) {
        System.out.println("En développement...");

        Console console = new Console();
        console.adjustSize();
        // console.debugKeys();
    }

    // Méthode pour effacer la console
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
