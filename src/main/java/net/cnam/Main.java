package net.cnam;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App();
        App.setInstance(app);
        app.start();
    }
}
