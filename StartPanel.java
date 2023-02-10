import javax.swing.*;
import java.awt.*;

class StartPanel extends JPanel {
    private Player player;
    private Computer computer;
    
    public StartPanel(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }

    private void initializeProperties() {
        this.setSize(1280, 720);
        this.setBackground(Color.WHITE);
        this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        this.setLayout(new BorderLayout());
    }

    private void displayBackdrop() {
        ImageIcon backdropIcon = new ImageIcon("backdrop.jpg");
        Image backdropImage = backdropIcon.getImage();
        backdropImage = backdropImage.getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        backdropIcon = new ImageIcon(backdropImage);

        JLabel imageLabel = new JLabel(backdropIcon);

        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.BOTTOM);

        this.add(imageLabel);
    }

    private void startGame() {
        JButton startButton = new JButton("START");
        startButton.setSize(200, 50);
        startButton.setAlignmentX(JLabel.CENTER);
        startButton.setAlignmentY(JLabel.CENTER);
        startButton.setBackground(Color.CYAN);

        JLabel buttonLabel = new JLabel();
        buttonLabel.add(startButton);
        buttonLabel.setBounds(75, 500, 200, 50);

        this.add(buttonLabel);

        startButton.addActionListener(e -> eventHandler());
    }

    private void eventHandler() {
        OptionsPanel optionsPanel = new OptionsPanel(player, computer);
        optionsPanel.populatePanel();
        Stack.push(optionsPanel);
    }

    public void populatePanel() {
        initializeProperties();
        startGame();
        displayBackdrop();
    }
}
