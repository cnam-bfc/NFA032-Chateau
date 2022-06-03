package net.cnam.chateau.game;

import net.cnam.chateau.App;
import net.cnam.chateau.GeneratorSettings;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Puzzle;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.event.player.PlayerInteractEvent;
import net.cnam.chateau.event.player.PlayerInteractListener;
import net.cnam.chateau.generator.Generator;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.play.EntityStats;
import net.cnam.chateau.gui.play.escape.EscapeMenu;
import net.cnam.chateau.gui.play.finish.FinishMenu;
import net.cnam.chateau.structure.Castle;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.structure.block.Block;
import net.cnam.chateau.utils.Couple;
import net.cnam.chateau.utils.Location;
import net.cnam.chateau.utils.StringUtils;
import net.cnam.chateau.utils.array.ArrayUtils;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends CFrame implements DisplayableComponent {
    private final App app;
    private final List<Puzzle> puzzles = new ArrayList<>();
    private final Castle castle;
    private final Map map;
    private final Player player;
    private final CLabel stageLevelLabel;
    private final CLabel blockNameLabel;
    private final EntityStats playerStats;
    private final CPanel otherInfos;
    private String infos;
    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;
    private final Statistic statistic;

    public Game(App app, long seed, String playerName) {
        super(0, 0);

        this.app = app;

        initPuzzles();

        GeneratorSettings settings = new GeneratorSettings();

        Generator generator = new Generator(app, this, seed, settings);
        this.castle = generator.generateCastle();
        Stage firstStage = this.castle.getStages()[0];
        this.player = new Player(app, firstStage, new Location(castle.getPlayerStartLocation().getX(), castle.getPlayerStartLocation().getY()), playerName);
        firstStage.getEntities().add(0, player);
        if (player.hasPet()) {
            firstStage.getEntities().add(1, player.getPet());
        }
        this.map = new Map(player);

        this.getContentPane().getComponents().add(map);

        CPanel header = new CPanel(0, 1, Orientation.HORIZONTAL, false);
        this.stageLevelLabel = new CLabel(HorizontalAlignment.LEFT, "Étage 1");
        header.getComponents().add(stageLevelLabel);
        CLabel title = new CLabel("Partie");
        header.getComponents().add(title);
        this.blockNameLabel = new CLabel(HorizontalAlignment.RIGHT, " ");
        header.getComponents().add(blockNameLabel);
        this.setHeader(header);

        CPanel footer = new CPanel(0, 4, Orientation.HORIZONTAL, false);

        this.playerStats = new EntityStats(player, Orientation.HORIZONTAL);
        playerStats.setHeight(4);
        footer.getComponents().add(playerStats);

        this.otherInfos = new CPanel(0, 4, Orientation.VERTICAL, false);
        this.infos = "Échap - Menu de pause";
        CLabel otherInfosLabel = new CLabel(infos);
        this.otherInfos.getComponents().add(new CLabel(""));
        this.otherInfos.getComponents().add(otherInfosLabel);
        footer.getComponents().add(otherInfos);

        this.setFooter(footer);

        try {
            this.audioPlayer = app.createAudioPlayer(Music.GAME);
            audioPlayer.setVolume(app.getSettings().getMusicVolume());
            audioPlayer.setLoop(true);
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                 IllegalArgumentException ignored) {
        }

        this.statistic = new Statistic(seed, playerName);
    }

    // TODO Corriger les puzzles
    private void initPuzzles() {
        ArrayList<Couple<String, Boolean>> answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Ronde", true));
        answersInit.add(new Couple<>("Plate", false));
        answersInit.add(new Couple<>("Tout est une question de point de vu", false));
        puzzles.add(new Puzzle("La Terre est-elle ronde ou plate ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("42", true));
        answersInit.add(new Couple<>("98", false));
        answersInit.add(new Couple<>("11", false));
        puzzles.add(new Puzzle("Le chiffre de la vie ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("3,141592", true));
        answersInit.add(new Couple<>("2,71828", false));
        answersInit.add(new Couple<>("1,435991", false));
        answersInit.add(new Couple<>("0,834626", false));
        puzzles.add(new Puzzle("Je suis la constante d'Archimède, qui suis-je ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Java", true));
        answersInit.add(new Couple<>("C", false));
        answersInit.add(new Couple<>("Python", false));
        answersInit.add(new Couple<>("Caml", false));
        answersInit.add(new Couple<>("html", false));
        puzzles.add(new Puzzle("En quel langage est produit ce jeu ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Harry Potter", true));
        answersInit.add(new Couple<>("Hermione", false));
        answersInit.add(new Couple<>("Voldemort", false));
        answersInit.add(new Couple<>("Alban", false));
        puzzles.add(new Puzzle("Quel nom du héro principal dans Harry Potter", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Pauvre", true));
        answersInit.add(new Couple<>("Fortuné", false));
        puzzles.add(new Puzzle("Quel est l'antonyme de riche ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Une casserole avec de l'eau", true));
        answersInit.add(new Couple<>("Un casserole avec du beurre", false));
        answersInit.add(new Couple<>("Une poêle à l'envers", false));
        answersInit.add(new Couple<>("Une chaussure", false));
        puzzles.add(new Puzzle("Dans quoi cuit-on les pâtes ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Herbivore", true));
        answersInit.add(new Couple<>("Omnivore", false));
        answersInit.add(new Couple<>("Carnivore", false));
        answersInit.add(new Couple<>("Végétalovore", false));
        puzzles.add(new Puzzle("Comment s'appellent les animaux qui se nourrissent uniquement de végétaux et de plantes ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("VS code", true));
        answersInit.add(new Couple<>("NetBeans", false));
        answersInit.add(new Couple<>("Intellij", false));
        puzzles.add(new Puzzle("Le pire IDE : ", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("9", true));
        answersInit.add(new Couple<>("12", false));
        answersInit.add(new Couple<>("trop compliqué pour moi", false));
        puzzles.add(new Puzzle("1+2(4)", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Ismail/Célian/Melvin", true));
        answersInit.add(new Couple<>("Melvin/Célian/Ismail", false));
        answersInit.add(new Couple<>("Célian/Melvin/Ismail", false));
        puzzles.add(new Puzzle("Dans quel ordre sont parties les candidats de Cnam-Lanta", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Dans les Alpes", true));
        answersInit.add(new Couple<>("Dans les Pyrénées", false));
        answersInit.add(new Couple<>("Dans le Jura", false));
        answersInit.add(new Couple<>("C'est pas un yaourt ?", false));
        puzzles.add(new Puzzle("Où se trouve le Mont Blanc ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Est", true));
        answersInit.add(new Couple<>("Ouest", false));
        answersInit.add(new Couple<>("Nord", false));
        answersInit.add(new Couple<>("Sud", false));
        answersInit.add(new Couple<>("Plein milieu", false));
        puzzles.add(new Puzzle("D'où se lève le soleil ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Paris", true));
        answersInit.add(new Couple<>("Marseille", false));
        answersInit.add(new Couple<>("Saussay-la-Campagne", false));
        answersInit.add(new Couple<>("New-York", false));
        answersInit.add(new Couple<>("Chalon", false));
        puzzles.add(new Puzzle("Où se trouve la Tour Eiffel ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("1914-1918", true));
        answersInit.add(new Couple<>("1939-1945", false));
        answersInit.add(new Couple<>("1814-1818", false));
        answersInit.add(new Couple<>("1839-1845", false));
        puzzles.add(new Puzzle("Quelles sont les dates de la Première Guerre mondiale ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Ananas", true));
        answersInit.add(new Couple<>("Chien", false));
        answersInit.add(new Couple<>("Castor", false));
        answersInit.add(new Couple<>("Victor", false));
        puzzles.add(new Puzzle("Trouver l'intrus", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Un petit carré blanc", true));
        answersInit.add(new Couple<>("Un rond blanc", false));
        puzzles.add(new Puzzle("Qu'est ce qui est petit, carré et blanc ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Le Big Bang", true));
        answersInit.add(new Couple<>("L'énorme Paf !", false));
        answersInit.add(new Couple<>("Le Grand Boom", false));
        answersInit.add(new Couple<>("Le plat d'Alban dans la piscine", false));
        puzzles.add(new Puzzle("L'explosion à l'origine de l'univers est : ", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Le genou", true));
        answersInit.add(new Couple<>("Le coude", false));
        answersInit.add(new Couple<>("Le bassin", false));
        answersInit.add(new Couple<>("Le pied", false));
        puzzles.add(new Puzzle("Où se situe la rotule dans le corps humain ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Maxime", true));
        answersInit.add(new Couple<>("Le chiot", false));
        answersInit.add(new Couple<>("Le chaton", false));
        puzzles.add(new Puzzle("Comment appelle-t-on le petit de la brebis ?", answersInit));
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        // Touche échap = on ouvre le menu de pause
        if (event.getKey() == 27) {
            app.getConsole().show(new EscapeMenu(app, this));
            return;
        }

        // On transmet la touche appuyée aux composants dans cette fenêtre
        super.onKeyPressed(event);

        // On déplace le joueur vers la direction souhaitée
        try {
            Direction direction = DirectionUtils.parseDirection(event.getKey(), true);
            int x = player.getLocation().getX();
            int y = player.getLocation().getY();
            switch (direction) {
                case TOP -> y--;
                case RIGHT -> x++;
                case BOTTOM -> y++;
                case LEFT -> x--;
            }
            player.teleport(new Location(x, y));
        } catch (DirectionNotFoundException | CoordinatesOutOfBoundsException |
                 EntityAlreadyTeleportedException ignored) {
        }

        // Touche espace = on interagit avec le block
        if (event.getKey() == 32) {
            try {
                Block block = player.getStage().getBlock(player.getLocation());
                if (block instanceof PlayerInteractListener playerInteractListener) {
                    PlayerInteractEvent playerInteractEvent = new PlayerInteractEvent(player);
                    playerInteractListener.onPlayerInteract(playerInteractEvent);
                }
            } catch (CoordinatesOutOfBoundsException ignored) {
            }
        }
    }

    @Override
    public boolean isInLoopingMode() {
        // Si le joueur est mort, on arrête le jeu
        if (player.isDead()) {
            stop();

            SimpleAudioPlayer deathAudioPlayer = null;
            try {
                deathAudioPlayer = app.createAudioPlayer(Music.DEATH);
                deathAudioPlayer.setVolume(app.getSettings().getMusicVolume());
                deathAudioPlayer.setLoop(true);
                deathAudioPlayer.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                     IllegalArgumentException ignored) {
            }
            app.getConsole().show(new FinishMenu(app, this, false));
            if (deathAudioPlayer != null) {
                deathAudioPlayer.stop();
            }
        }

        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public void stop() {
        display = false;
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
    }

    @Override
    public String[] render() {
        // Actualisation du header
        int stageNB = 1;
        for (Stage stage : castle.getStages()) {
            if (stage == player.getStage()) {
                break;
            }
            stageNB++;
        }

        int headerFreeLength = this.getLength() - "Partie".length() - 2;
        this.stageLevelLabel.setLength(headerFreeLength / 2);
        this.blockNameLabel.setLength(headerFreeLength / 2 + headerFreeLength % 2);

        this.stageLevelLabel.setText(" Étage " + stageNB);

        try {
            Block playerBlock = player.getStage().getBlock(player.getLocation());
            // Actualisation du nom du block en haut à droite
            if (playerBlock != null) {
                this.blockNameLabel.setText(playerBlock.getName() + " ");
            } else {
                this.blockNameLabel.setText(" ");
            }

            // Actualisation du texte d'informations en bas à droite
            String[] infosTable = StringUtils.convertStringToStringArray(infos);
            if (playerBlock instanceof PlayerInteractListener) {
                if (infosTable.length > 1) {
                    infos = infosTable[infosTable.length - 1];
                    infosTable = StringUtils.convertStringToStringArray(infos);
                }
                infosTable = ArrayUtils.addOnTopOfArray(infosTable, "Espace - Interagir");
                infos = StringUtils.convertStringArrayToString(infosTable);
            } else {
                if (infosTable.length > 1) {
                    infos = infosTable[infosTable.length - 1];
                }
            }
        } catch (CoordinatesOutOfBoundsException ignored) {
        }

        // Actualisation du footer
        int footerStatsLength = this.getContentPane().getLength();

        this.getFooter().getComponents().clear();
        this.playerStats.setLength(footerStatsLength / 2);
        this.getFooter().getComponents().add(this.playerStats);

        this.otherInfos.getComponents().clear();
        if (this.player.hasPet()) {
            EntityStats petStats = new EntityStats(this.player.getPet(), Orientation.VERTICAL);
            petStats.setHeight(2);
            petStats.setLength(footerStatsLength / 2);
            this.otherInfos.getComponents().add(petStats);
        } else {
            this.otherInfos.getComponents().add(new CLabel(""));
        }

        this.otherInfos.getComponents().add(new CLabel(infos, footerStatsLength / 2));
        this.otherInfos.setLength(footerStatsLength / 2);
        this.getFooter().getComponents().add(this.otherInfos);

        return super.render();
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);

        this.map.setHeight(this.getContentPane().getHeight());
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        this.map.setLength(this.getContentPane().getLength());
    }

    public Castle getCastle() {
        return castle;
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public SimpleAudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public boolean hasPuzzles() {
        return !puzzles.isEmpty();
    }

    public Puzzle getRandomPuzzle() {
        if (puzzles.isEmpty()) {
            return null;
        }
        return puzzles.remove(new Random().nextInt(0, puzzles.size()));
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
