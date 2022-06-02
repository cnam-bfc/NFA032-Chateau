package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.item.consumable.ConsumableItemMenu;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.consumable.Consumable;

import java.util.List;

public class UseItemButton extends CButton {
    private final App app;
    private final Fight fight;
    private final Consumable item;
    private final List<Entity> entities;

    public UseItemButton(App app, Fight fight, Consumable item, List<Entity> fightEntities) {
        super(app, "Utiliser\n" + ((Item)item).getName());

        this.app = app;
        this.fight = fight;
        this.item = item;
        this.entities = fightEntities;
    }

    @Override
    public void execute() {
        app.getConsole().show(new ConsumableItemMenu(app, item, entities));
        fight.updateMenuButtons();
    }
}
