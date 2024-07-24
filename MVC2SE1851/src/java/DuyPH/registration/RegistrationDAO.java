/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuyPH.registration;

import DuyPH.untils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author whisa
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;

        try {
            //1. get Connection    
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL String
                String Sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(Sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                if (rs.next()) {
                    //map ==> get data from resultSet and set data to DTO property
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullname, role);
                }//username and password have been existed
            }//connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accountList;
    
    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String searchValue)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. get Connection    
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL String
                String Sql = "Select username, password, lastname, isAdmin "
                        + "from Registration "
                        + "Where lastname like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(Sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    //5.1 get data from Result set
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.2 set data to DTO properties
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, fullname, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end account List has not existed
                    this.accountList.add(dto);
                }//end account has existed
            }//connection has been available
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        boolean result = false;

        try {
            //1. get Connection    
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL String
                String Sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(Sql);
                stm.setString(1, username);
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    result = true;
                }
                //username and password have been existed
            }//connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean createAccount(RegistrationDTO account) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        boolean result = false;

        try {
            //1. get Connection    
            con = DBHelper.getConnection();
            if (con != null) {
                //2. Create SQL String
                String Sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //3. Create Statement Object
                stm = con.prepareStatement(Sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullname());
                stm.setBoolean(4, account.isRole());
                //4. Execute Query
                int effectRows = stm.executeUpdate();
                //5. Process Result
                if (effectRows > 0) {
                    result = true;
                }
                //username and password have been existed
            }//connection has been available
        } finally {
            if (stm != null) {
                stm.close();
            }

            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
