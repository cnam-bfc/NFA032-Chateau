package net.cnam.gui.component;

// ┌┐└┘├┤─│┴┯
public class CFrame extends CComponent {

    private static final int DEFAULT_LENGTH = 50;
    private static final int DEFAULT_HEIGHT = 5;

    private CLabel title;
    private CPanel content;

    public CFrame() {
        this(null);
    }

    public CFrame(CLabel title) {
        this(title, DEFAULT_LENGTH, DEFAULT_HEIGHT);
    }

    public CFrame(int length, int height) {
        this(null, length, height);
    }

    public CFrame(CLabel title, int length, int height) {
        super(length, height);

        this.title = title;
        int panelHeight = height - 2;
        if (title != null) {
            this.title.setLength(length);
            panelHeight -= title.getHeight();
            panelHeight--;
        }
        this.content = new CPanel(length - 2, panelHeight);
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;

        linePointer = renderAddLine(result, linePointer, '┌' + "─".repeat(this.getLength() - 2) + '┐');
        if (title != null) {
            String[] titleRender = title.render();
            for (int i = 0; i < title.getHeight(); i++) {
                if (i < titleRender.length) {
                    linePointer = renderAddLine(result, linePointer, '│' + titleRender[i] + '│');
                } else {
                    linePointer = renderAddLine(result, linePointer, '│' + " ".repeat(this.getLength() - 2) + '│');
                }
            }
            linePointer = renderAddLine(result, linePointer, '├' + "─".repeat(this.getLength() - 2) + '┤');
        }
        for (String str : content.render()) {
            linePointer = renderAddLine(result, linePointer, '│' + str + '│');
        }
        renderAddLine(result, linePointer, '└' + "─".repeat(this.getLength() - 2) + '┘');

        return result;
    }

    private int renderAddLine(String[] lines, int linePointer, String line) {
        if (!(linePointer < lines.length)) {
            return linePointer;
        }

        lines[linePointer] = line;
        return ++linePointer;
    }

    @Override
    public void keyPressed(int key) {
        this.content.keyPressed(key);
    }

    public boolean hasTitle() {
        return title != null;
    }

    public CLabel getTitle() {
        return title;
    }

    public void setTitle(CLabel title) {
        this.title = title;
        int panelHeight = this.getHeight() - 2;
        if (this.title != null) {
            this.title.setLength(this.getLength() - 2);
            panelHeight -= this.title.getHeight();
            panelHeight--;
        }
        this.content.setHeight(panelHeight);
    }

    public CPanel getContent() {
        return content;
    }

    public void setContent(CPanel content) {
        this.content = content;
        int panelHeight = this.getHeight() - 2;
        if (title != null) {
            panelHeight -= title.getHeight();
            panelHeight--;
        }
        if (this.content == null) {
            this.content = new CPanel(this.getLength() - 2, panelHeight);
        } else {
            this.content.setSize(this.getLength() - 2, panelHeight);
        }
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        if (this.title != null) {
            this.title.setLength(length - 2);
        }
        this.content.setLength(length - 2);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);

        int panelHeight = height - 2;
        if (title != null) {
            panelHeight -= title.getHeight();
            panelHeight--;
        }
        this.content.setHeight(panelHeight);
    }
}
