package net.cnam.chateau.gui.component;

import net.cnam.chateau.event.key.KeyListener;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.utils.StringUtils;

// ┌┐└┘├┤─│┴┯
public class CFrame extends CComponent implements KeyListener {
    private CPanel header;
    private CPanel contentPane;
    private CPanel footer;

    public CFrame(int length, int height) {
        super(null, length, height);

        this.contentPane = new CPanel(0, 0);

        autoResize();
    }

    public CFrame(int length, int height, String title) {
        this(length, height);

        String[] titleLines = StringUtils.convertStringToStringArray(title);
        CPanel header = new CPanel(0, titleLines.length);
        header.getComponents().add(new CLabel(titleLines));

        setHeader(header);
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;

        linePointer = renderAddLine(result, linePointer, '┌' + "─".repeat(this.getLength() - 2) + '┐');

        // Header
        if (this.header != null && this.header.getHeight() > 0) {
            String[] headerRender = this.header.render();
            for (String line : headerRender) {
                linePointer = renderAddLine(result, linePointer, '│' + line + '│');
            }
            linePointer = renderAddLine(result, linePointer, '├' + "─".repeat(this.getLength() - 2) + '┤');
        }

        // Content
        for (String line : contentPane.render()) {
            linePointer = renderAddLine(result, linePointer, '│' + line + '│');
        }

        // Footer
        if (this.footer != null && this.footer.getHeight() > 0) {
            linePointer = renderAddLine(result, linePointer, '├' + "─".repeat(this.getLength() - 2) + '┤');
            String[] footerRender = this.footer.render();
            for (String line : footerRender) {
                linePointer = renderAddLine(result, linePointer, '│' + line + '│');
            }
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
    public void onKeyPressed(KeyPressedEvent event) {
        if (this.header != null) {
            this.header.onKeyPressed(event);
        }
        this.contentPane.onKeyPressed(event);
        if (this.footer != null) {
            this.footer.onKeyPressed(event);
        }
    }

    public CPanel getHeader() {
        return header;
    }

    public void setHeader(CPanel header) {
        this.header = header;

        autoResize();
    }

    public CPanel getContentPane() {
        return contentPane;
    }

    public void setContentPane(CPanel contentPane) {
        this.contentPane = contentPane;

        autoResize();
    }

    public CPanel getFooter() {
        return footer;
    }

    public void setFooter(CPanel footer) {
        this.footer = footer;

        autoResize();
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);

        autoResize();
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);

        autoResize();
    }

    private void autoResize() {
        int contentHeight = this.getHeight() - 2;
        if (this.header != null && this.header.getHeight() > 0) {
            contentHeight -= this.header.getHeight();
            contentHeight--;
        }
        if (this.footer != null && this.footer.getHeight() > 0) {
            contentHeight -= this.footer.getHeight();
            contentHeight--;
        }
        if (contentHeight < 0) {
            contentHeight = 0;
        }
        this.contentPane.setHeight(contentHeight);

        int length = this.getLength() - 2;
        if (length < 0) {
            length = 0;
        }
        if (this.header != null) {
            this.header.setLength(length);
        }
        this.contentPane.setLength(length);
        if (this.footer != null) {
            this.footer.setLength(length);
        }
    }
}
