package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.AVLTree;
import model.IConstants;
import model.SearchManager;
import model.Word;

import static model.IConstants.*;

public class MainWindow extends JFrame implements Observer {
    private JTextField textField;
    private ActionListener rangeSearchButtonListener;
    private ActionListener wordSearchButtonListener;
    private ActionListener domainSearchButtonListener;
    private JPanel mainPanel;
    private SearchManager manager;
    private Dimension labelDim;

    public MainWindow(String pName,SearchManager pManager){
        super(pName);
        InitMainWindow();
        CreateListeners();
        add(CreateTextField());
        add(CreateButtonPanel());
        add(CreateScrollArea());
        labelDim = new Dimension(LABEL_WIDTH,LABEL_HEIGHT);
        pack();
        setManager(pManager);
        setVisible(true);
    }
    private void setManager(SearchManager pManager){
        manager = pManager;
        manager.addObserver(this);
    }
    private void InitMainWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));
        setMinimumSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
    }
    private JTextField CreateTextField(){
        textField = new JTextField("Search");
        return textField;
    }
    private void CreateListeners(){
        domainSearchButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                manager.searchWord(textField.getText());
            }
        };
        wordSearchButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        };
        rangeSearchButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        };

    }
    private JPanel CreateButtonPanel(){
        JPanel buttonPanel = new JPanel(new GridLayout(1,3));
        JButton rangeSearchButton = new JButton("Range Search");
        JButton wordSearchButton = new JButton("Word Search");
        JButton domainSearchButton = new JButton("Domain Search");
        rangeSearchButton.addActionListener(rangeSearchButtonListener);
        wordSearchButton.addActionListener(wordSearchButtonListener);
        domainSearchButton.addActionListener(domainSearchButtonListener);
        buttonPanel.add(domainSearchButton);
        buttonPanel.add(rangeSearchButton);
        buttonPanel.add(wordSearchButton);
        return buttonPanel;
    }
    private JScrollPane CreateScrollArea(){
        mainPanel = new JPanel(new GridLayout());
        JScrollPane pane = new JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(new Dimension(MAINPANEL_WIDTH,MAINPANEL_HEIGHT));
        return pane;
    }

    public static void main(String[] args) {
        AVLTree<Word> wordTree = new AVLTree<Word>();
        ArrayList<String> s1 = new ArrayList<String>();
        ArrayList<String> s2 = new ArrayList<String>();
        s1.add("A");
        s1.add("B");
        s1.add("A");
        s1.add("B");
        s1.add("A");
        s1.add("B");
        s1.add("A");
        s1.add("B");
        wordTree.add(new Word("hola",s1));
        wordTree.add(new Word("buenas",s2));
        wordTree.add(new Word("adios","C"));
        MainWindow w = new MainWindow("Hola",new SearchManager(wordTree));
    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<String> result = (ArrayList<String>) o;
        mainPanel.removeAll();
        JLabel label;
        for (String text:result
             ) {
            label = new JLabel(text);
            label.setPreferredSize(labelDim);
            mainPanel.add(new JLabel(text));
        }
        mainPanel.revalidate();
    }
}
