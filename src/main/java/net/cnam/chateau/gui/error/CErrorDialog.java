package net.cnam.chateau.gui.error;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.HorizontalAlignment;
import net.cnam.chateau.utils.StringUtils;

public class CErrorDialog extends CFrame implements DisplayableComponent {

    public CErrorDialog(Type type, String text) {
        this(type, StringUtils.convertStringToStringArray(text));
    }

    public CErrorDialog(Type type, String[] text) {
        super(AppSettings.CONSOLE_MIN_LENGTH, AppSettings.CONSOLE_MIN_HEIGHT);

        CLabel title = new CLabel("ERREUR");
        title.getColors().add(CColor.BOLD);
        title.getColors().add(CColor.RED);
        this.setTitle(title);

        CLabel introMessage = new CLabel(type.getTitle(), this.getLength() - 2);
        introMessage.getColors().add(CColor.BOLD);
        introMessage.getColors().add(CColor.BLINKING);
        introMessage.getColors().add(CColor.RED);
        this.getContentPane().getComponents().add(introMessage);

        CLabel errorMessage = new CLabel(text, this.getLength() - 2);
        if (type.equals(Type.EXCEPTION)) {
            errorMessage.setHorizontalAlignment(HorizontalAlignment.LEFT);
        } else {
            errorMessage.getColors().add(CColor.BLINKING);
        }
        errorMessage.getColors().add(CColor.RED);
        this.getContentPane().getComponents().add(errorMessage);

        CLabel exitMessage = new CLabel(type.getFooter(), this.getLength() - 2);
        exitMessage.getColors().add(CColor.BLINKING);
        this.getContentPane().getComponents().add(exitMessage);
    }

    @Override
    public boolean isInLoopingMode() {
        return false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return false;
    }

    public enum Type {
        WARNING("\u26A0 Attention \u26A0", "Appuyez sur une touche pour continuer..."),
        EXCEPTION("\u26A0 Une erreur fatale est survenue \u26A0", "Appuyez sur une touche pour quitter...");

        private final String title;
        private final String footer;

        private Type(String text, String footer) {
            this.title = text;
            this.footer = footer;
        }

        public String getTitle() {
            return title;
        }

        public String getFooter() {
            return footer;
        }
    }
}
