package it.unibo.mvc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.FileChooserUI;

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
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();
    private static final int PROPORTION = 4;

    public SimpleGUIWithFileChooser (final Controller controller) {
        JPanel chooserPanel = new JPanel(new BorderLayout());
        JTextField browsePath = new JTextField(controller.getFilePath());
        browsePath.setEditable(false);
        chooserPanel.add(browsePath, BorderLayout.CENTER);
        JButton browseButton = new JButton("Browse");
        chooserPanel.add(browseButton, BorderLayout.LINE_END);

        JPanel canvasMain = new JPanel(new BorderLayout());
        canvasMain.add(chooserPanel, BorderLayout.NORTH);
        JTextArea textarea = new JTextArea();
        canvasMain.add(textarea, BorderLayout.CENTER);
        JButton saveButton = new JButton("Save");
        canvasMain.add(saveButton, BorderLayout.SOUTH);

        frame.setContentPane(canvasMain);
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

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    JFileChooser fileChooser = new JFileChooser("Choose a file");
                    fileChooser.setSelectedFile(controller.getCurrentFile());
                    final var result = fileChooser.showSaveDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        controller.setCurrentFile(fileChooser.getSelectedFile());
                        browsePath.setText(controller.getFilePath());
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, e1, "There was an error during execution", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void display(){
        frame.setVisible(true);
    }

    public static void main(String... args) {
        new SimpleGUIWithFileChooser(new Controller()).display();
    }
}
