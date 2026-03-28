package pro1.swingComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private final DisplayPanel displayPanel;

    public MainFrame() {
        this.setTitle("PRO1 Drawing");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());

        this.displayPanel = new DisplayPanel();
        this.add(this.displayPanel, BorderLayout.CENTER);

        JPanel leftPanel = createControlPanel();
        this.add(leftPanel, BorderLayout.WEST);

        this.displayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                displayPanel.addCircle(e.getPoint());
            }
        });

        this.setVisible(true);
    }

    private JPanel createControlPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(250, 0));
        leftPanel.setMinimumSize(new Dimension(150, 0));
        leftPanel.setMaximumSize(new Dimension(450, Integer.MAX_VALUE));
        leftPanel.setBorder(new EmptyBorder(20, 16, 20, 16));
        leftPanel.setBackground(new Color(245, 245, 245));

        JLabel radiusLabel = new JLabel("Polomer kolecek");
        radiusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSlider radiusSlider = new JSlider(5, 150, this.displayPanel.getPolomer());
        radiusSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        radiusSlider.setMajorTickSpacing(25);
        radiusSlider.setMinorTickSpacing(5);
        radiusSlider.setPaintTicks(true);
        radiusSlider.setPaintLabels(true);
        radiusSlider.addChangeListener(e -> this.displayPanel.setPolomer(radiusSlider.getValue()));

        JCheckBox boundingBoxCheckbox = new JCheckBox("Zobrazit obdelnik", true);
        boundingBoxCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
        boundingBoxCheckbox.setOpaque(false);
        boundingBoxCheckbox.addActionListener(e -> this.displayPanel.setBoundingBoxVisible(boundingBoxCheckbox.isSelected()));

        JButton resetButton = new JButton("reset");
        resetButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        resetButton.addActionListener(e -> this.displayPanel.resetDrawing());

        leftPanel.add(radiusLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        leftPanel.add(radiusSlider);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(boundingBoxCheckbox);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(resetButton);
        leftPanel.add(Box.createVerticalGlue());

        return leftPanel;
    }
}