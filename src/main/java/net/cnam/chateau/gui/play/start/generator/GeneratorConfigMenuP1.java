package net.cnam.chateau.gui.play.start.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.GeneratorSettings;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.utils.Couple;

public class GeneratorConfigMenuP1 extends CFrame implements DisplayableComponent, GeneratorConfigMenu {
    private final App app;
    private final GeneratorSettings generatorSettings;
    private final GeneratorConfigSlider minStageSlider;
    private final GeneratorConfigSlider maxStageSlider;
    private final GeneratorConfigSlider minSizeStageSlider;
    private final GeneratorConfigSlider maxSizeStageSlider;

    private boolean display = true;

    public GeneratorConfigMenuP1(App app, GeneratorSettings generatorSettings) {
        super(0, 0, "Configuration du générateur");

        this.app = app;
        this.generatorSettings = generatorSettings;

        CChoices choices = new CChoices(app, 1);
        // minStage
        this.minStageSlider = new GeneratorConfigSlider(this, "Nombre d'étages minimum");
        choices.add(this.minStageSlider);
        // maxStage
        this.maxStageSlider = new GeneratorConfigSlider(this, "Nombre d'étages maximum");
        choices.add(this.maxStageSlider);
        // minSizeStage
        this.minSizeStageSlider = new GeneratorConfigSlider(this, "Taille minimale des étages");
        choices.add(this.minSizeStageSlider);
        // maxSizeStage
        this.maxSizeStageSlider = new GeneratorConfigSlider(this, "Taille maximale des étages");
        choices.add(this.maxSizeStageSlider);

        update();

        // Bouton retour
        choices.add(new QuitComponentButton(app, this, "Retour"));

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public void stopLoopingMode() {
        this.display = false;
    }

    @Override
    public void update(GeneratorConfigSlider updatedSlider) {
        if (updatedSlider == minStageSlider) {
            generatorSettings.setMinStage(minStageSlider.getValue());
        } else if (updatedSlider == maxStageSlider) {
            generatorSettings.setMaxStage(maxStageSlider.getValue());
        } else if (updatedSlider == minSizeStageSlider) {
            generatorSettings.setMinSizeStage(minSizeStageSlider.getValue());
        } else if (updatedSlider == maxSizeStageSlider) {
            generatorSettings.setMaxSizeStage(maxSizeStageSlider.getValue());
        }

        update();
    }

    private void update() {
        // minStage
        Couple<Integer, Integer> minStage = this.generatorSettings.verifyMinStage();
        this.minStageSlider.setMinValue(minStage.getElemOne());
        this.minStageSlider.setMaxValue(minStage.getElemTwo());
        this.minStageSlider.forceValue(this.generatorSettings.getMinStage());

        // maxStage
        Couple<Integer, Integer> maxStage = this.generatorSettings.verifyMaxStage();
        this.maxStageSlider.setMinValue(maxStage.getElemOne());
        this.maxStageSlider.setMaxValue(maxStage.getElemTwo());
        this.maxStageSlider.forceValue(this.generatorSettings.getMaxStage());

        // minSizeStage
        Couple<Integer, Integer> minSizeStage = this.generatorSettings.verifyMinSizeStage();
        this.minSizeStageSlider.setMinValue(minSizeStage.getElemOne());
        this.minSizeStageSlider.setMaxValue(minSizeStage.getElemTwo());
        this.minSizeStageSlider.forceValue(this.generatorSettings.getMinSizeStage());

        // maxSizeStage
        Couple<Integer, Integer> maxSizeStage = this.generatorSettings.verifyMaxSizeStage();
        this.maxSizeStageSlider.setMinValue(maxSizeStage.getElemOne());
        this.maxSizeStageSlider.setMaxValue(maxSizeStage.getElemTwo());
        this.maxSizeStageSlider.forceValue(this.generatorSettings.getMaxSizeStage());
    }
}
