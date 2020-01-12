package Daniel.Klt;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Main {

    static File f;

    public static void main(String a[]){
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        chooser.setFileFilter(filter);

        int rvalue = chooser.showOpenDialog(null);

        if(rvalue == JFileChooser.APPROVE_OPTION){
            f = chooser.getSelectedFile();
        }

        if(f==null){
            JFrame frame = new JFrame("Error");
            JOptionPane.showMessageDialog(frame,"Keine Datei gewählt. Editor beenden");
            System.exit(0);
        }

        final JTextArea edit = new JTextArea(30,60);
        ReadtoArea(f,edit);

        JButton save = new JButton("Speichern");
        save.addActionListener(e -> WritetoFile(f,edit));

        JFrame frame = new JFrame("Editor");
        frame.getContentPane().add(new JScrollPane(edit), BorderLayout.NORTH);
        frame.getContentPane().add(save,BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public static void ReadtoArea(File file, JTextArea area){

        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(file));
            String str;
            while((str = bf.readLine()) != null){
                area.append("\n"+str);
            }
            bf.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void WritetoFile(File savePath,JTextArea textArea){
        try{
            BufferedWriter bf = new BufferedWriter(new FileWriter(savePath));
            bf.write(textArea.getText());
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Datei gespeichert");
        JOptionPane.showMessageDialog(frame, "Datei " + savePath.getName() + " erfolgreich gespeichert");
    }




}