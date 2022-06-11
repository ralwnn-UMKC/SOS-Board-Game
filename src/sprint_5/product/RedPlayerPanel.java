package sprint_5.product;

import java.awt.*;
import javax.swing.*;

public class RedPlayerPanel { // RIGHT PANEL ON GAMEBOARD
    private final GameBoard board;  
    private final GameLogic game;
    
    private final JPanel panel;
    private JTextField redScore;
    
    public RedPlayerPanel(GameBoard board, GameLogic game) {
        this.board = board;
        this.game = game;
        this.panel = redPlayerPanel();
    }
    
    private JPanel redPlayerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 5, 5, 5);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel redLabel = new JLabel("Red Player");
        redLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(redLabel, gbc);
        
        // SCORE
        gbc.gridy++;
        JLabel label = new JLabel("Score");
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label, gbc);
        gbc.gridy++;
        redScore = new JTextField(3);
        redScore.setFont(new Font("Tahoma", Font.BOLD, 12));
        redScore.setEditable(false);
        redScore.setHorizontalAlignment(JTextField.CENTER);
        panel.add(redScore, gbc);
       
        // UPDATE SCORE
        updateScore();
 
        return panel;
    }
    
    // UPDATES SCORE IN JTEXTFIELD
    public void updateScore() {
        int[] score = game.getScore();
        redScore.setText(Integer.toString(score[1]));
    }
    
    public JPanel getPanel() {
        return panel;
    }
}