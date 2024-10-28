package sprint2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameButton extends JButton {
    private java.util.List<Line> lines = new ArrayList<>();

    public GameButton(String text) {
        super(text);
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        int width = getWidth();
        int height = getHeight();

        for (Line line : lines) {
            g2.setColor(line.color);
            switch (line.direction) {
                case HORIZONTAL:
                    g2.drawLine(0, height / 2, width, height / 2);
                    break;
                case VERTICAL:
                    g2.drawLine(width / 2, 0, width / 2, height);
                    break;
                case DIAGONAL_RIGHT:
                    g2.drawLine(0, 0, width, height);
                    break;
                case DIAGONAL_LEFT:
                    g2.drawLine(width, 0, 0, height);
                    break;
            }
        }
    }

    public static class Line {
        public Color color;
        public Direction direction;

        public Line(Color color, Direction direction) {
            this.color = color;
            this.direction = direction;
        }
    }

    public enum Direction {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL_RIGHT,
        DIAGONAL_LEFT
    }
}
