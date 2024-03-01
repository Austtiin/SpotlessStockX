package stockXGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class Frame {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Frame window = new Frame();
                window.frame.setUndecorated(true);
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @wbp.parser.entryPoint
     */
    public Frame() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(34, 31, 32));
        frame.setBounds(100, 100, 1029, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panelDeco mainPanel = new panelDeco();
        mainPanel.setRoundTopRight(20);
        mainPanel.setRoundBottomRight(20);
        mainPanel.setRoundTopLeft(20);
        mainPanel.setRoundBottomLeft(20);
        mainPanel.setBounds(10, 11, 200, 589);
        mainPanel.setBackground(new Color(255, 255, 204));
        frame.getContentPane().add(mainPanel);

        panelDeco headerPanel = new panelDeco();
        headerPanel.setRoundTopRight(20);
        headerPanel.setRoundBottomRight(20);
        headerPanel.setRoundTopLeft(20);
        headerPanel.setRoundBottomLeft(20);
        headerPanel.setBounds(10, 11, 200, 589);
        headerPanel.setBackground(new Color(255, 255, 204));
        frame.getContentPane().add(headerPanel);

        // Our Vertical. Menu
        JToolBar menuBar = new JToolBar(JToolBar.VERTICAL);
        menuBar.setFloatable(false);
        menuBar.setOpaque(false);

        // Insert buttons
        JButton homeButton = createMenuButton("Home");
        JButton chemicalsButton = createMenuButton("Chemicals");
        JButton sitesButton = createMenuButton("Sites");
        JButton generateBOLButton = createMenuButton("Generate BOL");

        // Add buttons to the menuBar
        menuBar.add(homeButton);
        menuBar.add(chemicalsButton);
        menuBar.add(sitesButton);
        menuBar.add(generateBOLButton);
        menuBar.setBounds(0, 0, 120, mainPanel.getHeight());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(menuBar, BorderLayout.WEST);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                int arcWidth = 20;
                int arcHeight = 20;
                frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), arcWidth, arcHeight));
            }
        });
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.GRAY);
                button.setFont(new Font("Arial", Font.BOLD, 16));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.BLACK);
                button.setFont(new Font("Arial", Font.BOLD, 14));
            }
        });

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        return button;
    }
}