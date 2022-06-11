package sprint_5.product;

import javax.swing.*;

public class SOS implements Runnable {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new SOS());
    }

    @Override
    public void run() {
        new GameBoard(new GameLogic());
    }

}