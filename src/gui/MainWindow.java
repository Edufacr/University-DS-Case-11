package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JPanel mainPanel;
    private JTextField textField;
    private JButton searchWordsButton;
    private JButton searchFrequencyButton;
    private JButton searchDomainButton;
    private JPanel mainScrollPanel;
    private JScrollPane mainScroll;

    public MainWindow() {
        mainScrollPanel.setLayout(new GridLayout());
        searchDomainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                test();
            }
        });
        searchFrequencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        searchWordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    private void test(){
        JLabel testy = new JLabel("Search Result");
        mainScrollPanel.add(testy);
        mainScrollPanel.revalidate();
    }
}
