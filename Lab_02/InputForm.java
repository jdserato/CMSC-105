package Lab02;

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
    private JTextArea taData;
    private JPanel panel4;
    private JLabel lEnterData;
    private JLabel lNum;
    private JButton btnDone;
    private JPanel panel5;
    private JCheckBox cbFile;
    private int index = 1;
    private int size;
    private ArrayList<String> list;
    private boolean integer;
    private boolean error = false;

    InputForm() {
        add(panel);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
        panel5.setVisible(false);

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
                    } else {
                        askNature();
                    }
                } else {
                    refocus(panel1, panel2);
                }
            }
        });

        btnOK2.setEnabled(false);
        askTitle();

        btnOK2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfTitle.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    refocus(panel2, panel3);
                }
            }
        });

        tfTitle.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    if (tfTitle.getText().equals("")) {
                        JOptionPane.showMessageDialog(panel, "Please enter title.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        refocus(panel2, panel3);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        btnOK3.setEnabled(false);
        askSize();

        btnOK3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean error = false;
                try {
                    size = Integer.parseInt(tfSize.getText());
                } catch (NumberFormatException e1) {
                    error = true;
                }
                if (error || size <= 0) {
                    JOptionPane.showMessageDialog(panel, "Size must be positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    tfSize.setText("");
                } else {
                    if (cbFile.isSelected()) {
                        try {
                            list = new ArrayList<>();
                            error = false;
                            BufferedReader br = new BufferedReader(new FileReader("input.in"));
                            for (int i = 0; i < Integer.parseInt(tfSize.getText()); i++) {
                                String sCurrLine;
                                if ((sCurrLine = br.readLine()) != null) {
                                    if (sCurrLine.equals("") || sCurrLine.equals("\n") || sCurrLine.equals("\t")) {
                                        JOptionPane.showMessageDialog(panel, "File error. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                        error = true;
                                        break;
                                    } else {
                                        if (sCurrLine.charAt(0) == '\n' || sCurrLine.charAt(0) == '\t') {
                                            sCurrLine = sCurrLine.substring(1, sCurrLine.length());
                                        }
                                        if (rbCategorical.isSelected()) {
                                            list.add(sCurrLine);
                                        } else {
                                            try {
                                                if (index == 1) {
                                                    try {
                                                        Double.parseDouble(sCurrLine);
                                                        integer = !sCurrLine.contains(".");
                                                    } catch (NumberFormatException e2) {
                                                        JOptionPane.showMessageDialog(panel, "File error. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                                        error = true;
                                                        break;
                                                    }
                                                }
                                                if (integer) {
                                                    int d = Integer.parseInt(sCurrLine);
                                                    list.add(d + "");
                                                } else {
                                                    double d = Double.parseDouble(sCurrLine);
                                                    list.add(d + "");
                                                }
                                            } catch (NumberFormatException e2) {
                                                JOptionPane.showMessageDialog(panel, "File error. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
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
                                refocus(panel3, panel5);
                            } else {
                                tfSize.setText("");
                            }
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(panel, "File is non-existent. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            tfSize.setText("");
                        }
                    } else {
                        refocus(panel3, panel4);
                    }
                }
            }
        });

        tfSize.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    boolean error = false;
                    int size = 0;
                    try {
                        size = Integer.parseInt(tfSize.getText());
                    } catch (NumberFormatException e1) {
                        error = true;
                    }
                    if (error || size <= 0) {
                        JOptionPane.showMessageDialog(panel, "Size must be positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
                        tfSize.setText("");
                    } else {
                        if (cbFile.isSelected()) {
                            try {
                                list = new ArrayList<>();
                                error = false;
                                BufferedReader br = new BufferedReader(new FileReader("input.in"));
                                for (int i = 0; i < Integer.parseInt(tfSize.getText()); i++) {
                                    String sCurrLine;
                                    if ((sCurrLine = br.readLine()) != null) {
                                        if (sCurrLine.equals("") || sCurrLine.equals("\n") || sCurrLine.equals("\t")) {
                                            JOptionPane.showMessageDialog(panel, "File error. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                            error = true;
                                            break;
                                        } else {
                                            if (sCurrLine.charAt(0) == '\n' || sCurrLine.charAt(0) == '\t') {
                                                sCurrLine = sCurrLine.substring(1, sCurrLine.length());
                                            }
                                            if (rbCategorical.isSelected()) {
                                                list.add(sCurrLine);
                                            } else {
                                                try {
                                                    if (index == 1) {
                                                        try {
                                                            Double.parseDouble(sCurrLine);
                                                            integer = !sCurrLine.contains(".");
                                                        } catch (NumberFormatException e2) {
                                                            JOptionPane.showMessageDialog(panel, "File error. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                                            error = true;
                                                            break;
                                                        }
                                                    }
                                                    if (integer) {
                                                        int d = Integer.parseInt(sCurrLine);
                                                        list.add(d + "");
                                                    } else {
                                                        double d = Double.parseDouble(sCurrLine);
                                                        list.add(d + "");
                                                    }
                                                } catch (NumberFormatException e2) {
                                                    JOptionPane.showMessageDialog(panel, "File error. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
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
                                    refocus(panel3, panel5);
                                } else {
                                    tfSize.setText("");
                                }
                            } catch (IOException e1) {
                                JOptionPane.showMessageDialog(panel, "File is non-existent. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                                tfSize.setText("");
                            }
                        } else {
                            refocus(panel3, panel4);
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        list = new ArrayList<>();

        taData.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) {
                    if (taData.getText().equals("") || taData.getText().equals("\n") || taData.getText().equals("\t")) {
                        JOptionPane.showMessageDialog(panel, "Please enter a datum.", "Error", JOptionPane.ERROR_MESSAGE);
                        taData.setText("");
                    } else {
                        String str = taData.getText();
                        if (str.charAt(0) == '\n' || str.charAt(0) == '\t') {
                            str = str.substring(1, str.length());
                        }
                        if (rbCategorical.isSelected()) {
                            list.add(str);
                            askData();
                        } else {
                            try {
                                if (index == 1) {
                                    try {
                                        Double.parseDouble(str);
                                        integer = !str.contains(".");
                                        error = false;
                                    } catch (NumberFormatException e2) {
                                        JOptionPane.showMessageDialog(panel, "Please enter a numerical value.", "Error", JOptionPane.ERROR_MESSAGE);
                                        error = true;
                                    }
                                }
                                if (!error) {
                                    if (integer) {
                                        int d = Integer.parseInt(str);
                                        list.add(d + "");
                                    } else {
                                        double d = Double.parseDouble(str);
                                        list.add(d + "");
                                    }
                                    askData();
                                }
                            } catch (NumberFormatException e2) {
                                if (integer) {
                                    JOptionPane.showMessageDialog(panel, "Please input an integer.", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(panel, "Please input a floating number.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        taData.setText("");
                    }
                } else if (e.getKeyCode() == VK_TAB) {
                    JOptionPane.showMessageDialog(panel, "Tab character is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                    taData.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        btnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ViewSample vs = new ViewSample(list, tfTitle.getText(), rbCategorical.isSelected());
            }
        });
    }

    private void askSize() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!tfSize.getText().equals("")) {
                btnOK3.setEnabled(true);
                break;
            }
        }
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

    private void askTitle() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!tfTitle.getText().equals("")) {
                btnOK2.setEnabled(true);
                break;
            }
        }
    }

    private void refocus(JPanel prev, JPanel next) {
        for (Component c : prev.getComponents()) {
            c.setEnabled(false);
        }
        next.setVisible(true);
    }

    private void askData() {
        if (++index > Integer.parseInt(tfSize.getText())) {
            System.out.println(list);
            refocus(panel4, panel5);
        } else {
            lNum.setText(index + ". ");
        }
    }

    ArrayList<String> getList() {
        return list;
    }
}