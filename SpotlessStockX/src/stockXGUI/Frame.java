package stockXGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class Frame {
    private JFrame frame;
    private JLabel loadingLabel;
    private panelDeco contentPanel; // Add contentPanel declaration

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
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
        frame.getContentPane().setLayout(new BorderLayout());

        panelDeco mainPanel = new panelDeco();
        mainPanel.setRoundTopRight(20);
        mainPanel.setRoundBottomRight(20);
        mainPanel.setRoundTopLeft(20);
        mainPanel.setRoundBottomLeft(20);
        mainPanel.setBackground(new Color(255, 255, 204));
        frame.getContentPane().add(mainPanel, BorderLayout.WEST);

        JToolBar menuBar = new JToolBar(JToolBar.VERTICAL);
        menuBar.setFloatable(false);
        menuBar.setOpaque(false);

        JButton homeButton = createMenuButton("Home");
        JButton chemicalsButton = createMenuButton("Chemicals");
        JButton sitesButton = createMenuButton("Sites");
        JButton generateBOLButton = createMenuButton("Generate BOL");

        menuBar.add(homeButton);
        menuBar.add(chemicalsButton);
        menuBar.add(sitesButton);
        menuBar.add(generateBOLButton);
        menuBar.setBounds(0, 0, 120, mainPanel.getHeight());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(menuBar, BorderLayout.WEST);

        contentPanel = new panelDeco(); // Initialize contentPanel
        contentPanel.setRoundTopRight(20);
        contentPanel.setRoundTopLeft(20);
        contentPanel.setRoundBottomRight(20);
        contentPanel.setRoundBottomLeft(20);
        contentPanel.setBackground(new Color(255, 255, 204));
        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);

        loadingLabel = new JLabel("Loading..."); // Initialize loadingLabel
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.getContentPane().add(loadingLabel, BorderLayout.SOUTH);

        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
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

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.GRAY);
                button.setFont(new Font("Arial", Font.BOLD, 16));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.BLACK);
                button.setFont(new Font("Arial", Font.BOLD, 14));
            }
        });

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        // Add action listener for each button
        switch (text) {
            case "Home":
                button.addActionListener(e -> switchToHome());
                break;
            case "Chemicals":
                button.addActionListener(e -> switchToChemicals());
                break;
            case "Sites":
                button.addActionListener(e -> switchToSites());
                break;
            case "Generate BOL":
                button.addActionListener(e -> switchToGenerateBOL());
                break;
        }

        return button;
    }

    private void switchToHome() {
        loadingLabel.setVisible(true);
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadingLabel.setVisible(false);
                showHomeContent();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void switchToChemicals() {
        loadingLabel.setVisible(true);
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadingLabel.setVisible(false);
                showChemicalsContent();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void switchToSites() {
        loadingLabel.setVisible(true);
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadingLabel.setVisible(false);
                showSitesContent();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void switchToGenerateBOL() {
        loadingLabel.setVisible(true);
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadingLabel.setVisible(false);
                showGenerateBOLContent();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void showHomeContent() {
        JPanel homeContentPanel = new JPanel(new BorderLayout());
        homeContentPanel.add(new JLabel("Welcome to Home"), BorderLayout.CENTER);

        contentPanel.removeAll();
        contentPanel.add(homeContentPanel, "Home");
        contentPanel.revalidate();
        contentPanel.repaint();
    }


    private void showChemicalsContent() {
        JPanel chemicalsContentPanel = new JPanel(); 
        chemicalsContentPanel.add(new JLabel("Chemicals Panel"));

        contentPanel.removeAll();
        contentPanel.add(chemicalsContentPanel, "Chemicals");
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showSitesContent() {
        JPanel sitesContentPanel = new JPanel(); 
        sitesContentPanel.add(new JLabel("Sites Panel"));

        contentPanel.removeAll();
        contentPanel.add(sitesContentPanel, "Sites");
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showGenerateBOLContent() {
        JPanel generateBOLContentPanel = new JPanel(); 
        generateBOLContentPanel.add(new JLabel("Generate BOL Panel"));

        contentPanel.removeAll();
        contentPanel.add(generateBOLContentPanel, "Generate BOL");
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}