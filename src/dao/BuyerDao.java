/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Buyer;
import model.ComboItem;
import java.util.List;

public interface BuyerDao {

    List<ComboItem> getBuyerComboItems(); // id + label for combo

    Buyer getById(int id);                // optional: buyer details
    int insert(Buyer b);
}


