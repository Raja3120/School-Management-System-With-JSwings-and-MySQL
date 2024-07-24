import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class SwingUi extends JFrame{
    JPanel panel=new JPanel(new GridLayout(9,2));
    SwingUi(){
        JButton b1=new JButton("Add");
        JButton b2=new JButton("Delete");
        JButton b3=new JButton("Update");
        JButton b4=new JButton("View");
        JLabel l1=new JLabel("Student Id");
        JTextField t1=new JTextField();
        JLabel l2=new JLabel("Name");
        JTextField t2=new JTextField();
        JLabel l3=new JLabel("Age");
        JTextField t3=new JTextField();
        JLabel l4=new JLabel("Gender");
        JTextField t4=new JTextField();
        JLabel l5=new JLabel("Grade");
        JTextField t5=new JTextField();
        add(panel);
        panel.add(l1);
        panel.add(t1);
        panel.add(l2);
        panel.add(t2);
        panel.add(l3);
        panel.add(t3);
        panel.add(l4);
        panel.add(t4);
        panel.add(l5);
        panel.add(t5);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        //panel.ad);

b1.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String s1=t1.getText();
        String s2=t2.getText();
        int id=Integer.parseInt(s1);
        String s3=t3.getText();
        int age=Integer.parseInt(s3);
        String s4=t4.getText();
        String s5=t5.getText();
        float grade=Float.parseFloat(s5);
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","rajan");
            PreparedStatement st=con.prepareStatement("insert into sample value(?,?,?,?,?)");
st.setInt(1,id);
st.setString(2,s2);
st.setInt(3, age);
st.setString(4,s4);
st.setFloat(5, grade);
st.executeUpdate();
            System.out.println("Done insertion");
con.close();

        }catch(Exception f){
            f.printStackTrace();
        }
    }
});
b2.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String s1=t1.getText();
        int id=Integer.parseInt(s1);
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","rajan");
    PreparedStatement st=con.prepareStatement("delete from sample where Student_id=?");
    st.setInt(1,id);
    st.executeUpdate();
    System.out.println("Deleted");
    con.close();
        }catch(Exception r){
            r.printStackTrace();
        }
    }
});
b3.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

    }
});
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args){
        new SwingUi();
    }
}


