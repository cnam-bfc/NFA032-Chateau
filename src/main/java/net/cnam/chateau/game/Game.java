package net.cnam.chateau.game;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Puzzle;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.generator.Generator;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.gui.escape.menu.EscapeMenu;
import net.cnam.chateau.gui.play.fight.EntityStats;
import net.cnam.chateau.structure.Castle;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Couple;
import net.cnam.chateau.utils.Location;
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
    private final EntityStats playerStats;
    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;
    private final Statistics statistics;

    public Game(App app, long seed, String playerName) {
        super(0, 0);

        this.app = app;

        initPuzzles();

        Generator generator = new Generator(app, this, seed);
        this.castle = generator.generateCastle();
        Stage firstStage = this.castle.getStages()[0];
        this.player = new Player(app, firstStage, new Location(castle.getPlayerStartLocation().getX(), castle.getPlayerStartLocation().getY()), playerName);
        firstStage.getEntities().add(0, player);
        if (player.hasPet()) {
            firstStage.getEntities().add(1, player.getPet());
        }
        this.map = new Map(player);

        this.getContentPane().getComponents().add(map);

        CLabel title = new CLabel("Partie");
        CPanel header = new CPanel(0, title.getHeight());
        header.getComponents().add(title);
        this.setHeader(header);

        CPanel footer = new CPanel(0, 4, Orientation.HORIZONTAL, false);

        this.playerStats = new EntityStats(player, Orientation.HORIZONTAL);
        playerStats.setHeight(4);
        footer.getComponents().add(playerStats);

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
        puzzles.add(new Puzzle("Combien ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("3,141592", true));
        answersInit.add(new Couple<>("2,71828", false));
        answersInit.add(new Couple<>("1,435991", false));
        answersInit.add(new Couple<>("0,834626", false));
        puzzles.add(new Puzzle("Je suis la constante d'Archimède, qui suis-je ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Cnam", true));
        answersInit.add(new Couple<>("Cman", false));
        answersInit.add(new Couple<>("Manc", false));
        answersInit.add(new Couple<>("Canm", false));
        puzzles.add(new Puzzle("Ou êtes vous ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Harry Potter", true));
        answersInit.add(new Couple<>("Hermione", false));
        answersInit.add(new Couple<>("Voldemort", false));
        answersInit.add(new Couple<>("Alban", false));
        puzzles.add(new Puzzle("Quel nom du héro principal dans Harry Potter", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("5 fruits et légumes par jour", true));
        answersInit.add(new Couple<>("ses morts", false));
        answersInit.add(new Couple<>("3 fruits et légumes par jour", false));
        answersInit.add(new Couple<>("Le fiak de victor", false));
        puzzles.add(new Puzzle("il faut manger", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Une casserole avec de l'eau", true));
        answersInit.add(new Couple<>("Un casserole avec du beurre", false));
        answersInit.add(new Couple<>("Une poêle", false));
        answersInit.add(new Couple<>("Une chaussure", false));
        puzzles.add(new Puzzle("Dans quoi cuit-on les pâtes ?", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("7", true));
        answersInit.add(new Couple<>("10", false));
        answersInit.add(new Couple<>("5", false));
        answersInit.add(new Couple<>("3", false));
        puzzles.add(new Puzzle("Combien de coup de fouet pour dresser Victor", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("VS code", true));
        answersInit.add(new Couple<>("NetBeans", false));
        answersInit.add(new Couple<>("Intellij", false));
        puzzles.add(new Puzzle("Le pire IDE : ", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("9", true));
        answersInit.add(new Couple<>("12", false));
        puzzles.add(new Puzzle("1+2(4)", answersInit));

        answersInit = new ArrayList<>();
        answersInit.add(new Couple<>("Ismail/Melvin/Célian", true));
        answersInit.add(new Couple<>("Melvin/Célian/Ismail", false));
        answersInit.add(new Couple<>("Célian/Melvin/Ismail", false));
        puzzles.add(new Puzzle("Dans quel ordre sont parties les candidats de Cnam-Lanta", answersInit));
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
            Direction direction = DirectionUtils.parseDirection(event.getKey());
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
            app.getConsole().show(new InfoDialog(InfoDialog.Type.DEAD, "GAME OVER"));
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

        // TODO voir si ça reste ici
        int nbRoomsVisited = 0;
        int nbRoomsCastle = 0;
        Stage[] stages = this.castle.getStages();
        for (int i = 0 ; i < stages.length ; i++){
            Room[] rooms = stages[i].getRooms();
            for (int y = 0 ; y < rooms.length ; y++){
                nbRoomsCastle +=1;
                if (rooms[i].isVisible()){
                    nbRoomsVisited +=1;
                }
            }
        }
        statistic.setNbRoomsVisited(nbRoomsVisited);
        statistic.setNbRoomsCastle(nbRoomsCastle);
        statistic.calculScore();

        display = false;
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
    }

    @Override
    public String[] render() {
        int length = this.getContentPane().getLength();

        this.getFooter().getComponents().clear();
        this.playerStats.setLength(length / 2);
        this.getFooter().getComponents().add(this.playerStats);

        if (this.player.hasPet()) {
            EntityStats petStats = new EntityStats(this.player.getPet(), Orientation.VERTICAL);
            petStats.setHeight(2);
            petStats.setLength(length / 2);
            this.getFooter().getComponents().add(petStats);
        }

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
        return puzzles.remove(new Random().nextInt(0, puzzles.size()));
    }

    public Statistic getStatistics() {
        return statistic;
    }
}
