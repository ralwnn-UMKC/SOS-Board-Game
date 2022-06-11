package sprint_5.product;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.*;


@SuppressWarnings("serial")
public class GridPanel extends JPanel { 
    private final GameBoard board;
    private final GameLogic game;

    private Rectangle[][] cells;
    
    private final int gridSize;
    private int margin;
    
    public GridPanel(GameBoard board, GameLogic game) {
        this.board = board;
        this.game = game;
        this.gridSize = 32;
        int margin = 20;
        int gridCells = game.getMaximumSize();
        int size = gridCells * gridSize + 2 * margin;
        this.setPreferredSize(new Dimension(size, size));
        
        GameboardListener listener = new GameboardListener(board, game);
        this.addMouseListener(listener);
        calculateCells();
    }
    
    public void calculateCells() {
        int gridCells = game.getGameboardSize();
        int size = gridCells * gridSize;
        margin = (getPreferredSize().width - size) / 2;
        cells = new Rectangle[gridCells][gridCells];
        
        int x = margin;
        int y = margin;
        for (int row = 0; row < gridCells; row++) {
            for (int column = 0; column < gridCells; column++) {
                cells[row][column] = new Rectangle(x, y, gridSize, gridSize);
                x += gridSize;
            }
            x = margin;
            y += gridSize;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        
        g2d.setStroke(new BasicStroke(3f));
        g2d.setColor(Color.BLACK);
        char[][] gameboard = game.getGameboard();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                Rectangle r = cells[row][column];
                g2d.drawRect(r.x, r.y, r.width, r.height);
                
                String text = Character.toString(gameboard[row][column]);
                FontMetrics fm = g2d.getFontMetrics();
                Rectangle2D r2d = fm.getStringBounds(text, g2d);
                int x = (r.width - (int) r2d.getWidth()) / 2 + r.x;
                int y = (r.height - (int) r2d.getHeight()) / 2 + fm.getAscent() + r.y;
                g.drawString(text, x, y);
            }
        }
        
        List<CompletedSOSLine> lineSegments = game.getLineSegments();
        for (CompletedSOSLine segment : lineSegments) {
            Coordinates start = segment.getStartCoordinate();
            Coordinates end = segment.getEndCoordinate();
            Rectangle sr = cells[start.getRow()][start.getColumn()];
            Rectangle er = cells[end.getRow()][end.getColumn()];
            int spx = sr.x + sr.width / 2;
            int spy = sr.y + sr.height / 2;
            int epx = er.x + er.width / 2;
            int epy = er.y + er.height / 2;
            g2d.setColor(segment.getLineColor());
            g2d.drawLine(spx, spy, epx, epy);
        }
    }

    public int getGridWidth() {
        return gridSize; }

    public int getMargin() {
        return margin; }

    public Rectangle[][] getCells() {
        return cells;
    }
    
}
