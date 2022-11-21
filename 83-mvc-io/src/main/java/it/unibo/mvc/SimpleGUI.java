package it.unibo.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private static final int PROPORTION = 4;

    public SimpleGUI(SimpleController controller){
        JPanel canvas = new JPanel(new BorderLayout());
        
        JTextField textField = new JTextField();
        canvas.add(textField, BorderLayout.NORTH);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        canvas.add(textArea, BorderLayout.CENTER);

        JPanel buttonCanvas = new JPanel(new GridLayout(1, 2));
        JButton print = new JButton("Print");
        buttonCanvas.add(print);
        JButton showHistory = new JButton("Show history");
        buttonCanvas.add(showHistory);

        canvas.add(buttonCanvas, BorderLayout.SOUTH);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw/PROPORTION, sh/PROPORTION);
        frame.setLocationByPlatform(true);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller.setNext(textField.getText());
                controller.printCurrent();
            }
        });

        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String s1 = new String();
                for (String s2 : controller.getHistory()) {
                    s1 = s1 + s2 + "\n";
                }
                textArea.setText(s1);
            }
        });
    }

    private void display(){
        frame.setVisible(true);
    }

    public static void main(String... args){
        new SimpleGUI(new SimpleController()).display();
    }

}
