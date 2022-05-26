package net.cnam.chateau.gui.play;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class EntityStats extends CPanel {
    private final Entity entity;
    private final CLabel name;
    private final CProgressBar hpBar;

    public EntityStats(Entity entity) {
        super(HorizontalAlignment.LEFT, 0, 0, Orientation.VERTICAL, false);

        this.entity = entity;

        this.name = new CLabel(HorizontalAlignment.LEFT, entity.getName());
        this.getComponents().add(name);

        this.hpBar = new CProgressBar(0, 1, entity.getHealth(), entity.getMaxHealth(), "%VALUE%/%MAX_VALUE% hp");
        hpBar.getColors().add(CColor.RED);
        this.getComponents().add(hpBar);

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

    @Override
    public void setLength(int length) {
        super.setLength(length);

        name.setLength(length);
        hpBar.setLength(length);
    }
}
