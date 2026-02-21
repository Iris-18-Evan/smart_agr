/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import controller.LoginController;
import view.LoginView;

import controller.DashboardController;
import view.DashboardView;

import controller.DashboardController;
import model.User;           
import view.DashboardView;


import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginView view = new LoginView();
            new LoginController(view);             
            view.setLocationRelativeTo(null);
            view.setVisible(true);
        });
        
        /*SwingUtilities.invokeLater(() -> {
            DashboardView dashboard = new DashboardView();
            new DashboardController(dashboard, null); 
            dashboard.setVisible(true);
        });*/

    }
}


