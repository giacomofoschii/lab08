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
        canvas.add(textArea, BorderLayout.CENTER);

        JPanel buttonCanvas = new JPanel(new BorderLayout());
        JButton print = new JButton("Print");
        buttonCanvas.add(print, BorderLayout.LINE_START);
        JButton showHistory = new JButton("Show history");
        buttonCanvas.add(showHistory, BorderLayout.LINE_END);

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
                try {
                    controller.setNext(textField.getText());
                    controller.printCurrent();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, e, "There was an error in execution", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String s1 = new String();
                    for (String s2 : controller.getHistory()) {
                        s1 = s1 + s2 + "\n";
                    }
                    textArea.setText(s1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, e, "There was an error in execution", JOptionPane.ERROR_MESSAGE);
                }
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
