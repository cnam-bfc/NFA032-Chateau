package net.cnam;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
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
    }
}
