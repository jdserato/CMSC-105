import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by Semora on May 12, 2017.
 */
public class MainPane extends JFrame {
    private JLabel lTitle;
    private JPanel onePnlFirst;
    private JRadioButton OneRbSimple;
    private JRadioButton OneRbSystematic;
    private JRadioButton OneRbStratified;
    private JRadioButton OneRbQuit;
    private JButton OneBtnOK1;
    private JLabel OneLSamplingType;
    private JPanel panel;
    private JPanel OnePnlSecond;
    private JLabel OneLPop;
    private JTextField OneTfPop;
    private JButton OneBtnOK2;
    private JCheckBox OneCbFile;
    private JPanel OnePnlThird;
    private JPanel OnePnlFifth;
    private JLabel OneLData;
    private JTextArea OneTaData;
    private JButton OneBtnOK3;
    private JButton OneBtnDone;
    private JLabel OneLNum;
    private JPanel OnePnlFourth;
    private JLabel OneLSampleSize;
    private JTextField OneTfSampleSize;
    private JButton OneBtnOK4;
    private ArrayList<String> oneList;
    private int oneSize;
    private boolean oneNumeric;
    private BufferedReader oneBr;
    private Sampling oneTechnique;
    private int oneSampleSize;

    public MainPane() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(675, 450);
        setVisible(true);
        add(panel);
        
        oneSize = 0;
        oneList = new ArrayList<>();

        OnePnlSecond.setVisible(false);
        OnePnlThird.setVisible(false);
        OnePnlFourth.setVisible(false);
        OnePnlFifth.setVisible(false);

        OneBtnOK1.setEnabled(false);
        oneAskNature();

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
                OneBtnOK2.setEnabled(false);
            }
        });
        
        oneAskSize();

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
                if (error || oneSize <= 0) {
                    JOptionPane.showMessageDialog(panel, "Size must be positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
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
                                }
                                else if (newItem.length() == 0) {
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
                        }
                        else {
                            refocus(OnePnlSecond, OnePnlFourth);
                        }
                    } else {
                        refocus(OnePnlSecond, OnePnlThird);
                    }

                    if (!(oneTechnique instanceof RevisedStratified)) {
                        OneTfSampleSize.setText((int) Math.ceil((double) oneSize * .20) + "");
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
            public void keyPressed( KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    oneNumeric = false;
                    boolean error = false;
                    try {
                        oneSize = Integer.parseInt(OneTfPop.getText());
                    } catch (NumberFormatException e1) {
                        error = true;
                    }
                    if (error || oneSize <= 0) {
                        JOptionPane.showMessageDialog(panel, "Size must be positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
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
                            }
                            else {
                                refocus(OnePnlSecond, OnePnlFourth);
                            }
                        } else {
                            refocus(OnePnlSecond, OnePnlThird);
                        }

                        if (!(oneTechnique instanceof RevisedStratified)) {
                            OneTfSampleSize.setText((int) Math.ceil((double) oneSize * .20) + "");
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
                    System.out.println(oneList);
                    refocus(OnePnlThird, OnePnlFourth);
                }
            }
        });


        OneTaData.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed( KeyEvent e) {
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
                        System.out.println(oneList);
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
                } catch ( InputMismatchException | NumberFormatException e1) {
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
                new ViewPopulation(oneList, oneTechnique, oneSampleSize);
                hide();
            }
        });
    }

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

    private void refocus( JPanel prev,  JPanel next) {
        for (Component c : prev.getComponents()) {
            c.setEnabled(false);
        }
        next.setVisible(true);
    }

    public static void main(String[] args) {
        new MainPane();
    }
}
