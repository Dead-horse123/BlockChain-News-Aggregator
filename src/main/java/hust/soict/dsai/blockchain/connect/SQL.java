/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hust.soict.dsai.blockchain.connect;

import hust.soict.dsai.blockchain.login.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SQL extends ConnectJDBC{

    public static void modifyCell(String text,String change,String colName, String table) throws SQLException{
        ConnectJDBC connectJDBC = new ConnectJDBC();
        try (Connection conn = connectJDBC.connect()) {
            Statement st = conn.createStatement();
            String query = "UPDATE " + table + " SET " + colName + "='" + text +"' where " + colName + " = '" + change + "';";
            st.executeUpdate(query);
            conn.close();
        }
    }
    public static void removeRow(String Link) throws SQLException{
        ConnectJDBC connectJDBC = new ConnectJDBC();
        try (Connection conn = connectJDBC.connect()) {
            Statement st = conn.createStatement();
            String query = "DELETE FROM data1 WHERE Link='" + Link + "';";
            st.executeUpdate(query);
            conn.close();
        }
    }
    public static void addRow(String Link, String Website, String Type, String Summary, String Title, String Content, String Date, String Tags, String Author, String Category, String Entities) throws InterruptedException{
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        String query = "INSERT INTO data1(Link, Website, Type, Summary, Title, Content, Date, Tags, Author, Category, Entities) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        String[] list = {Link, Website, Type, Summary, Title, Content, Date, Tags, Author, Category, Entities};
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(query);
            for (String list1 : list) {
                for (int i = 0; i < list1.length(); i++) {
                    if (list1.charAt(i) == ',') {
                        addChar(list1, ',', i);
                    }
                }
            }
            pstm.setString(1,list[0]);
            pstm.setString(2,list[1]);
            pstm.setString(3,list[2]);
            pstm.setString(4,list[3]);
            pstm.setString(5,list[4]);
            pstm.setString(6,list[5]);
            pstm.setString(7,list[6]);
            pstm.setString(8,list[7]);
            pstm.setString(9,list[8]);
            pstm.setString(10,list[9]);
            //Khi thực hiện các lệnh insert/update/delete sử dụng executeUpdate(), nó sẽ trả về số hàng bị tác động
            int row = pstm.executeUpdate();
            if(row != 0){
                System.out.println("Thêm thành công " + row);
            }
            //Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String addChar(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }
    public static void fillTable(JTable table) throws SQLException{
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        model.setRowCount(0);
        Statement st = conn.createStatement();
        String query = "select * from data1;";
        ResultSet set = st.executeQuery(query);
        while(set.next()){
            String Link = set.getString("Link");
            String Website = set.getString("Website");
            String summary = set.getString("summary");
            String type = set.getString("type");
            String title = set.getString("title");
            String content = set.getString("content");
            String date = set.getString("date");
            String tags = set.getString("tags");
            String author = set.getString("author");
            String category = set.getString("category");
            String[] data = {Link, Website, type, summary, title, content, date, tags, author,category};
            model.addRow(data);
        }
    }
    public static ArrayList getUserInformation() throws SQLException{
        ArrayList<User> data = new ArrayList<User>();
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        
        Statement st = conn.createStatement();
        String query = "select * from user;";
        ResultSet set = st.executeQuery(query);
        while(set.next()){
            String username = set.getString("Username");
            String role = set.getString("Role");
            String password = set.getString("Password");
            User user = new User(username, password, role);
            data.add(user);
        }
        return data;
    }
    public static void AddUser(String username, String password, String role) throws InterruptedException, SQLException{
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        ArrayList<String[]> userList = getUserInformation();
        String[] newUser = {username,password,role};
        if(!userList.contains(newUser)){
            String query = "INSERT INTO user(Username, Password, Role) VALUES (?,?,?)";
            PreparedStatement pstm = null;
            try {
                pstm = conn.prepareStatement(query);
                pstm.setString(1, username);
                pstm.setString(2, password);
                pstm.setString(3, role);
                //Khi thực hiện các lệnh insert/update/delete sử dụng executeUpdate(), nó sẽ trả về số hàng bị tác động
                int row = pstm.executeUpdate();
                if(row != 0){
                    System.out.println("Thêm thành công " + row);
                }
                //Đóng kết nối
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("User already exist");
        }
    }
    public static void RemoveUser(String username) throws SQLException{
        ConnectJDBC connectJDBC = new ConnectJDBC();
        try (Connection conn = connectJDBC.connect()) {
            
            Statement st = conn.createStatement();
            String query = "DELETE FROM user WHERE Username = '" + username + "';";
            st.executeUpdate(query);
            conn.close();
        }
    }
    public static void search(String item, JTable SearchingResultsTable) throws SQLException{
        DefaultTableModel model1 = (DefaultTableModel) SearchingResultsTable.getModel();
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        model1.setRowCount(0);
        Statement st = conn.createStatement();
        String query = "select * from data1 where (Link like '%" + item + "%' or Website like '%" + item + "%' or type like '%" + item + "%' or summary like '%" + item + "%' or title like '%" + item + "%' or content like '%" + item + "%' or content like '%" + item + "%' or date like '%" + item + "%' or author like '%" + item + "%' or category like '%" + item + "%');";
        ResultSet set = st.executeQuery(query);
        while(set.next()){
            String Link = set.getString("Link");
            String Website = set.getString("Website");
            String summary = set.getString("summary");
            String type = set.getString("type");
            String title = set.getString("title");
            String content = set.getString("content");
            String date = set.getString("date");
            String tags = set.getString("tags");
            String author = set.getString("author");
            String category = set.getString("category");
            String[] data = {Link, Website, type, summary, title, content, date, tags, author,category};
            model1.addRow(data);
        }
    }
}

