package net.cnam.chateau.gui.dialog;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.StringUtils;

public class ErrorDialog extends CFrame implements DisplayableComponent {
    public ErrorDialog(Type type, String text) {
        this(type, StringUtils.convertStringToStringArray(text));
    }

    public ErrorDialog(Type type, String[] text) {
        super(AppSettings.CONSOLE_MIN_LENGTH, AppSettings.CONSOLE_MIN_HEIGHT);

        CLabel title = new CLabel("Erreur");
        title.getColors().add(CColor.BOLD);
        title.getColors().add(CColor.RED);
        CPanel header = new CPanel(0, title.getHeight());
        header.getComponents().add(title);
        this.setHeader(header);

        CLabel introMessage = new CLabel(type.getTitle(), this.getLength() - 2);
        introMessage.getColors().add(CColor.BOLD);
        introMessage.getColors().add(CColor.BLINKING);
        introMessage.getColors().add(CColor.RED);
        this.getContentPane().getComponents().add(introMessage);

        CLabel errorMessage = new CLabel(text, this.getLength() - 2);
        if (type.equals(Type.EXCEPTION)) {
            errorMessage.setHorizontalAlignment(HorizontalAlignment.LEFT);
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
        ERROR("\u26A0 Une erreur est survenue \u26A0", "Appuyez sur une touche pour continuer..."),
        EXCEPTION("\u26A0 Une erreur fatale est survenue \u26A0", "Appuyez sur une touche pour quitter...");

        private final String title;
        private final String footer;

        Type(String text, String footer) {
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
