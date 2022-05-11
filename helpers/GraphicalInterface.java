package com.company.helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphicalInterface implements ActionListener {
    private HashMap<String, ArrayList<String>> Tokens;
    JFrame frame;
    static ArrayList<String> content;
    JButton button_load;
    JButton button_interpret;
    JLabel label1;
    JLabel label2;
    File file;
    JTextArea text;
    static JTextArea result;

    public GraphicalInterface(HashMap<String, ArrayList<String>> Tokens) {
        this.Tokens=Tokens;
        content=new ArrayList<String>();
        frame = new JFrame("Interpreter");

        JPanel panel_head = new JPanel();
        JPanel panel_body = new JPanel();

        button_load = new JButton("Load file");
        button_interpret = new JButton("Interpret");

        label1 = new JLabel("File Content : ");
        label2 = new JLabel("Result : ");


        file = new File("");
        text = new JTextArea(5, 10);
        result = new JTextArea(5, 10);


        button_load.addActionListener(this);
        button_interpret.addActionListener(this);
        button_interpret.setEnabled(false);

        button_load.setBorderPainted(false);
        button_load.setFocusPainted(false);
        button_load.setBackground(new Color(4, 122, 178));
        button_load.setForeground(Color.WHITE);

        button_interpret.setBorderPainted(false);
        button_interpret.setFocusPainted(false);
        //button_interpret.setContentAreaFilled(false);

        text.setEnabled(false);
        result.setForeground(Color.green);


        text.setBackground(Color.BLACK);
        result.setBackground(Color.BLACK);

        panel_head.add(button_load);
        panel_head.add(button_interpret);


        panel_body.add(new JLabel(" "));
        panel_body.add(new JLabel(" "));
        panel_body.add(label1);
        panel_body.add(new JLabel(" "));
        panel_body.add(new JScrollPane(text));
        panel_body.add(new JLabel(" "));
        panel_body.add(label2);
        panel_body.add(new JLabel(" "));
        panel_body.add(new JScrollPane(result));

        panel_body.setLayout(new BoxLayout(panel_body, BoxLayout.Y_AXIS));

        frame.add(panel_head, BorderLayout.NORTH);

        frame.add(panel_body, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);//400 width and 500 height
        frame.setVisible(true);//making the frame visible
        frame.setResizable(false);
    }

    public static ArrayList<String> getContent(){
        return content;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_load) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                text.setText(null);
                result.setText(null);
                file = fileChooser.getSelectedFile();
                button_interpret.setEnabled(true);
                button_interpret.setBackground(new Color(70, 171, 28));
                button_interpret.setForeground(Color.WHITE);
                try {
                    BufferedReader in = new BufferedReader(new FileReader(file));
                    String line = in.readLine();
                    while (line != null) {
                        content.add(line);
                        text.append(line + "\n");
                        line = in.readLine();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource() == button_interpret) {
            result.setText(null);
            result.append("------------------------------  Yassine-r  ------------------------------ \n \n");
            ArrayList<String> results= Processing.Process(Processing.VIC(file,Tokens));
            for (String s : results) {
                result.append(s+"\n");
            }
        }
    }
}
