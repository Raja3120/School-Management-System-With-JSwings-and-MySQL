import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
public class Teacher_marks extends JFrame{
    Teacher_marks(String username,JPanel panel){
        panel.setLayout(null);
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement", "root", "rajan");
            PreparedStatement st=con.prepareStatement("select * from teacher_details where username=?");
            st.setString(1,username);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                String subject=rs.getString("subject");
                JLabel l=new JLabel("Select class:");
                l.setBounds(400,30,100,30);
                String[] clas={"6","7","8","9","10"};
                JComboBox<String> com=new JComboBox<String>(clas);
                com.setBounds(500,30,50,30);
                JButton b=new JButton("Next");
                b.setBounds(480,80,70,30);
                panel.add(l);
                panel.add(com);
                panel.add(b);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String classs=(String) com.getSelectedItem();
                        String tablename=classs+"_marks";
                        try {

                            PreparedStatement st2 = con.prepareStatement("select fullname from " + tablename);
                            ResultSet rs2= st2.executeQuery();
                            int in=0,j=0;
                            String[] a=new String[100];
                            JTextField[] textFields = new JTextField[100];
                            while(rs2.next()){

                             String name=rs2.getString("fullname");
                             JLabel l1=new JLabel(name);
                             a[in]=name;
                             l1.setBounds(300,120+j,150,30);
                             JTextField f=new JTextField();
                             textFields[in] = f;
                             f.setBounds(460,120+j,150,30);
                             panel.add(l1);
                             panel.add(f);
                             in++;
                             j+=40;
                            }
                            JButton b2=new JButton("Enter");
                            b2.setBounds(420,120+j,100,30);
                            int finalIn = in;
                            b2.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        for (int i = 0; i < finalIn; i++) {
                                            String mark = textFields[i].getText();
                                            PreparedStatement Statement = con.prepareStatement("update " + tablename + " set " + subject + " =? where fullname=?");
                                            Statement.setFloat(1, Float.parseFloat(mark));
                                            Statement.setString(2, a[i]);
                                            Statement.executeUpdate();
                                        }
                                        JOptionPane.showMessageDialog(null, "Marks entered successfully!");
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        JOptionPane.showMessageDialog(null, "Error occurred while entering marks!");
                                    }
                                }
                            });
                            panel.add(b2);
                            panel.revalidate();
                            panel.repaint();
                        }catch(Exception f){
                            f.printStackTrace();
                        }
                    }
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}