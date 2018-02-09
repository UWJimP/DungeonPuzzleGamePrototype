package test;

import java.awt.EventQueue;

import game.view.GameGUI;

public class TestStringBoard {

    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI().start();
            }            
        });
    }
}
