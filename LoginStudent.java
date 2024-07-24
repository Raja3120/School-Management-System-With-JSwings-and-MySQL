import javax.swing.*;
import java.sql.*;
public class LoginStudent {
    LoginStudent(String username,String password){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolmanagement","root","rajan");
            PreparedStatement st=con.prepareStatement("select * from student_login where username= ? and password=?");
            st.setString(1,username);
            st.setString(2,password);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                new StudentHome(username);
                con.close();
            }
            else{
                con.close();
                JOptionPane.showMessageDialog(null,"Invalid username or password","Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
