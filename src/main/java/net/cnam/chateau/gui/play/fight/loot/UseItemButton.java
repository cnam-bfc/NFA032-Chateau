package net.cnam.chateau.gui.play.fight.loot;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.common.OpenComponentButton;
import net.cnam.chateau.gui.component.CComponent;
import net.cnam.chateau.gui.play.item.consumable.ConsumableItemMenu;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.consumable.Consumable;

import java.util.List;

public class UseItemButton extends OpenComponentButton {
    private final App app;
    private final LootMenu lootMenu;
    private final List<Entity> entities;

    public UseItemButton(App app, LootMenu lootMenu, Consumable item, List<Entity> fightEntities) {
        super(app, new ConsumableItemMenu(app, item, fightEntities), "Utiliser\n" + ((Item) item).getName());

        this.app = app;
        this.lootMenu = lootMenu;
        this.entities = fightEntities;
    }

    @Override
    public void execute() {
        super.execute();

        Player player = lootMenu.getPlayer();
        // Si le joueur à consommé l'item
        if (!player.hasItem()) {
            Entity enemy = lootMenu.getEntity();
            // Si l'ennemi à encore un item, on remplace le bouton "échanger les items" par un bouton "prendre l'item"
            for (CComponent component : lootMenu.getButtons().getComponents()) {
                if (component instanceof ReplaceItemButton replaceItemButton) {
                    TakeItemButton takeItemButton = new TakeItemButton(app, player, enemy, lootMenu);
                    lootMenu.getButtons().replace(replaceItemButton, takeItemButton);
                    break;
                }
            }
            // Si l'ennemi à un item consommable
            if (enemy.hasItem() && enemy.getItem() instanceof Consumable consumable) {
                UseItemButton useItemButton = new UseItemButton(app, lootMenu, consumable, entities);
                useItemButton.setSelected(true);
                lootMenu.getButtons().replace(this, useItemButton);
            } else {
                lootMenu.getButtons().remove(this);
                lootMenu.getLeaveButton().setSelected(true);
            }
        }
    }
}
