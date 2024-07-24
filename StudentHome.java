import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

class StudentHome extends JFrame {
    JButton b1, b2, b3,b4;
    JPanel panel=new JPanel();
    JPanel panel1;
    StudentHome(String username) {
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(200,getHeight()));
        panel.setLayout(null);
        b1 = new JButton("Attendance");
        b1.setBounds(10, 50, 150, 40);
        b1.setBackground(Color.gray);
        b1.setBorderPainted(false);
        b2 = new JButton("Time Table");
        b2.setBounds(10, 110, 150, 40);
        b2.setBackground(Color.gray);
        b2.setBorderPainted(false);
        b3 = new JButton("Report Card");
        b3.setBounds(10, 170, 150, 40);
        b3.setBackground(Color.gray);
        b3.setBorderPainted(false);
        b4 = new JButton("Details");
        b4.setBounds(10, 230, 150, 40);
        b4.setBackground(Color.gray);
        b4.setBorderPainted(false);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        setTitle("Student Home");
        setLayout(new BorderLayout());
        setSize(1650, 1080);
        add(panel,BorderLayout.WEST);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1=new JPanel(null);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel1.removeAll();
                panel1.setLayout(null);
            new Student_attendence(username,panel1);
                panel1.revalidate();
                panel1.repaint();
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel1.removeAll();
            new Student_timetable(username,panel1);
                panel1.revalidate();
                panel1.repaint();
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel1.removeAll();
            new Student_report(username,panel1);
            panel1.revalidate();
            panel1.repaint();
            }
        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel1.removeAll();
                panel1.setLayout(null);
                new Student_details(username,panel1);
                panel1.revalidate();
                panel1.repaint();
            }
        });
        new Student_details(username,panel1);
        add(panel1);
        setVisible(true);

    }

}


