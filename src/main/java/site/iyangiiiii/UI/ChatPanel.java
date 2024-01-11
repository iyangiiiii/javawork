package site.iyangiiiii.UI;

import javax.swing.*;
import java.awt.*;

class ChatPanel extends JPanel {
    private Image backgroundImage;

    public ChatPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
