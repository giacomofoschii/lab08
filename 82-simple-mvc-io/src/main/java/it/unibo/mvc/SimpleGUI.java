package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final Controller controller = new Controller();
    private static final int PROPORTION = 4;
    private final JFrame frame = new JFrame();

    public SimpleGUI() {
        JPanel canvas = new JPanel(new BorderLayout());

        JTextArea textarea = new JTextArea();
        canvas.add(textarea, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        canvas.add(saveButton, BorderLayout.SOUTH);

        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw/PROPORTION, sh/PROPORTION);
        frame.setLocationByPlatform(true);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.saveTextOnFile(textarea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1, "There was an error during execution", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(String... args) {
        new SimpleGUI().display();
    }
}
