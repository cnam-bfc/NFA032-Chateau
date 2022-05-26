package net.cnam.chateau.gui.play;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class EntityStats extends CPanel {
    private final Entity entity;
    private final CLabel name;
    private final CProgressBar hpBar;

    public EntityStats(Entity entity, int length) {
        super(HorizontalAlignment.LEFT, length, 0, Orientation.VERTICAL, false);

        this.entity = entity;

        this.name = new CLabel(HorizontalAlignment.LEFT, entity.getName());
        this.getComponents().add(name);

        CLabel hpLabel = new CLabel(HorizontalAlignment.LEFT, "Vie: ");
        hpLabel.getColors().add(CColor.RED);
        this.hpBar = new CProgressBar(length- hpLabel.getLength(), 1, entity.getHealth(), entity.getMaxHealth());
        hpBar.getColors().add(CColor.RED);
        CPanel hp = new CPanel(HorizontalAlignment.LEFT, length, 1, Orientation.HORIZONTAL, false);
        hp.getComponents().add(hpLabel);
        hp.getComponents().add(hpBar);
        this.getComponents().add(hp);

        int height = 0;
        for (CComponent component : this.getComponents()) {
            height += component.getHeight();
        }
        this.setHeight(height);
    }

    @Override
    public String[] render() {
        name.setText(entity.getName());
        name.setLength(entity.getName().length());

        hpBar.setValue(entity.getHealth());
        hpBar.setMaxValue(entity.getMaxHealth());

        return super.render();
    }
}
