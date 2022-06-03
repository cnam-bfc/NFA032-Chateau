package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.gui.common.OpenComponentButton;
import net.cnam.chateau.gui.play.item.consumable.ConsumableItemMenu;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.consumable.Consumable;

import java.util.List;

public class UseItemButton extends OpenComponentButton {
    private final Fight fight;

    public UseItemButton(App app, Fight fight, Consumable item, List<Entity> fightEntities) {
        super(app, new ConsumableItemMenu(app, item, fightEntities), "Utiliser\n" + ((Item) item).getName());

        this.fight = fight;
    }

    @Override
    public void execute() {
        super.execute();

        fight.updateMenuButtons();
    }
}
