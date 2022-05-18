package net.cnam.chateau.gui;

// Sources ANSI codes:
// https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
// https://askubuntu.com/questions/558280/changing-colour-of-text-and-background-of-terminal
// https://en.wikipedia.org/wiki/ANSI_escape_code
public enum CColor {

    RESET(0, 0, 0, 0),
    BOLD(1, 1, 22, 22),
    DIM(2, 2, 22, 22),
    ITALIC(3, 3, 23, 23),
    UNDERLINE(4, 4, 24, 24),
    BLINKING(5, 5, 25, 25),
    REVERSE(7, 7, 27, 27),
    HIDDEN(8, 8, 28, 28),
    STRIKETHROUGH(9, 9, 29, 29),
    BLACK(33),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    WHITE(37),
    BRIGHT_BLACK(90),
    BRIGHT_RED(91),
    BRIGHT_GREEN(91),
    BRIGHT_YELLOW(93),
    BRIGHT_BLUE(94),
    BRIGHT_MAGENTA(95),
    BRIGHT_CYAN(96),
    BRIGHT_WHITE(97);

    private static final String PREFIX = "\033[";
    private static final String SUFFIX = "m";
    private static final int FOREGROUND_RESET_CODE = 39;
    private static final int BACKGROUND_RESET_CODE = 49;

    private final int foregroundCode;
    private final int backgroundCode;
    private final int foregroundResetCode;
    private final int backgroundResetCode;

    private CColor(int foregroundCode) {
        this(foregroundCode, foregroundCode + 10);
    }

    private CColor(int foregroundCode, int backgroundCode) {
        this(foregroundCode, backgroundCode, FOREGROUND_RESET_CODE, BACKGROUND_RESET_CODE);
    }

    private CColor(int foregroundCode, int backgroundCode, int foregroundResetCode, int backgroundResetCode) {
        this.foregroundCode = foregroundCode;
        this.backgroundCode = backgroundCode;
        this.foregroundResetCode = foregroundResetCode;
        this.backgroundResetCode = backgroundResetCode;
    }

    public String getForeground() {
        return PREFIX + foregroundCode + SUFFIX;
    }

    public String getBackground() {
        return PREFIX + backgroundCode + SUFFIX;
    }

    public String getForegroundReset() {
        return PREFIX + foregroundResetCode + SUFFIX;
    }

    public String getBackgroundReset() {
        return PREFIX + backgroundResetCode + SUFFIX;
    }

    @Override
    public String toString() {
        return getForeground();
    }
}
