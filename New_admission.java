import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
public class New_admission extends JFrame{
    New_admission(){
        JPanel panel=new JPanel(null);
        JLabel l=new JLabel("Welcome to Alliance School");
        l.setBounds(580,90,300,40);
        l.setForeground(Color.white);
        JLabel l1=new JLabel("Full Name:");
        l1.setBounds(500,150,150,30);
        JTextField f1=new JTextField();
        f1.setBounds(650,150,200,30);
        JLabel l2=new JLabel("Class:");
        l2.setBounds(500,185,150,30);
        JTextField f2=new JTextField();
        f2.setBounds(650,185,200,30);
        JLabel l3=new JLabel("Username:");
        l3.setBounds(500,220,150,30);
        JTextField f3=new JTextField();
        f3.setBounds(650,220,200,30);
        JLabel l4=new JLabel("Password:");
        l4.setBounds(500,255,150,30);
        JTextField f4=new JTextField();
        f4.setBounds(650,255,200,30);
        JLabel l5=new JLabel("Confirm Password:");
        l5.setBounds(500,290,150,30);
        JTextField f5=new JTextField();
        f5.setBounds(650,290,200,30);
        JButton b=new JButton("Submit");
        b.setBounds(600,330,100,30);
        panel.add(l);
        panel.add(l1);
        panel.add(f1);
        panel.add(l2);
        panel.add(f2);
        panel.add(l3);
        panel.add(f3);
        panel.add(l4);
        panel.add(f4);
        panel.add(l5);
        panel.add(f5);
        panel.add(b);
        add(panel);
        JLabel back;
        try {
            Image backgroundImage = ImageIO.read(new File("newadmin.jpg"));
            back = new JLabel(new ImageIcon(backgroundImage));
            back.setBounds(0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null));
            panel.add(back);
        } catch (Exception e) {
            e.printStackTrace();
        }
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1=f1.getText();
                String s2=f2.getText();
                String s3=f3.getText();
                String s4=f4.getText();
                String s5=f5.getText();
                int classs=0;
                if (!s2.isEmpty()) {
                    try {
                        classs = Integer.parseInt(s2);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Class Value", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Class field is empty", "Error", JOptionPane.ERROR_MESSAGE);
                }

                int finalClasss = classs;
                if(s4.equals(s5)) {
                    try {
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement", "root", "rajan");
                        PreparedStatement st1=con.prepareStatement("insert into student_details value(?,?,?,NULL)");
                        st1.setString(1,s3);
                        st1.setString(2,s1);
                        st1.setInt(3, finalClasss);
                        String tablename1=s2+"_attendence";
                        PreparedStatement st2=con.prepareStatement("insert into "+tablename1+" value(?,1,1,1,1,1,1)");
                        st2.setString(1,s1);
                        String tablename2=s2+"_marks";
                        PreparedStatement st3=con.prepareStatement("insert into "+tablename2+" value(?,0,0,0)");
                        st3.setString(1,s1);
                        PreparedStatement st4=con.prepareStatement("insert into student_login value(?,?)");
                        st4.setString(1,s3);
                        st4.setString(2,s4);
                        st4.executeUpdate();
                        st1.executeUpdate();
                        st2.executeUpdate();
                        st3.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Entered Successfully");

                    }catch(Exception z){
                        z.printStackTrace();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Password Not matched","",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setSize(1560,1080);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Fill Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
