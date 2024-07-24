import java.awt.*;
import java.sql.*;
import javax.swing.*;
public class Student_report extends JFrame{
    Student_report(String username,JPanel panel){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement","root","rajan");
            PreparedStatement st=con.prepareStatement("select * from student_details where username=?");
            st.setString(1,username);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                String fullname=rs.getString("fullname");
                String classs=rs.getString("class");
                String tablename=classs+"_marks";
                PreparedStatement st2=con.prepareStatement("select * from "+tablename+" where fullname=?");
                st2.setString(1,fullname);
                ResultSet rs2=st2.executeQuery();
                if(rs2.next()){
                    String s1=rs2.getString("Maths");
                    String s2=rs2.getString("Science");
                    String s3=rs2.getString("Social");
                    float f1=(Float.parseFloat(s1)+Float.parseFloat(s2)+Float.parseFloat(s3))/3;
                    String s4=Float.toString(f1);
                    JLabel l=new JLabel("Name: "+fullname);
                    JLabel l0=new JLabel("Class: "+classs);
                    JLabel l1=new JLabel("Subject          Marks");
                    JLabel l2=new JLabel("Maths              "+s1);
                    JLabel l3=new JLabel("Science          "+s2);
                    JLabel l4=new JLabel("Social             "+s3);
                    JLabel l5=new JLabel("Percentage    "+s4);
                    panel.setLayout(new GridLayout(8,1));
                    panel.add(l);
                    panel.add(l0);
                    panel.add(l1);
                    panel.add(l2);
                    panel.add(l3);
                    panel.add(l4);
                    panel.add(l5);

                    con.close();
                }
                con.close();
            }else{
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
