package net.cnam;

public class App {
    public static void main(String[] args) {
        System.out.println("En développement...");
    }
}

//morceau de code test pour les flèches à voir plus tard
/*
        int test = 0;
        while (test != 65) {
            try {
                test = RawConsoleInput.read(true);
                System.out.println((char) test + " : " + test);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (test == 66) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
*/

