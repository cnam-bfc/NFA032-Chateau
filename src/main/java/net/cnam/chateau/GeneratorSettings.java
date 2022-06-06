package net.cnam.chateau;

import net.cnam.chateau.utils.Couple;

import java.io.*;

public class GeneratorSettings {

    private static final int MIN_SIZE_STAGE = 25;
    private static final int MAX_SIZE_STAGE = 35;
    private static final int MIN_STAGE = 2;
    private static final int MAX_STAGE = 5;
    private static final int MIN_SIZE_ROOM = 6;
    private static final int MAX_SIZE_ROOM = MIN_SIZE_ROOM * 2 + 2;
    private static final int NB_ITERATION_MIN = 3;
    private static final int NB_ITERATION_MAX = 5;
    private static final int POURCENT_DIVIDE = 10;
    private static final int MIN_BLOCKS = 1;
    private static final int MAX_BLOCKS = 3;
    private static final int LUCK_BLOCK = 35;
    private static final int MIN_ENEMIES_STAGE = 1;
    private static final int MAX_ENEMIES_STAGE = 3;
    private static final int LUCK_SPECIAL_ENEMY = 30;

    public static final String DEFAULT_FILE_PATH = "generator-settings.dat";

    private int minSizeStage = MIN_SIZE_STAGE;              // Taille minimum d'un étage
    private int maxSizeStage = MAX_SIZE_STAGE;              // Taille maximum d'un étage
    private int minStage = MIN_STAGE;                       // Nombre d'étages minimum
    private int maxStage = MAX_STAGE;                       // Nombre d'étages maximum
    private int minSizeRoom = MIN_SIZE_ROOM;                // Taille minimum des pièces
    private int maxSizeRoom = MAX_SIZE_ROOM;                // Taille maximum des pièces
    private int nbIterationMin = NB_ITERATION_MIN;          // Nombre d'itérations minimum lors des divisions de pièces
    private int nbIterationMax = NB_ITERATION_MAX;          // Nombre d'itérations maximum lors des divisions de pièces
    private int pourcentDivide = POURCENT_DIVIDE;           // Chance de faire une division de pièce à chaque itération
    private int minBlocks = MIN_BLOCKS;                     // Nombre minimum de blocks par pièce
    private int maxBlocks = MAX_BLOCKS;                     // Nombre maximum de blocks par pièce
    private int luckBlock = LUCK_BLOCK;                     // Chance d'avoir des blocks rare dans les pièces
    private int minEnemiesStage = MIN_ENEMIES_STAGE;        // Nombre minimum d'ennemie par étage
    private int maxEnemiesStage = MAX_ENEMIES_STAGE;        // Nombre maximum d'ennemie par étage
    private int luckSpecialEnemy = LUCK_SPECIAL_ENEMY;    // Chance de faire apparaître un ennemi special à la génération

    /**
     * Méthode permettant de remettre à zéro les paramètres du générateur
     */
    public void reset() {
        this.minSizeStage = MIN_SIZE_STAGE;
        this.maxSizeStage = MAX_SIZE_STAGE;
        this.minStage = MIN_STAGE;
        this.maxStage = MAX_STAGE;
        this.minSizeRoom = MIN_SIZE_ROOM;
        this.maxSizeRoom = MAX_SIZE_ROOM;
        this.nbIterationMin = NB_ITERATION_MIN;
        this.nbIterationMax = NB_ITERATION_MAX;
        this.pourcentDivide = POURCENT_DIVIDE;
        this.minBlocks = MIN_BLOCKS;
        this.maxBlocks = MAX_BLOCKS;
        this.luckBlock = LUCK_BLOCK;
        this.minEnemiesStage = MIN_ENEMIES_STAGE;
        this.maxEnemiesStage = MAX_ENEMIES_STAGE;
        this.luckSpecialEnemy = LUCK_SPECIAL_ENEMY;
    }

    /**
     * Méthode permettant de sauvegarder dans un fichier les paramètres de l'utilisateur.
     *
     * @param file Le fichier où doivent être sauvegardés les paramètres
     * @throws IOException Levé d'une exception si un problème lors de la sauvegarde
     */
    public void save(File file) throws IOException {
        FileOutputStream fluxEntree = new FileOutputStream(file);
        BufferedOutputStream outTampon = new BufferedOutputStream(fluxEntree);
        DataOutputStream out = new DataOutputStream(outTampon);

        out.writeInt(this.minSizeStage);
        out.writeInt(this.maxSizeStage);
        out.writeInt(this.minStage);
        out.writeInt(this.maxStage);
        out.writeInt(this.minSizeRoom);
        out.writeInt(this.maxSizeRoom);
        out.writeInt(this.nbIterationMin);
        out.writeInt(this.nbIterationMax);
        out.writeInt(this.pourcentDivide);
        out.writeInt(this.minBlocks);
        out.writeInt(this.maxBlocks);
        out.writeInt(this.luckBlock);
        out.writeInt(this.minEnemiesStage);
        out.writeInt(this.maxEnemiesStage);
        out.writeInt(this.luckSpecialEnemy);

        out.close();
    }

    /**
     * Méthode permettant de récupérer les paramètres de l'utilisateur si ceux-ci existent.
     *
     * @param file Le fichier où doivent être sauvegardés les paramètres
     * @throws IOException                    Levé d'une exception si un problème lors du chargement du fichier
     * @throws SettingsFileCorruptedException Levé d'une exception si le fichier de paramètres est corrompu
     */
    public void load(File file) throws IOException, SettingsFileCorruptedException {
        FileInputStream fluxEntree = new FileInputStream(file);
        BufferedInputStream inTampon = new BufferedInputStream(fluxEntree);
        DataInputStream in = new DataInputStream(inTampon);

        if (in.available() < 15) {
            in.close();
            throw new SettingsFileCorruptedException(file, "Le fichier de paramètres est corrompu");
        }

        setMinSizeStage(in.readInt());
        setMaxSizeStage(in.readInt());
        setMinStage(in.readInt());
        setMaxStage(in.readInt());
        setMinSizeRoom(in.readInt());
        setMaxSizeRoom(in.readInt());
        setNbIterationMin(in.readInt());
        setNbIterationMax(in.readInt());
        setPourcentDivide(in.readInt());
        setMinBlocks(in.readInt());
        setMaxBlocks(in.readInt());
        setLuckBlock(in.readInt());
        setMinEnemiesStage(in.readInt());
        setMaxEnemiesStage(in.readInt());
        setLuckSpecialEnemy(in.readInt());

        in.close();
    }

    public int getMinSizeStage() {
        return minSizeStage;
    }

    public void setMinSizeStage(int minSizeStage) {
        this.minSizeStage = minSizeStage;
    }

    public Couple<Integer, Integer> verifyMinSizeStage() {
        return new Couple<>(25, this.maxSizeStage);
    }

    public int getMaxSizeStage() {
        return maxSizeStage;
    }

    public void setMaxSizeStage(int maxSizeStage) {
        this.maxSizeStage = maxSizeStage;
    }

    public Couple<Integer, Integer> verifyMaxSizeStage() {

        return new Couple<>(this.minSizeStage, 100);
    }

    public int getMinStage() {
        return minStage;
    }

    public void setMinStage(int minStage) {
        this.minStage = minStage;
    }

    public Couple<Integer, Integer> verifyMinStage() {
        return new Couple<>(2, this.maxStage);
    }

    public int getMaxStage() {
        return maxStage;
    }

    public void setMaxStage(int maxStage) {
        this.maxStage = maxStage;
    }

    public Couple<Integer, Integer> verifyMaxStage() {
        return new Couple<>(this.minStage, 8);
    }

    public int getMinSizeRoom() {
        return minSizeRoom;
    }

    public void setMinSizeRoom(int minSizeRoom) {
        this.minSizeRoom = minSizeRoom;
    }

    public Couple<Integer, Integer> verifyMinSizeRoom() {
        return new Couple<>(5, 7);
    }

    public int getMaxSizeRoom() {
        return maxSizeRoom;
    }

    public void setMaxSizeRoom(int maxSizeRoom) {
        this.maxSizeRoom = maxSizeRoom;
    }

    public Couple<Integer, Integer> verifyMaxSizeRoom() {
        return new Couple<>(this.minSizeRoom * 2 + 1, this.minSizeRoom * 3);
    }

    public int getNbIterationMin() {
        return nbIterationMin;
    }

    public void setNbIterationMin(int nbIterationMin) {
        this.nbIterationMin = nbIterationMin;
    }

    public Couple<Integer, Integer> verifyNbIterationMin() {
        return new Couple<>(3, 5);
    }

    public int getNbIterationMax() {
        return nbIterationMax;
    }

    public void setNbIterationMax(int nbIterationMax) {
        this.nbIterationMax = nbIterationMax;
    }

    public Couple<Integer, Integer> verifyNbIterationMax() {
        return new Couple<>(this.nbIterationMin, 8);
    }

    public int getPourcentDivide() {
        return pourcentDivide;
    }

    public void setPourcentDivide(int pourcentDivide) {
        this.pourcentDivide = pourcentDivide;
    }

    public Couple<Integer, Integer> verifyPourcentDivide() {
        return new Couple<>(10, 25);
    }

    public int getMinBlocks() {
        return minBlocks;
    }

    public void setMinBlocks(int minBlocks) {
        this.minBlocks = minBlocks;
    }

    public Couple<Integer, Integer> verifyMinBlocks() {
        return new Couple<>(1, (int) Math.pow(this.minSizeRoom - 2, 2) / 3);
    }

    public int getMaxBlocks() {
        return maxBlocks;
    }

    public void setMaxBlocks(int maxBlocks) {
        this.maxBlocks = maxBlocks;
    }

    public Couple<Integer, Integer> verifyMaxBlocks() {
        return new Couple<>(this.minBlocks, (int) Math.pow(this.minSizeRoom - 2, 2) / 3);
    }

    public int getLuckBlock() {
        return luckBlock;
    }

    public void setLuckBlock(int luckBlock) {
        this.luckBlock = luckBlock;
    }

    public Couple<Integer, Integer> verifyLuckBlock() {
        return new Couple<>(10, 30);
    }

    public int getMinEnemiesStage() {
        return minEnemiesStage;
    }

    public void setMinEnemiesStage(int minEnemiesStage) {
        this.minEnemiesStage = minEnemiesStage;
    }

    public Couple<Integer, Integer> verifyMinEnemiesStage() {
        return new Couple<>(0, this.maxEnemiesStage);
    }

    public int getMaxEnemiesStage() {
        return maxEnemiesStage;
    }

    public void setMaxEnemiesStage(int maxEnemiesStage) {
        this.maxEnemiesStage = maxEnemiesStage;
    }

    public Couple<Integer, Integer> verifyMaxEnemiesStage() {
        return new Couple<>(this.minEnemiesStage, 10);
    }

    public int getLuckSpecialEnemy() {
        return luckSpecialEnemy;
    }

    public void setLuckSpecialEnemy(int luckSpecialEnemy) {
        this.luckSpecialEnemy = luckSpecialEnemy;
    }

    public Couple<Integer, Integer> verifyLuckSpecialEnemy() {
        return new Couple<>(10, 30);
    }
}
