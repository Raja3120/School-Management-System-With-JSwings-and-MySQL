import java.sql.*;
import javax.swing.*;
import java.awt.*;
public class Student_timetable extends JFrame{
    Student_timetable(String username,JPanel panel){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement","root","rajan");
            PreparedStatement st=con.prepareStatement("select * from student_details where username=?");
            st.setString(1,username);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                String classs=rs.getString("class");
                String tablename=classs+"_timetable";
                PreparedStatement st2=con.prepareStatement("select * from "+tablename);
                ResultSet rs2=st2.executeQuery();
                if(rs2.next()){
                    panel.setLayout(new GridLayout(0,6));
                    panel.setPreferredSize(new Dimension(200,200));
                    panel.add(new JLabel("Time"));
                    panel.add(new JLabel("Monday"));
                    panel.add(new JLabel("Tuesday"));
                    panel.add(new JLabel("Wednesday"));
                    panel.add(new JLabel("Thursday"));
                    panel.add(new JLabel("Friday"));
                     panel.add(new JLabel(rs2.getString("Timings")));
                     panel.add(new JLabel(rs2.getString("monday")));
                     panel.add(new JLabel(rs2.getString("tuesday")));
                     panel.add(new JLabel(rs2.getString("wednesday")));
                     panel.add(new JLabel(rs2.getString("thursday")));
                    panel.add(new JLabel(rs2.getString("friday")));
                    while(rs2.next()) {
                        panel.add(new JLabel(rs2.getString("Timings")));
                        panel.add(new JLabel(rs2.getString("monday")));
                        panel.add(new JLabel(rs2.getString("tuesday")));
                        panel.add(new JLabel(rs2.getString("wednesday")));
                        panel.add(new JLabel(rs2.getString("thursday")));
                        panel.add(new JLabel(rs2.getString("friday")));
                    }
                }
                else{
                    con.close();
                }
            }
            else{
                con.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
