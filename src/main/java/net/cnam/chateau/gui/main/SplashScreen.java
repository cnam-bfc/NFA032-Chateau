package net.cnam.chateau.gui.main;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;

public class SplashScreen extends CFrame implements DisplayableComponent {
    public SplashScreen() {
        super(0, 0);

        CLabel label = new CLabel("" +
                " ███████╗███████╗ ██████╗███╗   ██╗ █████╗ ███╗   ███╗\n" +
                " ██╔════╝██╔════╝██╔════╝████╗  ██║██╔══██╗████╗ ████║\n" +
                " █████╗  ███████╗██║     ██╔██╗ ██║███████║██╔████╔██║\n" +
                " ██╔══╝  ╚════██║██║     ██║╚██╗██║██╔══██║██║╚██╔╝██║\n" +
                " ███████╗███████║╚██████╗██║ ╚████║██║  ██║██║ ╚═╝ ██║\n" +
                " ╚══════╝╚══════╝ ╚═════╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝     ╚═╝\n" +
                "                                                     \n" +
                "          ██████╗  █████╗ ███╗   ███╗███████╗         \n" +
                "         ██╔════╝ ██╔══██╗████╗ ████║██╔════╝         \n" +
                "         ██║  ███╗███████║██╔████╔██║█████╗           \n" +
                "         ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝           \n" +
                "         ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗         \n" +
                "          ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝         ");
        label.getColors().add(CColor.YELLOW);

        CLabel startLabel = new CLabel("Appuyez sur une touche pour démarrer...");
        startLabel.getColors().add(CColor.BLINKING);

        this.getContentPane().getComponents().add(label);
        this.getContentPane().getComponents().add(startLabel);
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    @Override
    public boolean isInLoopingMode() {
        return false;
    }

    @Override
    public void stopLoopingMode() {
    }
}
