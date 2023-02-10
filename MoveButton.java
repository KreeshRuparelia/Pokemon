import javax.swing.*;
import java.awt.*;
import java.io.*;

class MoveButton extends JButton {
    private String displayText;
    private int x;
    private int y;
    private int width;
    private int height;

    public MoveButton(String displayText, int x, int y, int width, int height) {
        this.displayText = displayText;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void initializeProperties() {
        this.setText(displayText);
        this.setBounds(x, y, width, height);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(30f);
            this.setFont(font);
        } 
        catch (FontFormatException | IOException e) {
            this.setFont(new Font("Courier", Font.PLAIN, 30));
        }

        this.setBackground(Color.GREEN);
        this.setOpaque(true);
    }   
}