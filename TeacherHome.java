import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
public class TeacherHome extends JFrame{
    JButton b2,b3,b4;
    JPanel panel=new JPanel();
    JPanel panel1=new JPanel();
    TeacherHome(String username){
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(200, 200));
        panel.setLayout(null);
        b2=new JButton("Time Table");
        b2.setBounds(10, 50, 150, 40);
        b2.setBackground(Color.gray);
        b2.setBorderPainted(false);
        b3=new JButton("Marks");
        b3.setBounds(10, 110, 150, 40);
        b3.setBackground(Color.gray);
        b3.setBorderPainted(false);
        b4=new JButton("Details");
        b4.setBounds(10, 170, 150, 40);
        b4.setBackground(Color.gray);
        b4.setBorderPainted(false);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        setTitle("Teacher Home");
        setLayout(new BorderLayout());
        setSize(1650, 1080);
        panel.setVisible(true);
        add(panel, BorderLayout.WEST);

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel1.removeAll();
                new Teacher_timetable(username,panel1);
                panel1.revalidate();
                panel1.repaint();
            }
        });
b3.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        panel1.removeAll();
        new Teacher_marks(username,panel1);
        panel1.revalidate();
        panel1.repaint();    }
});
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel1.removeAll();
                panel1.setLayout(null);
                new Teacher_details(username,panel1);
                panel1.revalidate();
                panel1.repaint();
            }
        });
        new Teacher_details(username,panel1);
        add(panel1);
        setVisible(true);
    }
    public static void main(String[] args){
        new TeacherHome("babu@gmail.com");
    }

}
