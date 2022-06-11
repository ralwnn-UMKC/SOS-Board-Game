package sprint_5.product;

import java.awt.*;
import javax.swing.*;

public class BluePlayerPanel { // LEFT PANEL ON GAME BOARD
    private final GameBoard board;  
    private final GameLogic game;
    
    private final JPanel panel;
    private JTextField blueScore;;
    
    public BluePlayerPanel(GameBoard board, GameLogic game) {
        this.board = board;
        this.game = game;
        this.panel = bluePlayerPanel();
    }
   
    private JPanel bluePlayerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel blueLabel = new JLabel("Blue Player");
        blueLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(blueLabel, gbc);

        // SCORE
        gbc.gridy++;;
        JLabel label = new JLabel("Score");
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label, gbc);
        gbc.gridy++;
        blueScore = new JTextField(3);
        blueScore.setFont(new Font("Tahoma", Font.BOLD, 12));
        blueScore.setEditable(false);
        blueScore.setHorizontalAlignment(JTextField.CENTER);
        panel.add(blueScore, gbc);

        // UPDATE SCORE
        updateScore();

        return panel;
    }
    
    // UPDATES SCORE IN JTEXTFIELD
    public void updateScore() {
        int[] score = game.getScore();
        blueScore.setText(Integer.toString(score[0]));
    }

    public JPanel getPanel() {
        return panel;
    }
}