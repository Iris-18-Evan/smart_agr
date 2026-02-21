/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dao;

import model.User;
import java.sql.SQLException;


public interface UserDao {
    User findByEmail(String email) throws SQLException;
    User authenticate(String email, String password) throws SQLException;  
    
}


