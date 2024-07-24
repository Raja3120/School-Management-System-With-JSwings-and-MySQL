import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class Mark_attendence extends JFrame {
    Mark_attendence(String clas, String subject) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement", "root", "rajan");
            String tablename = clas + "_attendence";
            PreparedStatement st = con.prepareStatement("select fullname from " + tablename);
            ResultSet rs = st.executeQuery();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            ArrayList<JCheckBox> checkboxes = new ArrayList<>();
            JButton b1= new JButton("Save");
            b1.setPreferredSize(new Dimension(80,30));
            while (rs.next()) {
                String name = rs.getString("fullname");
                JCheckBox checkBox = new JCheckBox(name);
                b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        boolean present = checkBox.isSelected();
                        updateAttendance(con, tablename, name, present, subject);
                        dispose();
                    }

                });
                        panel.add(checkBox);
                        checkboxes.add(checkBox);

            }
            panel.add(b1);

            add(panel);
            JScrollPane scr=new JScrollPane(panel);
            add(scr,BorderLayout.CENTER);
            setSize(400, 300);
            setLocationRelativeTo(null);
            setVisible(true);
            setTitle("Mark Attendance for " + clas);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No class for marking Attendence");

        }

    }

        void updateAttendance(Connection con, String tablename, String name,boolean present,String subject){
        try {
            String updateQuery = "";
            if (present) {
                updateQuery = "UPDATE " + tablename + " SET " + subject + "_present = " + subject + "_present + 1, " + subject + "_total = " + subject + "_total + 1 WHERE fullname = ?";
            } else {
                updateQuery = "UPDATE " + tablename + " SET " + subject + "_total = " + subject + "_total + 1 WHERE fullname = ?";
            }
            PreparedStatement updateStatement = con.prepareStatement(updateQuery);
            updateStatement.setString(1, name);
            updateStatement.executeUpdate();

        }catch(SQLException w){
            w.printStackTrace();
        }
        }

    }

