import java.awt.*;
import java.sql.*;
import javax.swing.*;
public class Teacher_details extends JFrame{
    Teacher_details(String username,JPanel panel){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement","root","rajan");
            PreparedStatement st=con.prepareStatement("select * from teacher_details where username=?");
            st.setString(1,username);
            ResultSet rs= st.executeQuery();
            if(rs.next()){
                String fullname=rs.getString("fullname");
                String subject=rs.getString("subject");
                //JPanel panel=new JPanel(new GridLayout(3,1));
                panel.setLayout(null);
                JLabel l1=new JLabel("User Name :    "+username);
                l1.setBounds(400,30,200,40);
                JLabel l2=new JLabel("Full Name :    "+fullname);
                l2.setBounds(400,80,200,40);
                JLabel l3=new JLabel("Subject    :        "+subject);
                l3.setBounds(400,130,200,40);
                panel.add(l1);
                panel.add(l2);
                panel.add(l3);

            }
            else{
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
