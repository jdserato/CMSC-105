package Lab03;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by localuser on 4/18/17.
 */
public class mainFrame extends JFrame{
    private JLabel descriptive;
    private JLabel menu;
    private JRadioButton ungroupedData;
    private JRadioButton groupedData;
    private JRadioButton quit;
    private JButton okayButton;
    private JPanel panel1;


    public mainFrame() {

        add(panel1);
        setVisible(true);
        setSize(625,450);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (groupedData.isSelected()){

                }else if (ungroupedData.isSelected()){

                }else{
                    int confirmation = JOptionPane.showConfirmDialog(panel1,"ARE YOU SURE?");
                    if (confirmation == 0){
                        dispose();
                        return;
                    }else{
                        //nothing
                    }

                }
            }
        });
    }
}
