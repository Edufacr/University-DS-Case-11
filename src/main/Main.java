package main;

import gui.MainWindow;
import model.SearchManager;
import model.TreeLoader;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        TreeLoader loader = new TreeLoader();
        loader.loadTrees();
        SearchManager manager = new SearchManager(loader.getWordTree(),loader.getWordsOccurrenceTree(),loader.getUrlTree(),loader.getWordsIndexed());
        MainWindow window = new MainWindow("University-DS-Case_11",manager);
    }
}
