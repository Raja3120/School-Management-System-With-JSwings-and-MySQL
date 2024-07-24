import java.sql.*;
import javax.swing.*;
import java.awt.*;
public class Student_attendence extends JFrame {
    Student_attendence(String username,JPanel panel){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement","root","rajan");
            PreparedStatement st= con.prepareStatement("select * from student_details where username=?");
            st.setString(1,username);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                String fullname=rs.getString("fullname");
                String classs=rs.getString("class");
                String tablename=classs+"_attendence";
                JLabel jl=new JLabel("Full Name:"+fullname);
                jl.setBounds(0,10,200,30);
                JLabel jl1=new JLabel("Class :"+classs);

                jl1.setBounds(0,40,200,30);
                PreparedStatement st2=con.prepareStatement("select * from "+tablename+" where fullname=?");
                st2.setString(1,fullname);
                ResultSet rs2=st2.executeQuery();
                if(rs2.next()){
                    int i1=rs2.getInt("Maths_present");
                    int i2=rs2.getInt("Maths_total");
                    int i3=rs2.getInt("Science_present");
                    int i4=rs2.getInt("Science_total");
                    int i5=rs2.getInt("Social_present");
                    int i6=rs2.getInt("Social_total");
                    float f1=i1;
                    float f2=i3;
                    float f3=i5;
                    float f4=(((f1/i1)+(f2/i2)+(f3/i3))/3)*100;
                    String s1=Float.toString((f1/i2)*100);
                    String s2=Float.toString((f2/i4)*100);
                    String s3=Float.toString((f3/i6)*100);
                    String s4=Float.toString(f4);
                    JLabel l1=new JLabel("Subject     Attendence");
                    l1.setBounds(0,70,200,30);
                    JLabel l2=new JLabel("Maths           "+s1);
                    l2.setBounds(0,100,200,30);
                    JLabel l3=new JLabel("Science       "+s2);
                    l3.setBounds(0,130,200,30);
                    JLabel l4=new JLabel("Social          "+s3);
                    l4.setBounds(0,160,200,30);
                    JLabel l5=new JLabel("Total             "+s4);
                    l5.setBounds(0,190,200,30);
                    panel.add(jl);
                    panel.add(jl1);
                    panel.add(l1);
                    panel.add(l2);
                    panel.add(l3);
                    panel.add(l4);
                    panel.add(l5);
                    con.close();

                }
                else{
                    con.close();
                }
            }else{
        con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
