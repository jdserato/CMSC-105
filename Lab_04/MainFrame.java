import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_TAB;

/**
 * Created by Amora & Serato on April 21, 2017.
 */
public class MainFrame extends JFrame {
    JPanel panel;
    private JTextArea ThreeTaMenuHeader;
    private JRadioButton ThreeRbGrouped;
    private JRadioButton ThreeRbUngrouped;
    private JRadioButton ThreeRbQuit;
    private JButton ThreeBtnOK1;
    private JTextField ThreeTfTitle;
    private JPanel ThreePanel2;
    private JLabel ThreeLTitle;
    private JPanel ThreePanel1;
    private JButton ThreeBtnOK2;
    private JTextField ThreeTfSize;
    private JLabel ThreeLSize;
    private JButton ThreeBtnOK3;
    private JPanel ThreePanel3;
    private JTextArea ThreeTaData;
    private JPanel ThreePanel4;
    private JLabel ThreeLEnterData;
    private JLabel ThreeLNum;
    private JButton btnDone;
    private JPanel ThreePanel5;
    private JPanel pnlUngrouped;
    private JPanel pnlGrouped;
    private JTextField ThreeTfIntervals;
    private JCheckBox ThreeCbFirstOpen;
    private JCheckBox ThreeCbLastOpen;
    private JButton ThreeBtnOK4;
    private JPanel panel6;
    private JLabel ThreeLInterval;
    private int threeIndex = 1;
    private int threeSize;
    private ArrayList<String> threeList;
    private boolean threeInteger;
    private boolean threeError = false;
    static final String threeHEADER = "Descriptive Statistics";

    public MainFrame() {
        add(panel);
        setSize(675, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        threeIndex = 1;
        threeSize = 0;
        threeList = new ArrayList<>();
        threeInteger = false;
        threeError = false;
        ThreeTaMenuHeader.setText(threeHEADER);
        ThreePanel2.setVisible(false);
        pnlUngrouped.setVisible(false);
        ThreePanel3.setVisible(false);
        ThreePanel4.setVisible(false);
        ThreePanel5.setVisible(false);
        pnlGrouped.setVisible(false);

        setTitle(threeHEADER);
        setVisible(true);

        ThreeBtnOK1.setEnabled(false);
        threeAskNature();

        ThreeBtnOK1.addActionListener(e -> {
            if (ThreeRbQuit.isSelected()) {
                int i = JOptionPane.showConfirmDialog(panel, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    dispose();
                } else {
                    threeAskNature();
                }
            } else {
                refocus(ThreePanel1, ThreePanel2);
                ThreeBtnOK2.setEnabled(true);
            }
        });

        ThreeBtnOK2.setEnabled(false);
        threeAskTitle();

        ThreeBtnOK2.addActionListener(e -> {
            if (ThreeTfTitle.getText().equals("")) {
                JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (ThreeRbGrouped.isSelected()) {
                    refocus(ThreePanel2, pnlGrouped);
                    pnlGrouped.setVisible(true);
                } else {
                    refocus(ThreePanel2, pnlUngrouped);
                }
            }
        });

        ThreeTfTitle.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed( KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    if (ThreeTfTitle.getText().equals("")) {
                        JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (ThreeRbGrouped.isSelected()) {
                            refocus(ThreePanel2, pnlGrouped);
                            pnlGrouped.setVisible(true);
                        } else {
                            refocus(ThreePanel2, pnlUngrouped);
                            pnlUngrouped.setVisible(true);
                            ThreePanel3.setVisible(true);
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        if (ThreeRbUngrouped.isSelected()) {
            threeAskSize();
        } else if (ThreeRbGrouped.isSelected()){
            threeAskInterval();
        }

        if (ThreeRbUngrouped.isSelected()) { // Ungrouped Data
            ThreePanel3.setVisible(true);
            ThreeBtnOK3.setEnabled(false);
            threeAskSize();

            ThreeBtnOK3.addActionListener(e -> {
                boolean error = false;
                try {
                    threeSize = Integer.parseInt(ThreeTfSize.getText());
                } catch (NumberFormatException e1) {
                    error = true;
                }
                if (error || threeSize <= 1) {
                    JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                    ThreeTfSize.setText("");
                } else {
                    refocus(ThreePanel3, ThreePanel4);
                }
            });

            ThreeTfSize.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed( KeyEvent e) {
                    if (e.getKeyCode() == VK_ENTER) {
                        boolean error = false;
                        int size = 0;
                        try {
                            size = Integer.parseInt(ThreeTfSize.getText());
                        } catch (NumberFormatException e1) {
                            error = true;
                        }
                        if (error || size <= 1) {
                            JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                            ThreeTfSize.setText("");
                        } else {
                            refocus(ThreePanel3, ThreePanel4);
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

            threeList = new ArrayList<>();

            ThreeTaData.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed( KeyEvent e) {
                    if (e.getKeyCode() == VK_ENTER) {
                        if (ThreeTaData.getText().equals("") || ThreeTaData.getText().equals("\n") || ThreeTaData.getText().equals("\t")) {
                            JOptionPane.showMessageDialog(panel, "Please enter a datum.", "Error", JOptionPane.ERROR_MESSAGE);
                            ThreeTaData.setText("");
                        } else {
                            String str = ThreeTaData.getText();
                            if (str.charAt(0) == '\n' || str.charAt(0) == '\t') {
                                str = str.substring(1, str.length());
                            }
                            try {
                                if (threeIndex == 1) {
                                    try {
                                        Double.parseDouble(str);
                                        threeInteger = !str.contains(".");
                                        threeError = false;
                                    } catch (NumberFormatException e2) {
                                        JOptionPane.showMessageDialog(panel, "Please enter a numerical value.", "Error", JOptionPane.ERROR_MESSAGE);
                                        threeError = true;
                                    }
                                }
                                if (!threeError) {
                                    if (threeInteger) {
                                        int d = Integer.parseInt(str);
                                        threeList.add(d + "");
                                    } else {
                                        double d = Double.parseDouble(str);
                                        threeList.add(d + "");
                                    }
                                    threeAskData();
                                }
                            } catch (NumberFormatException e2) {
                                if (threeInteger) {
                                    JOptionPane.showMessageDialog(panel, "Please input an threeInteger.", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(panel, "Please input a floating number.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            ThreeTaData.setText("");
                        }
                    } else if (e.getKeyCode() == VK_TAB) {
                        JOptionPane.showMessageDialog(panel, "Tab character is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                        ThreeTaData.setText("");
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

        } else { // Grouped Data
            ThreeTfIntervals.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed( KeyEvent e) {
                    if (e.getKeyCode() == VK_ENTER) {
                        boolean error = false;
                        try {
                            threeSize = Integer.parseInt(ThreeTfIntervals.getText());
                        } catch (NumberFormatException e1) {
                            error = true;
                        }
                        if (error || threeSize <= 1) {
                            JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                            ThreeTfIntervals.setText("");
                        } else {
                            refocus(panel6, ThreePanel5);
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

            ThreeBtnOK4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean error = false;
                    try {
                        threeSize = Integer.parseInt(ThreeTfIntervals.getText());
                    } catch (NumberFormatException e1) {
                        error = true;
                    }
                    if (error || threeSize <= 1) {
                        JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                        ThreeTfIntervals.setText("");
                    } else {
                        refocus(panel6, ThreePanel5);
                    }
                }
            });
        }

        btnDone.addActionListener(e -> {

            ThreePanel1.setVisible(true);
            ThreeBtnOK1.setEnabled(false);
            ThreePanel1.setEnabled(true);
            ThreePanel2.setVisible(false);
            ThreePanel3.setVisible(false);
            ThreePanel4.setVisible(false);
            ThreePanel5.setVisible(false);
            ThreePanel1.setEnabled(false);
            pnlGrouped.setVisible(false);
            pnlUngrouped.setVisible(false);
            threeIndex = 1;
            ThreeLNum.setText("1.");
            threeInteger = false;
            threeError = false;

            if (ThreeRbUngrouped.isSelected()) {
                new Editing(threeList, ThreeTfTitle.getText());
            } else {
                new Creating(threeSize, ThreeTfTitle.getText(), ThreeCbFirstOpen.isSelected(), ThreeCbLastOpen.isSelected());
            }
            threeList = new ArrayList<>();

            threeSize = 0;
            ThreeTfSize.setText("");
            ThreeTfTitle.setText("");
            ThreeTaData.setText("");

            ThreePanel1.setEnabled(true);
            threeRegain(ThreePanel1.getComponents());
            threeRegain(ThreePanel2.getComponents());
            threeRegain(ThreePanel3.getComponents());
            threeRegain(ThreePanel4.getComponents());
            threeRegain(ThreePanel5.getComponents());
            threeRegain(panel6.getComponents());
            threeRegain(pnlGrouped.getComponents());
            threeRegain(pnlUngrouped.getComponents());

            Semora.in.setEnabled(false);
            Semora.in.hide();
            ThreePanel1.setEnabled(true);
        });
    }

    private void threeRegain(Component[] components) {
        for (Component c : components) {
            c.setEnabled(true);
        }
    }

    private void threeAskInterval() {
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!ThreeTfIntervals.getText().equals("")) {
                ThreeBtnOK4.setEnabled(true);
                break;
            }
        }
    }

    private void threeAskSize() {
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!ThreeTfSize.getText().equals("")) {
                ThreeBtnOK3.setEnabled(true);
                break;
            }
        }
    }

    private void threeAskNature() {
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (ThreeRbGrouped.isSelected() || ThreeRbUngrouped.isSelected() || ThreeRbQuit.isSelected()) {
                ThreeBtnOK1.setEnabled(true);
                break;
            }
        }
    }

    private void threeAskTitle() {
        ThreePanel1.setEnabled(true);
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!ThreeTfTitle.getText().equals("")) {
                ThreeBtnOK2.setEnabled(true);
                break;
            }
        }
    }

    private void threeAskData() {
        if (++threeIndex > Integer.parseInt(ThreeTfSize.getText())) {
            refocus(ThreePanel4, ThreePanel5);
        } else {
            ThreeLNum.setText(threeIndex + ". ");
        }
    }

    private void refocus( JPanel prev,  JPanel next) {
        for (Component c : prev.getComponents()) {
            c.setEnabled(false);
        }
        next.setVisible(true);
    }
}