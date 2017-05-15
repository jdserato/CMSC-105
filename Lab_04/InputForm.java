import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_TAB;

/**
 * Created by Semora on March 17, 2017
 */
public class InputForm extends JFrame{
    JPanel panel;
    private JTextArea TwoTaMenuHeader;
    private JRadioButton TwoRbCategorical;
    private JRadioButton TwoRbNumerical;
    private JRadioButton TwoRbQuit;
    private JButton TwoBtnOK1;
    private JTextField TwoTfTitle;
    private JPanel TwoPanel2;
    private JLabel TwoLTitle;
    private JPanel TwoPanel1;
    private JButton TwoBtnOK2;
    private JTextField TwoTfSize;
    private JLabel TwoLSize;
    private JButton TwoBtnOK3;
    private JPanel TwoPanel3;
    private JTextArea TwoTaData;
    private JPanel TwoPanel4;
    private JLabel TwoLEnterData;
    private JLabel TwoLNum;
    private JButton TwoBtnDone;
    private JPanel TwoPanel5;
    private JCheckBox TwoCbFile;
    private int twoIndex = 1;
    private int twoSize;
    private ArrayList<String> twoList;
    private boolean twoInteger;
    private boolean twoError = false;

    public InputForm() {
        twoIndex = 1;
        twoSize = 0;
        twoList = new ArrayList<>();
        twoInteger = false;
        twoError = false;
        add(panel);
        TwoPanel2.setVisible(false);
        TwoPanel3.setVisible(false);
        TwoPanel4.setVisible(false);
        TwoPanel5.setVisible(false);
        setTitle(TwoTaMenuHeader.getText());
        setVisible(true);
        setSize(625, 450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        TwoBtnOK1.setEnabled(false);
        twoAskNature();

        TwoBtnOK1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TwoRbQuit.isSelected()) {
                    int i = JOptionPane.showConfirmDialog(panel, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                    if (i == 0) {
                        dispose();
                    } else {
                        twoAskNature();
                    }
                } else {
                    refocus(TwoPanel1, TwoPanel2);
                }
            }
        });

        TwoBtnOK2.setEnabled(false);
        twoAskTitle();

        TwoBtnOK2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (TwoTfTitle.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    refocus(TwoPanel2, TwoPanel3);
                }
            }
        });

        TwoTfTitle.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed( KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    if (TwoTfTitle.getText().equals("")) {
                        JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        refocus(TwoPanel2, TwoPanel3);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        TwoBtnOK3.setEnabled(false);
        twoAskSize();

        TwoBtnOK3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean error = false;
                try {
                    twoSize = Integer.parseInt(TwoTfSize.getText());
                } catch (NumberFormatException e1) {
                    error = true;
                }
                if (error || twoSize <= 0) {
                    JOptionPane.showMessageDialog(panel, "Size must be positive twoInteger.", "Error", JOptionPane.ERROR_MESSAGE);
                    TwoTfSize.setText("");
                } else {
                    if (TwoCbFile.isSelected()) {
                        try {
                            twoList = new ArrayList<>();
                            error = false;
                            BufferedReader br = new BufferedReader(new FileReader("input.in"));
                            for (int i = 0; i < Integer.parseInt(TwoTfSize.getText()); i++) {
                                String sCurrLine;
                                if ((sCurrLine = br.readLine()) != null) {
                                    if (sCurrLine.equals("") || sCurrLine.equals("\n") || sCurrLine.equals("\t")) {
                                        JOptionPane.showMessageDialog(panel, "File twoError. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                        error = true;
                                        break;
                                    } else {
                                        if (sCurrLine.charAt(0) == '\n' || sCurrLine.charAt(0) == '\t') {
                                            sCurrLine = sCurrLine.substring(1, sCurrLine.length());
                                        }
                                        if (TwoRbCategorical.isSelected()) {
                                            twoList.add(sCurrLine);
                                        } else {
                                            try {
                                                if (twoIndex == 1) {
                                                    try {
                                                        Double.parseDouble(sCurrLine);
                                                        twoInteger = !sCurrLine.contains(".");
                                                    } catch (NumberFormatException e2) {
                                                        JOptionPane.showMessageDialog(panel, "File twoError. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                                        error = true;
                                                        break;
                                                    }
                                                }
                                                if (twoInteger) {
                                                    int d = Integer.parseInt(sCurrLine);
                                                    twoList.add(d + "");
                                                } else {
                                                    double d = Double.parseDouble(sCurrLine);
                                                    twoList.add(d + "");
                                                }
                                            } catch (NumberFormatException e2) {
                                                JOptionPane.showMessageDialog(panel, "File twoError. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                                error = true;
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(panel, "Size exceeds number of datum. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                    error = true;
                                    break;
                                }
                            } if (!error) {
                                refocus(TwoPanel3, TwoPanel5);
                            } else {
                                TwoTfSize.setText("");
                            }
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(panel, "File is non-existent. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            TwoTfSize.setText("");
                        }
                    } else {
                        refocus(TwoPanel3, TwoPanel4);
                    }
                }
            }
        });

        TwoTfSize.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed( KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    boolean error = false;
                    int size = 0;
                    try {
                        size = Integer.parseInt(TwoTfSize.getText());
                    } catch (NumberFormatException e1) {
                        error = true;
                    }
                    if (error || size <= 1) {
                        JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                        TwoTfSize.setText("");
                    } else {
                        if (TwoCbFile.isSelected()) {
                            try {
                                twoList = new ArrayList<>();
                                error = false;
                                BufferedReader br = new BufferedReader(new FileReader("input.in"));
                                for (int i = 0; i < Integer.parseInt(TwoTfSize.getText()); i++) {
                                    String sCurrLine;
                                    if ((sCurrLine = br.readLine()) != null) {
                                        if (sCurrLine.equals("") || sCurrLine.equals("\n") || sCurrLine.equals("\t")) {
                                            JOptionPane.showMessageDialog(panel, "File twoError. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                            error = true;
                                            break;
                                        } else {
                                            if (sCurrLine.charAt(0) == '\n' || sCurrLine.charAt(0) == '\t') {
                                                sCurrLine = sCurrLine.substring(1, sCurrLine.length());
                                            }
                                            if (TwoRbCategorical.isSelected()) {
                                                twoList.add(sCurrLine);
                                            } else {
                                                try {
                                                    if (twoIndex++ == 1) {
                                                        try {
                                                            Double.parseDouble(sCurrLine);
                                                            twoInteger = !sCurrLine.contains(".");
                                                        } catch (NumberFormatException e2) {
                                                            JOptionPane.showMessageDialog(panel, "File twoError. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                                            error = true;
                                                            break;
                                                        }
                                                    }
                                                    if (twoInteger) {
                                                        int d = Integer.parseInt(sCurrLine);
                                                        twoList.add(d + "");
                                                    } else {
                                                        double d = Double.parseDouble(sCurrLine);
                                                        twoList.add(d + "");
                                                    }
                                                } catch (NumberFormatException e2) {
                                                    JOptionPane.showMessageDialog(panel, "File twoError. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                                    error = true;
                                                    break;
                                                }
                                            }
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(panel, "Size exceeds number of datum. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                        error = true;
                                        break;
                                    }
                                } if (!error) {
                                    refocus(TwoPanel3, TwoPanel5);
                                } else {
                                    TwoTfSize.setText("");
                                }
                            } catch (IOException e1) {
                                JOptionPane.showMessageDialog(panel, "File is non-existent. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                TwoTfSize.setText("");
                            }
                        } else {
                            refocus(TwoPanel3, TwoPanel4);
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        twoList = new ArrayList<>();

        TwoTaData.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed( KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    if (TwoTaData.getText().equals("") || TwoTaData.getText().equals("\n") || TwoTaData.getText().equals("\t")) {
                        JOptionPane.showMessageDialog(panel, "Please enter a datum.", "Error", JOptionPane.ERROR_MESSAGE);
                        TwoTaData.setText("");
                    } else {
                        String str = TwoTaData.getText();
                        if (str.charAt(0) == '\n' || str.charAt(0) == '\t') {
                            str = str.substring(1, str.length());
                        }
                        if (TwoRbCategorical.isSelected()) {
                            twoList.add(str);
                            twoAskData();
                        } else {
                            try {
                                if (twoIndex == 1) {
                                    try {
                                        Double.parseDouble(str);
                                        twoInteger = !str.contains(".");
                                        twoError = false;
                                    } catch (NumberFormatException e2) {
                                        JOptionPane.showMessageDialog(panel, "Please enter a numerical value.", "Error", JOptionPane.ERROR_MESSAGE);
                                        twoError = true;
                                    }
                                }
                                if (!twoError) {
                                    if (twoInteger) {
                                        int d = Integer.parseInt(str);
                                        twoList.add(d + "");
                                    } else {
                                        double d = Double.parseDouble(str);
                                        twoList.add(d + "");
                                    }
                                    twoAskData();
                                }
                            } catch (NumberFormatException e2) {
                                if (twoInteger) {
                                    JOptionPane.showMessageDialog(panel, "Please input an twoInteger.", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(panel, "Please input a floating number.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        TwoTaData.setText("");
                    }
                } else if (e.getKeyCode() == VK_TAB) {
                    JOptionPane.showMessageDialog(panel, "Tab character is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                    TwoTaData.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        TwoBtnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwoPanel1.setVisible(true);
                TwoBtnOK1.setEnabled(false);
                TwoPanel1.setEnabled(true);
                TwoPanel2.setVisible(false);
                TwoPanel3.setVisible(false);
                TwoPanel4.setVisible(false);
                TwoPanel5.setVisible(false);
                TwoPanel1.setEnabled(false);
                twoIndex = 1;
                TwoLNum.setText("1.");
                twoSize = 0;
                twoInteger = false;
                twoError = false;

                new ViewSample(twoList, TwoTfTitle.getText(), TwoRbCategorical.isSelected());
                twoList = new ArrayList<>();

                for (Component c : panel.getComponents()) {
                    if (c instanceof JPanel) {
                        for (Component c1 : ((JPanel) c).getComponents()) {
                            c1.setEnabled(true);
                        }
                    }
                }
                TwoRbCategorical.setSelected(false);
                TwoRbNumerical.setSelected(false);
                TwoCbFile.setSelected(false);
                TwoTfSize.setText("");
                TwoTfTitle.setText("");
                TwoTaData.setText("");
                SeratoAndAmora.in.hide();
            }
        });
    }

    private void twoAskSize() {
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!TwoTfSize.getText().equals("")) {
                TwoBtnOK3.setEnabled(true);
                break;
            }
        }
    }

    private void twoAskNature() {
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (TwoRbCategorical.isSelected() || TwoRbNumerical.isSelected() || TwoRbQuit.isSelected()) {
                TwoBtnOK1.setEnabled(true);
                break;
            }
        }
    }

    private void twoAskTitle() {
        TwoPanel1.setEnabled(true);
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!TwoTfTitle.getText().equals("")) {
                TwoBtnOK2.setEnabled(true);
                break;
            }
        }
    }

    private void twoAskData() {
        if (++twoIndex > Integer.parseInt(TwoTfSize.getText())) {
            refocus(TwoPanel4, TwoPanel5);
        } else {
            TwoLNum.setText(twoIndex + ". ");
        }
    }

    private void refocus( JPanel prev,  JPanel next) {
        for (Component c : prev.getComponents()) {
            c.setEnabled(false);
        }
        next.setVisible(true);
    }
}