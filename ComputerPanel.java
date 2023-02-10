import javax.swing.*;
import java.awt.*;
import java.io.*;

class ComputerPanel extends JPanel {
    private String computerMoveMessage;

    public ComputerPanel(String computerMoveMessage) {
        this.computerMoveMessage = computerMoveMessage;
    }

    private void initializeProperties() {
        this.setSize(1280, 720);
        this.setBackground(Color.WHITE);
        this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        this.setLayout(new BorderLayout());
    }

    private void displayMessage() {
        JLabel textLabel = new JLabel(computerMoveMessage);

        textLabel.setBounds(0, 0, 1280, 720);

        textLabel.setVerticalTextPosition(JLabel.CENTER);
        textLabel.setHorizontalTextPosition(JLabel.CENTER);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            textLabel.setFont(font.deriveFont(64f));
        }
        catch (FontFormatException | IOException e) {
            textLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        }

        textLabel.setBackground(Color.LIGHT_GRAY);
        textLabel.setOpaque(true);

        this.add(textLabel);
    }

    public void populatePanel() {
        initializeProperties();
        displayMessage();
    }
}