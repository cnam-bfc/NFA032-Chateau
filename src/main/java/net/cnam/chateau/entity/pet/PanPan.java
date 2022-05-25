package net.cnam.chateau.entity.pet;

public class PanPan extends Pet {
    private boolean power = true;

    public PanPan() {
        super("PanPan");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO détere un objet aléatoire rare
        power = false;
    }
}
