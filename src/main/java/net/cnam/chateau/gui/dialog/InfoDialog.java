package net.cnam.chateau.gui.dialog;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.utils.StringUtils;

public class InfoDialog extends CFrame implements DisplayableComponent {
    public InfoDialog(Type type, String text) {
        this(type, StringUtils.convertStringToStringArray(text));
    }

    public InfoDialog(Type type, String[] text) {
        super(AppSettings.CONSOLE_MIN_LENGTH, AppSettings.CONSOLE_MIN_HEIGHT);

        CLabel title = new CLabel("Message");
        title.getColors().add(CColor.BOLD);
        title.getColors().add(CColor.YELLOW);
        CPanel header = new CPanel(0, title.getHeight());
        header.getComponents().add(title);
        this.setHeader(header);

        CLabel introMessage = new CLabel(type.getTitle(), this.getLength() - 2);
        introMessage.getColors().add(CColor.BOLD);
        if (!type.equals(Type.DEAD)) {
            introMessage.getColors().add(CColor.BLINKING);
        }
        introMessage.getColors().add(CColor.YELLOW);
        this.getContentPane().getComponents().add(introMessage);

        CLabel errorMessage = new CLabel(text, this.getLength() - 2);
        if (type.equals(Type.DEAD)) {
            errorMessage.getColors().add(CColor.BLINKING);
            errorMessage.getColors().add(CColor.BOLD);
            errorMessage.getColors().add(CColor.RED);
        } else {
            errorMessage.getColors().add(CColor.YELLOW);
        }
        this.getContentPane().getComponents().add(errorMessage);

        CLabel exitMessage = new CLabel(type.getFooter(), this.getLength() - 2);
        if (!type.equals(Type.DEAD)) {
            exitMessage.getColors().add(CColor.BLINKING);
        }
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
        INFO("\u24D8 Information \u24D8", "Appuyez sur une touche pour continuer..."),
        DEAD("\u2620 Vous êtes mort \u2620", "Appuyez sur une touche pour retourner au menu principal...");

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
