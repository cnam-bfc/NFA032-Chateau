package net.cnam.chateau.gui.play.item.consumable;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.item.consumable.Consumable;

import java.util.List;

public class ConsumableItemMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public ConsumableItemMenu(App app, Consumable consumable, List<Entity> entities) {
        super(0, 0, "Utiliser " + ((Item) consumable).getName());

        Item item = (Item) consumable;

        CLabel label = new CLabel("Sur quelle entité voulez-vous utiliser " + item.getName() + " ?");
        this.getContentPane().getComponents().add(label);

        CChoices choices = new CChoices(app, 1);
        for (Entity entity : entities) {
            choices.add(new ConsumeItemButton(app, this, item, entity));
        }

        choices.add(new BackButton(app, this));

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public void stopDisplaying() {
        display = false;
    }
}
