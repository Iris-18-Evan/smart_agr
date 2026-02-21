/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;


public class Role implements Serializable {
    private int id;
    private String name; // e.g., "Admin", "Farmer", "FieldOfficer", "Buyer"

    public Role() {}

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }

    // setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
