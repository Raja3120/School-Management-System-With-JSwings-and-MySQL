import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.imageio.*;
import java.io.File;
class Login extends JFrame {
    JButton b1;
    JLabel Usertype, Username, Password;
    JTextField field1, field2;
    JComboBox<String> com;
    JLabel back;
    Login() {
        Usertype = new JLabel();
        Usertype.setText("UserType");
        Usertype.setBounds(550,200,100,30);
        Usertype.setForeground(Color.WHITE);
        String[] user = {"Student", "Teacher","New Admission"};
        com = new JComboBox<String>(user);
        com.setBounds(650,200,150,30);
        Username = new JLabel();
        Username.setText("Username");
        Username.setBounds(550,240,100,30);
        Username.setForeground(Color.WHITE);
        field1 = new JTextField(15);
        field1.setBounds(650,240,150,30);
        Password = new JLabel();
        Password.setText("Password");
        Password.setBounds(550,280,100,30);
        Password.setForeground(Color.WHITE);
        field2 = new JTextField(15);
        field2.setBounds(650,280,150,30);
        b1 = new JButton("Login");
        b1.setBounds(625,320,100,30);
        add(Usertype);
        add(com);
        add(Username);
        add(field1);
        add(Password);
        add(field2);
        add(b1);
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Image backgroundImage = ImageIO.read(new File("login1.jpeg"));
            back = new JLabel(new ImageIcon(backgroundImage));
            back.setBounds(0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null));
            add(back);
        } catch (Exception e) {
            e.printStackTrace();
        }
        field2.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
      b1.addActionListener(new ActionListener() {
        public void actionPerformed (ActionEvent e){
            login();
        }
    });
}
void login(){
    String userType = (String) com.getSelectedItem();
    String username = field1.getText();
    String password = field2.getText();
    if (userType == "Teacher") {
        new LoginTeacher(username,password);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } else if(userType=="Student"){
        new LoginStudent(username,password);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }else{
        new New_admission();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
}

public class LoginPage {
    public static void main(String[] args){
        try{
            Login l=new Login();
            l.setSize(1650,1080);
            l.setLayout(null);
            l.setLocationRelativeTo(null);
            l.setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
