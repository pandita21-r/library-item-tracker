package com.library.app.ui;

import java.awt.*;
import javax.swing.Icon;

public class BookIcon implements Icon {
    private final int width;
    private final int height;
    private final Color coverColor;
    private final Color pageColor;
    private final Color accentColor;

    public BookIcon(int width, int height, Color coverColor, Color pageColor, Color accentColor) {
        this.width = width;
        this.height = height;
        this.coverColor = coverColor;
        this.pageColor = pageColor;
        this.accentColor = accentColor;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int coverWidth = width - 4;
        int coverHeight = height - 8;
        int spineWidth = coverWidth / 5;

        g2.setColor(pageColor);
        g2.fillRoundRect(x + 4, y + 4, coverWidth - spineWidth, coverHeight, 8, 8);

        g2.setColor(coverColor);
        g2.fillRoundRect(x + 2, y + 2, coverWidth, coverHeight, 12, 12);

        g2.setColor(accentColor);
        g2.fillRect(x + 2, y + 2, spineWidth, coverHeight);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(2f));
        g2.drawLine(x + 2, y + 8, x + coverWidth, y + 8);
        g2.drawLine(x + 2, y + coverHeight - 4, x + coverWidth, y + coverHeight - 4);
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
    }
}
