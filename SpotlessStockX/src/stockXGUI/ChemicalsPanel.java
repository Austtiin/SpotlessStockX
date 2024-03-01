// Purpose: Contains the ChemicalsPanel class which is a JPanel that contains the title and add button for the chemicals panel.


package stockXGUI;

import javax.swing.*;
import java.awt.*;

public class ChemicalsPanel extends JPanel {
    public ChemicalsPanel() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        // Title / Components Label
        JLabel titleLabel = new JLabel("Chemicals Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Add Button
        JButton addButton = new JButton("Add Chemical");
        add(addButton, BorderLayout.CENTER);
    }
}