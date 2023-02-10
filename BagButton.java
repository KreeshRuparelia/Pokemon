import javax.swing.*;
import java.awt.*;
import java.io.*;

class BagButton extends JButton {
    private String healName;
    private int x;
    private int y;
    private int width;
    private int height;

    public BagButton(String healName, int x, int y, int width, int height) {
        this.healName = healName;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void initializeProperties() {
        this.setText(healName);
        this.setBounds(x, y, width, height);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(48f);
            this.setFont(font);
        } 
        catch (FontFormatException | IOException e) {
            this.setFont(new Font("Courier", Font.PLAIN, 48));
        }

        this.setBackground(Color.RED);
        this.setOpaque(true);
    }
}
