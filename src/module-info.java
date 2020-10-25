module Gestion.Magasin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens market;
    opens market.controller;
    opens market.entities;
    opens market.entities.payement;
    opens market.fxml;
}