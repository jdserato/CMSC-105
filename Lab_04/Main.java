import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_TAB;

/**
 * Created by Semora on May 08, 2017.
 */

public class Main extends JFrame {
    private JLabel lTitle;
    private JTextArea lDesc;
    private JRadioButton rbLab1;
    private JRadioButton rbLab3;
    private JRadioButton rbLab2;
    private JRadioButton rbQuit;
    private JButton btnExplore;
    private JPanel panel;
    private JPanel pnlEverything;
    private JPanel onePnlFirst;
    private JRadioButton OneRbSimple;
    private JRadioButton OneRbSystematic;
    private JRadioButton OneRbStratified;
    private JRadioButton OneRbQuit;
    private JButton OneBtnOK1;
    private JLabel OneLSamplingType;
    private JPanel OnePnlSecond;
    private JLabel OneLPop;
    private JTextField OneTfPop;
    private JButton OneBtnOK2;
    private JCheckBox OneCbFile;
    private JPanel OnePnlThird;
    private JLabel OneLData;
    private JButton OneBtnOK3;
    private JTextArea OneTaData;
    private JLabel OneLNum;
    private JPanel OnePnlFifth;
    private JButton OneBtnDone;
    private JPanel OnePnlFourth;
    private JLabel OneLSampleSize;
    private JTextField OneTfSampleSize;
    private JButton OneBtnOK4;
    private JPanel pnlLabOne;
    private JPanel TwoPanel2;
    private JLabel TwoLTitle;
    private JButton TwoBtnOK2;
    private JTextField TwoTfTitle;
    private JPanel TwoPanel1;
    private JRadioButton TwoRbNumerical;
    private JRadioButton TwoRbQuit;
    private JRadioButton TwoRbCategorical;
    private JCheckBox TwoCbFile;
    private JButton TwoBtnOK1;
    private JPanel TwoPanel3;
    private JLabel TwoLSize;
    private JButton TwoBtnOK3;
    private JTextField TwoTfSize;
    private JPanel TwoPanel4;
    private JLabel TwoLEnterData;
    private JLabel TwoLNum;
    private JTextArea TwoTaData;
    private JPanel TwoPanel5;
    private JButton TwoBtnDone;
    private JTextArea TwoTaMenuHeader;
    private JPanel pnlLabTwo;
    private JPanel ThreePanel1;
    private JRadioButton ThreeRbUngrouped;
    private JRadioButton ThreeRbQuit;
    private JRadioButton ThreeRbGrouped;
    private JButton ThreeBtnOK1;
    private JPanel ThreePanel2;
    private JLabel ThreeLTitle;
    private JButton ThreeBtnOK2;
    private JTextField ThreeTfTitle;
    private JPanel ThreePanel5;
    private JButton btnDone;
    private JTextArea ThreeTaMenuHeader;
    private JPanel pnlUngrouped;
    private JPanel ThreePanel3;
    private JLabel ThreeLSize;
    private JButton ThreeBtnOK3;
    private JTextField ThreeTfSize;
    private JPanel ThreePanel4;
    private JLabel ThreeLEnterData;
    private JLabel ThreeLNum;
    private JTextArea ThreeTaData;
    private JPanel pnlGrouped;
    private JPanel panel6;
    private JLabel ThreeLInterval;
    private JButton ThreeBtnOK4;
    private JCheckBox ThreeCbFirstOpen;
    private JCheckBox ThreeCbLastOpen;
    private JTextField ThreeTfIntervals;
    private JPanel pnlLabThree;
    public static Main main;

    // LAB 1
    private ArrayList<String> oneList;
    private int oneSize;
    private boolean oneNumeric;
    private BufferedReader oneBr;
    private Sampling oneTechnique;
    private int oneSampleSize;

    // LAB 2
    private int twoIndex = 1;
    private int twoSize;
    private ArrayList<String> twoList = new ArrayList<>();
    private boolean twoInteger;
    private boolean twoError = false;

    // LAB 3
    private int threeIndex = 1;
    private int threeSize;
    private ArrayList<String> threeList = new ArrayList<>();
    private boolean threeInteger;
    private boolean threeError = false;
    static final String threeHEADER = "Descriptive Statistics";

    Main() {
        add(panel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(750, 520);
        setVisible(true);

        pnlLabOne.setVisible(false);
        pnlLabTwo.setVisible(false);
        pnlLabThree.setVisible(false);

        btnExplore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rbQuit.isSelected()) {
                    int choice = JOptionPane.showConfirmDialog(panel, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (choice == 0) {
                        dispose();
                    }
                } else if (rbLab1.isSelected() || rbLab2.isSelected() || rbLab3.isSelected()){
                    if (rbLab1.isSelected()) {
                        refocus(pnlEverything, pnlLabOne);
                    } else if (rbLab2.isSelected()) {
                        refocus(pnlEverything, pnlLabTwo);
                    } else if (rbLab3.isSelected()) {
                        refocus(pnlEverything, pnlLabThree);
                    }
                    pnlEverything.setVisible(false);
                }
            }
        });


        while (!btnExploreClicked());


        oneList = new ArrayList<>();
        OnePnlSecond.setVisible(false);
        OnePnlThird.setVisible(false);
        OnePnlFourth.setVisible(false);
        OnePnlFifth.setVisible(false);

        // LAB 1
        if (rbLab1.isSelected()) {
            oneSize = 0;

            pnlLabOne.setVisible(true);

            OneBtnOK1.setEnabled(false);
            oneAskNature();


        }

        OneBtnOK1.addActionListener(e -> {
            if (OneRbQuit.isSelected()) {
                int i = JOptionPane.showConfirmDialog(panel, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
                if (i == 0) {
                    dispose();
                } else {
                    oneAskNature();
                }
            } else {
                if (OneRbSystematic.isSelected()) {
                    oneTechnique = new SystematicSampling(oneList);
                } else if (OneRbSimple.isSelected()) {
                    oneTechnique = new SimpleRandomSampling(oneList);
                } else {
                    oneTechnique = new RevisedStratified(oneList);
                }
                refocus(onePnlFirst, OnePnlSecond);
            }
        });


        OneBtnOK2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean error = false;
                oneNumeric = false;
                try {
                    oneSize = Integer.parseInt(OneTfPop.getText());
                } catch (NumberFormatException e1) {
                    error = true;
                }
                if (error || oneSize <= 1) {
                    JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                    OneTfPop.setText("");
                } else {
                    if (OneCbFile.isSelected()) {
                        error = false;
                        try {
                            oneBr = new BufferedReader(new FileReader("input.in"));
                            for (int i = 1; i <= oneSize; i++) {
                                String newItem;
                                newItem = oneBr.readLine();
                                if (newItem == null) {
                                    error = true;
                                    break;
                                } else if (newItem.length() == 0) {
                                    i--;
                                    error = true;
                                    break;
                                } else if (i == 1) {
                                    oneNumeric = !((newItem.charAt(0) >= 'A' && newItem.charAt(0) <= 'Z') || (newItem.charAt(0) >= 'a' && newItem.charAt(0) <= 'z'));
                                }
                                for (int j = 0; j < newItem.length(); j++) {
                                    if (oneNumeric) {
                                        if (newItem.charAt(j) >= '0' && newItem.charAt(j) <= '9') {
                                            if (j + 1 == newItem.length()) {
                                                oneList.add(newItem);
                                            }
                                        } else if (!error) {
                                            i--;
                                            error = true;
                                            break;
                                        }
                                    } else {
                                        if ((newItem.charAt(j) >= 'A' && newItem.charAt(j) <= 'Z') || (newItem.charAt(j) >= 'a' && newItem.charAt(j) <= 'z')) {
                                            if (j + 1 == newItem.length()) {
                                                oneList.add(newItem);
                                            }
                                        } else if (!error) {
                                            i--;
                                            error = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        if (error) {
                            JOptionPane.showMessageDialog(panel, "An error has been found in your file. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            refocus(OnePnlSecond, OnePnlFourth);
                        }
                    } else {
                        refocus(OnePnlSecond, OnePnlThird);
                    }

                    if (!(oneTechnique instanceof RevisedStratified)) {
                        OneTfSampleSize.setText((int) Math.ceil((double) oneSize * .20) + "");
                        OneLSampleSize.setText("Enter sample size:");
                    } else {
                        OneLSampleSize.setText("Enter percentage:");
                    }
                }
            }
        });

        OneTfPop.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    oneNumeric = false;
                    boolean error = false;
                    try {
                        oneSize = Integer.parseInt(OneTfPop.getText());
                    } catch (NumberFormatException e1) {
                        error = true;
                    }
                    if (error || oneSize <= 1) {
                        JOptionPane.showMessageDialog(panel, "Size must be greater than 1.", "Error", JOptionPane.ERROR_MESSAGE);
                        OneTfPop.setText("");
                    } else {
                        if (OneCbFile.isSelected()) {
                            error = false;
                            try {
                                oneBr = new BufferedReader(new FileReader("input.in"));
                                for (int i = 1; i <= oneSize; i++) {
                                    String newItem;
                                    newItem = oneBr.readLine();
                                    if (newItem.length() == 0) {
                                        i--;
                                        error = true;
                                    } else if (i == 1) {
                                        oneNumeric = !((newItem.charAt(0) >= 'A' && newItem.charAt(0) <= 'Z') || (newItem.charAt(0) >= 'a' && newItem.charAt(0) <= 'z'));
                                    }
                                    for (int j = 0; j < newItem.length(); j++) {
                                        if (oneNumeric) {
                                            if (newItem.charAt(j) >= '0' && newItem.charAt(j) <= '9') {
                                                if (j + 1 == newItem.length()) {
                                                    oneList.add(newItem);
                                                }
                                            } else if (!error) {
                                                i--;
                                                error = true;
                                            }
                                        } else {
                                            if ((newItem.charAt(j) >= 'A' && newItem.charAt(j) <= 'Z') || (newItem.charAt(j) >= 'a' && newItem.charAt(j) <= 'z')) {
                                                if (j + 1 == newItem.length()) {
                                                    oneList.add(newItem);
                                                }
                                            } else if (!error) {
                                                i--;
                                                error = true;
                                            }
                                        }
                                    }
                                }
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            if (error) {
                                JOptionPane.showMessageDialog(panel, "An error has been found in your file. Please re-check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                refocus(OnePnlSecond, OnePnlFourth);
                            }
                        } else {
                            refocus(OnePnlSecond, OnePnlThird);
                        }

                        if (!(oneTechnique instanceof RevisedStratified)) {
                            OneTfSampleSize.setText((int) Math.ceil((double) oneSize * .20) + "");
                            OneLSampleSize.setText("Enter sample size:");
                        } else {
                            OneLSampleSize.setText("Enter percentage:");
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        OneBtnOK3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean error;
                String newItem;
                error = false;
                int i = Integer.parseInt(OneLNum.getText().substring(0, OneLNum.getText().length() - 2));
                newItem = OneTaData.getText();
                newItem = newItem.trim();

                if (newItem.length() == 0) {
                    JOptionPane.showMessageDialog(panel, "Please re-enter your entry.", "Re-entry", JOptionPane.WARNING_MESSAGE);
                    i--;
                    error = true;
                } else if (i == 1) {
                    oneNumeric = !((newItem.charAt(0) >= 'A' && newItem.charAt(0) <= 'Z') || (newItem.charAt(0) >= 'a' && newItem.charAt(0) <= 'z'));
                }
                int perCtr = 0;
                for (int j = 0; j < newItem.length(); j++) {
                    if (oneNumeric) {
                        if ((newItem.charAt(j) >= '0' && newItem.charAt(j) <= '9') || (newItem.charAt(j) == '.' && ++perCtr <= 1)) {
                            if (j + 1 == newItem.length()) {
                                if (newItem.charAt(j) == '.') {
                                    newItem = newItem.substring(0, newItem.length() - 1);
                                }
                                oneList.add(newItem);
                            }
                        } else if (!error) {
                            JOptionPane.showMessageDialog(panel, "ERROR! Input must be oneNumeric only!", "Error", JOptionPane.ERROR_MESSAGE);
                            i--;
                            error = true;
                        }
                    } else {
                        if ((newItem.charAt(j) >= 'A' && newItem.charAt(j) <= 'Z') || (newItem.charAt(j) >= 'a' && newItem.charAt(j) <= 'z')) {
                            if (j + 1 == newItem.length()) {
                                oneList.add(newItem);
                            }
                        } else if (!error) {
                            JOptionPane.showMessageDialog(panel, "ERROR! Input must be character data only!", "Error", JOptionPane.ERROR_MESSAGE);
                            i--;
                            error = true;
                        }
                    }
                }
                if (i++ != oneSize) {
                    OneLNum.setText(i + ". ");
                    OneTaData.setText("");
                } else {
                    refocus(OnePnlThird, OnePnlFourth);
                }
            }
        });


        OneTaData.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    boolean error;
                    String newItem;
                    error = false;

                    int i = Integer.parseInt(OneLNum.getText().substring(0, OneLNum.getText().length() - 2));
                    newItem = OneTaData.getText();
                    newItem = newItem.trim();

                    if (newItem.length() == 0) {
                        JOptionPane.showMessageDialog(panel, "Please re-enter your entry.", "Re-entry", JOptionPane.WARNING_MESSAGE);
                        i--;
                        error = true;
                    } else if (i == 1) {
                        oneNumeric = !((newItem.charAt(0) >= 'A' && newItem.charAt(0) <= 'Z') || (newItem.charAt(0) >= 'a' && newItem.charAt(0) <= 'z'));
                    }
                    int perCtr = 0;
                    for (int j = 0; j < newItem.length(); j++) {
                        if (oneNumeric) {
                            if ((newItem.charAt(j) >= '0' && newItem.charAt(j) <= '9') || (newItem.charAt(j) == '.' && ++perCtr <= 1)) {
                                if (j + 1 == newItem.length()) {
                                    if (newItem.charAt(j) == '.') {
                                        newItem = newItem.substring(0, newItem.length() - 1);
                                    }
                                    oneList.add(newItem);
                                }
                            } else if (!error) {
                                JOptionPane.showMessageDialog(panel, "ERROR! Input must be oneNumeric only!", "Error", JOptionPane.ERROR_MESSAGE);
                                i--;
                                error = true;
                            }
                        } else {
                            if ((newItem.charAt(j) >= 'A' && newItem.charAt(j) <= 'Z') || (newItem.charAt(j) >= 'a' && newItem.charAt(j) <= 'z')) {
                                if (j + 1 == newItem.length()) {
                                    oneList.add(newItem);
                                }
                            } else if (!error) {
                                JOptionPane.showMessageDialog(panel, "ERROR! Input must be character data only!", "Error", JOptionPane.ERROR_MESSAGE);
                                i--;
                                error = true;
                            }
                        }
                    }
                    if (i++ != oneSize) {
                        OneLNum.setText(i + ". ");
                        OneTaData.setText("");
                    } else {
                        refocus(OnePnlThird, OnePnlFourth);
                    }
                }
            }
        });

        OneBtnOK4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean error = false;
                oneSampleSize = 0;
                int max = oneTechnique instanceof RevisedStratified ? 100 : oneSize;

                try {
                    oneSampleSize = Integer.parseInt(OneTfSampleSize.getText());
                } catch (InputMismatchException | NumberFormatException e1) {
                    error = true;
                }
                if (oneSampleSize <= 0 || oneSampleSize >= max) {
                    error = true;
                }

                if (error) {
                    JOptionPane.showMessageDialog(panel, "Number must be greater than 0 but less than " + max + ".");
                } else {
                    refocus(OnePnlFourth, OnePnlFifth);
                }
            }
        });

        OneBtnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.hide();
                regain(pnlLabOne);
                regain(OnePnlSecond);
                pnlLabOne.setVisible(false);
                onePnlFirst.setVisible(true);
                new ViewPopulation(oneList, oneTechnique, oneSampleSize);
                OneTfPop.setText("");
                OneTaData.setText("");
                OneBtnOK2.setEnabled(true);
                OneTfSampleSize.setText("20");
                OneLNum.setText("1. ");
                OneCbFile.setSelected(false);
                oneList.clear();
            }
        });

        OneTfSampleSize.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    boolean error = false;
                    oneSampleSize = 0;
                    int max = oneTechnique instanceof RevisedStratified ? 100 : oneSize;

                    try {
                        oneSampleSize = Integer.parseInt(OneTfSampleSize.getText());
                    } catch (InputMismatchException | NumberFormatException e1) {
                        error = true;
                    }
                    if (oneSampleSize <= 0 || oneSampleSize >= max) {
                        error = true;
                    }

                    if (error) {
                        JOptionPane.showMessageDialog(panel, "Number must be greater than 0 but less than " + max + ".");
                    } else {
                        refocus(OnePnlFourth, OnePnlFifth);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        TwoPanel2.setVisible(false);
        TwoPanel3.setVisible(false);
        TwoPanel4.setVisible(false);
        TwoPanel5.setVisible(false);

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
            public void keyPressed(KeyEvent e) {
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
                            }
                            if (!error) {
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
            public void keyPressed(KeyEvent e) {
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
                                }
                                if (!error) {
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

        if (rbLab2.isSelected()) {
            // LAB 2
            twoIndex = 1;
            twoSize = 0;
            twoList = new ArrayList<>();
            twoInteger = false;
            twoError = false;
            add(panel);
            setTitle(TwoTaMenuHeader.getText());
            setVisible(true);
            setSize(625, 450);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            TwoBtnOK1.setEnabled(false);
            twoAskNature();



            TwoBtnOK2.setEnabled(false);
            twoAskTitle();



            TwoBtnOK3.setEnabled(false);
            twoAskSize();



            twoList = new ArrayList<>();




        }

        TwoTaData.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
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

                regain(pnlLabTwo);
                pnlLabTwo.setVisible(false);
                TwoPanel1.setVisible(true);
                pnlEverything.setVisible(true);
                hide();
            }
        });

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

            hide();
            ThreePanel1.setEnabled(true);
            pnlLabThree.setVisible(false);
            regain(pnlEverything);
            pnlEverything.setVisible(true);
        });

        ThreePanel2.setVisible(false);
        pnlUngrouped.setVisible(false);
        ThreePanel3.setVisible(false);
        ThreePanel4.setVisible(false);
        ThreePanel5.setVisible(false);
        pnlGrouped.setVisible(false);

        // LAB 3
        if (rbLab3.isSelected()) {
            threeIndex = 1;
            threeSize = 0;
            threeList = new ArrayList<>();
            threeInteger = false;
            threeError = false;
            ThreeTaMenuHeader.setText(threeHEADER);

            setTitle(threeHEADER);

            ThreeBtnOK1.setEnabled(false);
            threeAskNature();



            ThreeBtnOK2.setEnabled(false);
            threeAskTitle();



            if (ThreeRbUngrouped.isSelected()) {
                threeAskSize();
            } else if (ThreeRbGrouped.isSelected()){
                threeAskInterval();
            }

            if (ThreeRbUngrouped.isSelected()) { // Ungrouped Data
                ThreePanel3.setVisible(true);
                ThreeBtnOK3.setEnabled(false);
                threeAskSize();



                threeList = new ArrayList<>();



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


            }


        }
    }

    boolean ans = false;
    private boolean btnExploreClicked() {
        btnExplore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ans = true;
            }
        });
        return ans;
    }

    // LAB 1
    private void oneAskNature() {
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (OneRbSimple.isSelected() || OneRbStratified.isSelected() || OneRbQuit.isSelected() || OneRbSystematic.isSelected()) {
                OneBtnOK1.setEnabled(true);
                break;
            }
        }
    }

    private void oneAskSize() {
        OneBtnOK2.setEnabled(false);
        while (true) {
            try {
                wait();
            } catch ( InterruptedException | IllegalMonitorStateException e) {
                // do nothing
            }
            if (!OneTfPop.getText().equals("")) {
                OneBtnOK2.setEnabled(true);
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

    private void regain(JPanel pane) {
        ans = false;
        for (Component c : pane.getComponents()) {
            c.setEnabled(true);
            if (c instanceof JPanel) {
                regain((JPanel) c);
            }
        }
        pnlEverything.setEnabled(true);
        for (Component c : pnlEverything.getComponents()) {
            c.setEnabled(true);
        }
        pnlEverything.setVisible(true);
        pane.setVisible(false);
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

    public static void main(String[] args) {
        main = new Main();
    }

    // LAB 3
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
}
