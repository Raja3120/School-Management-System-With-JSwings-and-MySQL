import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
public class Student_details extends JFrame{
    Student_details(String username,JPanel panel) {
        JLabel l=new JLabel("Welcome to Alliance School");
        l.setBounds(400,0,300,40);
        panel.add(l);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement", "root", "rajan");
            PreparedStatement st=con.prepareStatement("select * from student_details where username=?");
            st.setString(1,username);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
               String fullname=rs.getString("fullname");
                String classs=rs.getString("class");
                byte[] image=rs.getBytes("photo");
                JLabel l1=new JLabel("Username  :  "+username);
                l1.setBounds(400,190,180,30);
                JLabel l2=new JLabel("Full Name : "+fullname);
                l2.setBounds(400,220,180,30);
                JLabel l3=new JLabel("Class         : "+classs);
                l3.setBounds(400,250,180,30);
                JLabel l4;
                JButton b;
                if(image !=null){
                    ImageIcon imageIcon=new ImageIcon(image);
                    Image image1=imageIcon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
                    l4=new JLabel(new ImageIcon(image1));
                    l4.setBounds(425,50,100,130);
                }else{
                    ImageIcon plac=new ImageIcon("C:\\Users\\annar\\Music\\placeholder.jpg");
                    Image image2=plac.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
                    l4=new JLabel(new ImageIcon(image2));
                    l4.setBounds(425,50,100,100);
                    b=new JButton("Add Photo");
                    b.setBackground(Color.white);
                    b.setBounds(425,150,100,30);
                    b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                           JFileChooser file=new JFileChooser();
                           int returnn=file.showOpenDialog(null);
                           if(returnn==JFileChooser.APPROVE_OPTION){
                               File file1=file.getSelectedFile();
                               String path=file1.getAbsolutePath();
                               ImageIcon icon=new ImageIcon(path);
                               Image i=icon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
                               l4.setIcon(new ImageIcon(i));
                               try{
                                   FileInputStream f=new FileInputStream(file1);
                                   ByteArrayOutputStream b=new ByteArrayOutputStream();
                                   byte[] c=new byte[1024];
                                   for(int read;(read=f.read(c))!=-1;){
                                       b.write(c,0,read);
                                   }
                                   byte[] imageByte=b.toByteArray();
                                   PreparedStatement st2=con.prepareStatement("update student_details set photo=? where username=?");
                                   st2.setBytes(1,imageByte);
                                   st2.setString(2,username);
                                   st2.executeUpdate();
                                   JOptionPane.showMessageDialog(null,"Image added Successfully");
                                   st2.close();
                                   f.close();
                                   b.close();
                                   con.close();
                               }catch(Exception z){
                                   z.printStackTrace();
                               }
                           }
                        }
                    });
                    panel.add(b);
                }
                panel.add(l1);
                panel.add(l2);
                panel.add(l3);
                panel.add(l4);
//                add(panel, BorderLayout.CENTER);
//                setTitle("Student Details");
//                setSize(1650,1080);
//                setLocationRelativeTo(null);
//                setVisible(true);
//                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            else{
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
