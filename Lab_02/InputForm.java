package Lab02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Semora on March 17, 2017
 */
public class InputForm extends JFrame{
    private JPanel panel;
    private JTextArea taMenuHeader;
    private JRadioButton rbCategorical;
    private JRadioButton rbNumerical;
    private JRadioButton rbQuit;
    private JButton btnOK1;
    private JTextField tfTitle;
    private JPanel panel2;
    private JLabel lTitle;
    private JPanel panel1;
    private JButton btnOK2;
    private JTextField tfSize;
    private JLabel lSize;
    private JButton btnOK3;
    private JPanel panel3;
    private JTextArea textArea1;
    private JPanel panel4;

    InputForm() {
        add(panel);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);

        setTitle(taMenuHeader.getText());
        setVisible(true);
        setSize(625, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnOK1.setEnabled(false);

        askNature();

        btnOK1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbQuit.isSelected()) {
                    int i = JOptionPane.showConfirmDialog(panel, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        dispose();
                        panel.setVisible(false);
                    }
                    else {
                        askNature();
                    }
                } else {
                    for (Component c : panel1.getComponents()) {
                        c.setEnabled(false);
                    }
                    panel1.setEnabled(false);
                    panel2.setVisible(true);
                }
            }
        });

        btnOK2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfTitle.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    panel2.setEnabled(false);
                    panel3.setVisible(true);
                }
            }
        });

        btnOK3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean error = false;
                int size = 0;
                try {
                    size = Integer.parseInt(tfSize.getText());
                } catch (NumberFormatException e1) {
                    error = true;
                }
                if(error || size <= 0) {
                    JOptionPane.showMessageDialog(panel, "Size must be positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    tfSize.setText("");
                } else {
                    panel3.setEnabled(false);
                    panel4.setVisible(true);
                }
            }
        });
    }

    private void askNature() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (rbCategorical.isSelected() || rbNumerical.isSelected() || rbQuit.isSelected()) {
                btnOK1.setEnabled(true);
                break;
            }
        }
    }
}
