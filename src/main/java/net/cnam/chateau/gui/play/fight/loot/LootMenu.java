package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;

public class LootMenu extends CFrame implements DisplayableComponent {
    private final CChoices choices;
    private final LeaveButton leaveButton;

    private boolean display = true;

    public LootMenu(App app, Player player, Entity enemy) {
        super(0, 0, "Butin de " + enemy.getName());

        this.choices = new CChoices(app, 1);
        if (enemy.hasWeapon()) {
            if (player.hasWeapon()) {
                choices.add(new ReplaceWeaponButton(app, player, enemy, this));
            } else {
                choices.add(new TakeWeaponButton(app, player, enemy, this));
            }
        }
        if (enemy.hasItem()) {
            if (player.hasItem()) {
                choices.add(new ReplaceItemButton(app, player, enemy, this));
            } else {
                choices.add(new TakeItemButton(app, player, enemy, this));
            }
        }

        this.leaveButton = new LeaveButton(app, this);

        choices.add(leaveButton);

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public String[] render() {
        update();

        return super.render();
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public void stop() {
        display = false;
    }

    public CChoices getChoices() {
        return choices;
    }

    public LeaveButton getLeaveButton() {
        return leaveButton;
    }

    private void update(){

    }
}
