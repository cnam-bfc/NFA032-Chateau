package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.finish.FinishMenu;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Exit extends Block implements BlockListener {
    private final App app;

    /**
     * Constructeur
     *
     * @param app L'application
     */
    public Exit(App app) {
        super("Sortie");

        this.app = app;
    }

    /**
     * Redéfinition de la méthode permettant d'afficher un caractère sur la carte.
     * Ici on affiche la porte de sortie avec un espace jaune dans un mur.
     *
     * @return Un espace jaune
     */
    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW.getBackground() + " " + CColor.BRIGHT_YELLOW.getBackgroundReset();
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (event.getEntity() == app.getCurrentGame().getPlayer()) {
            app.getCurrentGame().stop();

            SimpleAudioPlayer winAudioPlayer = null;
            try {
                winAudioPlayer = app.createAudioPlayer(Music.WIN);
                winAudioPlayer.setVolume(app.getSettings().getMusicVolume());
                winAudioPlayer.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                     IllegalArgumentException ignored) {
            }
            app.getConsole().show(new FinishMenu(app, app.getCurrentGame(), true));
            if (winAudioPlayer != null) {
                winAudioPlayer.stop();
            }
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
