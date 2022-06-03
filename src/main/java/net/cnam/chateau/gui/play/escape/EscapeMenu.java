package net.cnam.chateau.gui.play.escape;

import net.cnam.chateau.App;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.game.Game;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.main.menu.information.InfoButton;
import net.cnam.chateau.item.consumable.Consumable;

public class EscapeMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public EscapeMenu(App app, Game game) {
        super(0, 0, "Pause");

        CChoices choices = new CChoices(app, 1);
        choices.add(new QuitComponentButton(app, this, "Reprendre la partie"));
        if (game.getPlayer().hasItem() && game.getPlayer().getItem() instanceof Consumable) {
            choices.add(new UseItemButton(app, this, game.getPlayer()));
        }
        choices.add(new InfoButton(app));
        choices.add(new CheatButton(app, game, this));
        choices.add(new SettingsButton(app, game));
        choices.add(new QuitGameButton(app, this));

        this.getContentPane().getComponents().add(choices);

        CPanel footer = new CPanel(0, 1);
        CLabel seedLabel = new CLabel("Seed : " + game.getCastle().getSeed());
        footer.getComponents().add(seedLabel);
        this.setFooter(footer);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        if (event.getKey() == 27) {
            stopLoopingMode();
            return;
        }

        super.onKeyPressed(event);
    }

    public void stopLoopingMode() {
        display = false;
    }
}
