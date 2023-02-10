import javax.swing.*;
import java.awt.*;
import java.io.*;

class EndPanel extends JPanel {
    private boolean userWon;

    

    public EndPanel(boolean userWon) {
        this.userWon = userWon;
    }

    private void initializeProperties() {
        this.setSize(1280, 720);
        this.setBackground(Color.WHITE);
        this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        this.setLayout(new BorderLayout());
    }

    private void displayWinCondition() {
        JLabel textLabel = new JLabel("YOU WIN", SwingConstants.CENTER);

        textLabel.setBounds(0, 0, 1280, 720);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            textLabel.setFont(font.deriveFont(128f));
        }
        catch (FontFormatException | IOException e) {
            textLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        }

        textLabel.setBackground(Color.GREEN);
        textLabel.setOpaque(true);

        this.add(textLabel);
    }

    private void displayLossCondition() {
        JLabel textLabel = new JLabel("YOU LOSE", SwingConstants.CENTER);

        textLabel.setBounds(0, 0, 1280, 720);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            textLabel.setFont(font.deriveFont(128f));
        }
        catch (FontFormatException | IOException e) {
            textLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        }

        textLabel.setBackground(Color.RED);
        textLabel.setOpaque(true);

        textLabel.setHorizontalTextPosition(JLabel.CENTER);
        textLabel.setVerticalTextPosition(JLabel.CENTER);

        this.add(textLabel);
    }

    private void tryAgain() {
        JButton tryAgainButton = new JButton("TRY AGAIN");
        tryAgainButton.setBounds(420, 500, 440, 75);
        tryAgainButton.setFont(new Font("Courier", Font.PLAIN, 40));

        tryAgainButton.setBackground(Color.WHITE);
        tryAgainButton.setOpaque(true);

        this.add(tryAgainButton);

        tryAgainButton.addActionListener(e -> newGame());
    }

    private void newGame() { 
       Main.tryGame = true;
    }

    public void populatePanel() {
        initializeProperties();

        tryAgain();

        if (userWon) {
            displayWinCondition();
        }

        else {
            displayLossCondition();
        }
    }
}
