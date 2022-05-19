package net.cnam.chateau.gui.component;

// ┌┐└┘├┤─│┴┯
public class CFrame extends CComponent {

    private CLabel title;
    private CPanel contentPane;

    public CFrame(int length, int height) {
        this(HorizontalAlignment.CENTER, length, height);
    }

    public CFrame(HorizontalAlignment horizontalAlignment, int length, int height) {
        this(horizontalAlignment, null, length, height);
    }

    public CFrame(CLabel title, int length, int height) {
        this(HorizontalAlignment.CENTER, title, length, height);
    }

    public CFrame(HorizontalAlignment horizontalAlignment, CLabel title, int length, int height) {
        super(horizontalAlignment, length, height);

        this.title = title;
        int panelHeight = height - 2;
        if (title != null) {
            this.title.setLength(length);
            panelHeight -= title.getHeight();
            panelHeight--;
        }
        this.contentPane = new CPanel(length - 2, panelHeight);
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
        for (String str : contentPane.render()) {
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
    public void onKeyPressed(int key) {
        this.contentPane.onKeyPressed(key);
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
        this.contentPane.setHeight(panelHeight);
    }

    public CPanel getContentPane() {
        return contentPane;
    }

    public void setContentPane(CPanel contentPane) {
        this.contentPane = contentPane;
        int panelHeight = this.getHeight() - 2;
        if (title != null) {
            panelHeight -= title.getHeight();
            panelHeight--;
        }
        if (this.contentPane == null) {
            this.contentPane = new CPanel(this.getLength() - 2, panelHeight);
        } else {
            this.contentPane.setSize(this.getLength() - 2, panelHeight);
        }
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        if (this.title != null) {
            this.title.setLength(length - 2);
        }
        this.contentPane.setLength(length - 2);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);

        int panelHeight = height - 2;
        if (title != null) {
            panelHeight -= title.getHeight();
            panelHeight--;
        }
        this.contentPane.setHeight(panelHeight);
    }
}
