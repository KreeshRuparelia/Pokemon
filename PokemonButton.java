import javax.swing.*;
import java.awt.*;
import java.io.*;

class PokemonButton extends JButton {
    private Player player;
    private int pokemonIndex;
    private int x;
    private int y;
    private int width;
    private int height;

    public PokemonButton(Player player, int pokemonIndex, int x, int y, int width, int height) {
        this.player = player;
        this.pokemonIndex = pokemonIndex - 1;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void initializeProperties() {
        this.setText(player.getAllPokemon()[pokemonIndex].getPokemonName());
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


        if (player.getAllPokemon()[pokemonIndex].getCurrentHealth() > 0 && !player.getAllPokemon()[pokemonIndex].equals(player.getCurrentPokemon())) {
            this.setBackground(Color.BLUE);
        }

        else this.setBackground(Color.GRAY);

        this.setOpaque(true);
    }
}