package net.cnam.chateau.gui.play.item.consumable;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.consumable.Consumable;

public class ConsumeItemButton extends CButton {
    private final App app;
    private final ConsumableItemMenu consumableItemMenu;
    private final Item item;
    private final Entity entity;

    public ConsumeItemButton(App app, ConsumableItemMenu consumableItemMenu, Item item, Entity entity) {
        super(app, entity.getName() + ((entity == app.getCurrentGame().getPlayer()) ? " (Vous)" : ""));

        this.app = app;
        this.consumableItemMenu = consumableItemMenu;
        this.item = item;
        this.entity = entity;
    }

    @Override
    public void execute() {
        ((Consumable) item).consume(entity);
        consumableItemMenu.stopLoopingMode();
        app.getCurrentGame().getPlayer().setItem(null);
    }
}
