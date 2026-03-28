package pro1.swingComponents;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayPanel extends JPanel {
    private final List<Point> centers = new ArrayList<>();
    private int polomer = 20;
    private boolean boundingBoxVisible = true;

    public DisplayPanel() {
        this.setBackground(Color.WHITE);
    }

    public int getPolomer() {
        return this.polomer;
    }

    public void addCircle(Point center) {
        this.centers.add(center);
        this.repaint();
    }

    public void setPolomer(int circleRadius) {
        this.polomer = circleRadius;
        this.repaint();
    }

    public void setBoundingBoxVisible(boolean boundingBoxVisible) {
        this.boundingBoxVisible = boundingBoxVisible;
        this.repaint();
    }

    public void resetDrawing() {
        this.centers.clear();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();

        if (this.boundingBoxVisible && this.centers.size() >= 2) {
            drawBoundingBox(graphics);
        }

        graphics.setColor(Color.BLACK);
        for (Point center : this.centers) {
            int prumer = this.polomer * 2;
            graphics.fillOval(center.x - this.polomer, center.y - this.polomer, prumer, prumer);
        }

        graphics.dispose();
    }

    private void drawBoundingBox(Graphics2D graphics) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Point center : this.centers) {
            minX = Math.min(minX, center.x);
            minY = Math.min(minY, center.y);
            maxX = Math.max(maxX, center.x);
            maxY = Math.max(maxY, center.y);
        }

        graphics.setColor(Color.RED);
        graphics.drawRect(minX, minY, maxX - minX, maxY - minY);
    }
}