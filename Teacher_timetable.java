import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
public class Teacher_timetable extends JFrame{
    Teacher_timetable(String username,JPanel panel){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement", "root", "rajan");
            PreparedStatement st=con.prepareStatement("Select*from teacher_details where username=?");
            st.setString(1,username);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                String subject=rs.getString("subject");
                String tablename=subject+"_timetable";
                PreparedStatement st2=con.prepareStatement("select * from "+tablename);
                ResultSet rs2=st2.executeQuery();
                panel.setLayout(new GridLayout(0,6));
                panel.add(new JLabel("Timings"));
                panel.add(new JLabel("     Monday"));
                panel.add(new JLabel("    Tuesday"));
                panel.add(new JLabel("    Wednesday"));
                panel.add(new JLabel("    Thursday"));
                panel.add(new JLabel("     Friday"));
                while(rs2.next()){
                    String timing=rs2.getString("Timings");
                    panel.add(new JLabel(timing));
                    for (int i = 2; i <= 6; i++) {
                        String data = rs2.getString(i);
                        JButton button = new JButton(data);
                        button.setBackground(Color.WHITE);
                        button.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                new Mark_attendence(data,subject);
                            }
                        });
                        panel.add(button);
                    }
                }

                }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
