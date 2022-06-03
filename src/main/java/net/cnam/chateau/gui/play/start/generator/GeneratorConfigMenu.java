package net.cnam.chateau.gui.play.start.generator;

import net.cnam.chateau.App;
import net.cnam.chateau.GeneratorSettings;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.utils.Couple;

public class GeneratorConfigMenu extends CFrame implements DisplayableComponent {
    private final GeneratorSettings generatorSettings;
    private final GeneratorConfigSlider minStageSlider;
    private final GeneratorConfigSlider maxStageSlider;
    private final GeneratorConfigSlider minSizeStageSlider;
    private final GeneratorConfigSlider maxSizeStageSlider;
    private final GeneratorConfigSlider minSizeRoomSlider;
    private final GeneratorConfigSlider maxSizeRoomSlider;
    private final GeneratorConfigSlider nbIterationMinSlider;
    private final GeneratorConfigSlider nbIterationMaxSlider;
    private final GeneratorConfigSlider pourcentDivideSlider;
    private final GeneratorConfigSlider minBlocksSlider;
    private final GeneratorConfigSlider maxBlocksSlider;
    private final GeneratorConfigSlider luckBlockSlider;
    private final GeneratorConfigSlider minEnemiesStageSlider;
    private final GeneratorConfigSlider maxEnemiesStageSlider;
    private final GeneratorConfigSlider luckSpecialEnemySlider;

    private boolean display = true;

    public GeneratorConfigMenu(App app, GeneratorSettings generatorSettings) {
        super(0, 0, "Configuration du générateur");

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

        // minSizeRoom
        this.minSizeRoomSlider = new GeneratorConfigSlider(this, "Taille minimale des pièces");
        choices.add(this.minSizeRoomSlider);

        // maxSizeRoom
        this.maxSizeRoomSlider = new GeneratorConfigSlider(this, "Taille maximale des pièces");
        choices.add(this.maxSizeRoomSlider);

        // nbIterationMin
        this.nbIterationMinSlider = new GeneratorConfigSlider(this, "Tentatives de divisions des pièces minimum");
        choices.add(this.nbIterationMinSlider);

        // nbIterationMax
        this.nbIterationMaxSlider = new GeneratorConfigSlider(this, "Tentatives de divisions des pièces maximum");
        choices.add(this.nbIterationMaxSlider);

        // pourcentDivide
        this.pourcentDivideSlider = new GeneratorConfigSlider(this, "Chance de division des pièces");
        choices.add(this.pourcentDivideSlider);

        // minBlocks
        this.minBlocksSlider = new GeneratorConfigSlider(this, "Nombre de structures minimum par pièce");
        choices.add(this.minBlocksSlider);

        // maxBlocks
        this.maxBlocksSlider = new GeneratorConfigSlider(this, "Nombre de structures maximum par pièce");
        choices.add(this.maxBlocksSlider);

        // luckBlock
        this.luckBlockSlider = new GeneratorConfigSlider(this, "Chance de générer une structure rare");
        choices.add(this.luckBlockSlider);

        // minEnemiesStage
        this.minEnemiesStageSlider = new GeneratorConfigSlider(this, "Nombre d'ennemis minimum par étage");
        choices.add(this.minEnemiesStageSlider);

        // maxEnemiesStage
        this.maxEnemiesStageSlider = new GeneratorConfigSlider(this, "Nombre d'ennemis maximum par étage");
        choices.add(this.maxEnemiesStageSlider);

        // luckSpecialEnemy
        this.luckSpecialEnemySlider = new GeneratorConfigSlider(this, "Chance générer un ennemi spécial");
        choices.add(this.luckSpecialEnemySlider);

        update();

        CPanel footer = new CPanel(0, 1);
        QuitComponentButton backButton = new QuitComponentButton(app, this, "Retour");
        backButton.setSelected(true);
        footer.getComponents().add(backButton);
        this.setFooter(footer);

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

    public void update(GeneratorConfigSlider updatedSlider) {
        if (updatedSlider == minStageSlider) {
            generatorSettings.setMinStage(minStageSlider.getValue());
        } else if (updatedSlider == maxStageSlider) {
            generatorSettings.setMaxStage(maxStageSlider.getValue());
        } else if (updatedSlider == minSizeStageSlider) {
            generatorSettings.setMinSizeStage(minSizeStageSlider.getValue());
        } else if (updatedSlider == maxSizeStageSlider) {
            generatorSettings.setMaxSizeStage(maxSizeStageSlider.getValue());
        } else if (updatedSlider == minSizeRoomSlider) {
            generatorSettings.setMinSizeRoom(minSizeRoomSlider.getValue());
        } else if (updatedSlider == maxSizeRoomSlider) {
            generatorSettings.setMaxSizeRoom(maxSizeRoomSlider.getValue());
        } else if (updatedSlider == nbIterationMinSlider) {
            generatorSettings.setNbIterationMin(nbIterationMinSlider.getValue());
        } else if (updatedSlider == nbIterationMaxSlider) {
            generatorSettings.setNbIterationMax(nbIterationMaxSlider.getValue());
        } else if (updatedSlider == pourcentDivideSlider) {
            generatorSettings.setPourcentDivide(pourcentDivideSlider.getValue());
        } else if (updatedSlider == minBlocksSlider) {
            generatorSettings.setMinBlocks(minBlocksSlider.getValue());
        } else if (updatedSlider == maxBlocksSlider) {
            generatorSettings.setMaxBlocks(maxBlocksSlider.getValue());
        } else if (updatedSlider == luckBlockSlider) {
            generatorSettings.setLuckBlock(luckBlockSlider.getValue());
        } else if (updatedSlider == minEnemiesStageSlider) {
            generatorSettings.setMinEnemiesStage(minEnemiesStageSlider.getValue());
        } else if (updatedSlider == maxEnemiesStageSlider) {
            generatorSettings.setMaxEnemiesStage(maxEnemiesStageSlider.getValue());
        } else if (updatedSlider == luckSpecialEnemySlider) {
            generatorSettings.setLuckSpecialEnemy(luckSpecialEnemySlider.getValue());
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

        // minSizeRoom
        Couple<Integer, Integer> minSizeRoom = this.generatorSettings.verifyMinSizeRoom();
        this.minSizeRoomSlider.setMinValue(minSizeRoom.getElemOne());
        this.minSizeRoomSlider.setMaxValue(minSizeRoom.getElemTwo());
        this.minSizeRoomSlider.forceValue(this.generatorSettings.getMinSizeRoom());

        // maxSizeRoom
        Couple<Integer, Integer> maxSizeRoom = this.generatorSettings.verifyMaxSizeRoom();
        this.maxSizeRoomSlider.setMinValue(maxSizeRoom.getElemOne());
        this.maxSizeRoomSlider.setMaxValue(maxSizeRoom.getElemTwo());
        this.maxSizeRoomSlider.forceValue(this.generatorSettings.getMaxSizeRoom());

        // nbIterationMin
        Couple<Integer, Integer> nbIterationMin = this.generatorSettings.verifyNbIterationMin();
        this.nbIterationMinSlider.setMinValue(nbIterationMin.getElemOne());
        this.nbIterationMinSlider.setMaxValue(nbIterationMin.getElemTwo());
        this.nbIterationMinSlider.forceValue(this.generatorSettings.getNbIterationMin());

        // nbIterationMax
        Couple<Integer, Integer> nbIterationMax = this.generatorSettings.verifyNbIterationMax();
        this.nbIterationMaxSlider.setMinValue(nbIterationMax.getElemOne());
        this.nbIterationMaxSlider.setMaxValue(nbIterationMax.getElemTwo());
        this.nbIterationMaxSlider.forceValue(this.generatorSettings.getNbIterationMax());

        // pourcentDivide
        Couple<Integer, Integer> pourcentDivide = this.generatorSettings.verifyPourcentDivide();
        this.pourcentDivideSlider.setMinValue(pourcentDivide.getElemOne());
        this.pourcentDivideSlider.setMaxValue(pourcentDivide.getElemTwo());
        this.pourcentDivideSlider.forceValue(this.generatorSettings.getPourcentDivide());

        // minBlocks
        Couple<Integer, Integer> minBlocks = this.generatorSettings.verifyMinBlocks();
        this.minBlocksSlider.setMinValue(minBlocks.getElemOne());
        this.minBlocksSlider.setMaxValue(minBlocks.getElemTwo());
        this.minBlocksSlider.forceValue(this.generatorSettings.getMinBlocks());

        // maxBlocks
        Couple<Integer, Integer> maxBlocks = this.generatorSettings.verifyMaxBlocks();
        this.maxBlocksSlider.setMinValue(maxBlocks.getElemOne());
        this.maxBlocksSlider.setMaxValue(maxBlocks.getElemTwo());
        this.maxBlocksSlider.forceValue(this.generatorSettings.getMaxBlocks());

        // luckBlock
        Couple<Integer, Integer> luckBlock = this.generatorSettings.verifyLuckBlock();
        this.luckBlockSlider.setMinValue(luckBlock.getElemOne());
        this.luckBlockSlider.setMaxValue(luckBlock.getElemTwo());
        this.luckBlockSlider.forceValue(this.generatorSettings.getLuckBlock());

        // minEnemiesStage
        Couple<Integer, Integer> minEnemiesStage = this.generatorSettings.verifyMinEnemiesStage();
        this.minEnemiesStageSlider.setMinValue(minEnemiesStage.getElemOne());
        this.minEnemiesStageSlider.setMaxValue(minEnemiesStage.getElemTwo());
        this.minEnemiesStageSlider.forceValue(this.generatorSettings.getMinEnemiesStage());

        // maxEnemiesStage
        Couple<Integer, Integer> maxEnemiesStage = this.generatorSettings.verifyMaxEnemiesStage();
        this.maxEnemiesStageSlider.setMinValue(maxEnemiesStage.getElemOne());
        this.maxEnemiesStageSlider.setMaxValue(maxEnemiesStage.getElemTwo());
        this.maxEnemiesStageSlider.forceValue(this.generatorSettings.getMaxEnemiesStage());

        // luckSpecialEnemy
        Couple<Integer, Integer> luckSpecialEnemy = this.generatorSettings.verifyLuckSpecialEnemy();
        this.luckSpecialEnemySlider.setMinValue(luckSpecialEnemy.getElemOne());
        this.luckSpecialEnemySlider.setMaxValue(luckSpecialEnemy.getElemTwo());
        this.luckSpecialEnemySlider.forceValue(this.generatorSettings.getLuckSpecialEnemy());
    }
}
