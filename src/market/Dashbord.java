package market;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import market.fxml.FormeProduit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashbord extends Application {
    private Scene scene ;
    private BorderPane root;
    private VBox espaces;
    private Button btnClient;
    private Button btnProduit;
    private Button btnCategory;
    private Button btnOrder;
    private Label labelTitre;
    private HBox paneTop;
    private Parent espaceClient = FXMLLoader.load(getClass().getResource("fxml/client.fxml"));
    private FormeProduit espaceProduit = new FormeProduit();
    private Parent espaceCategory = FXMLLoader.load(getClass().getResource("fxml/category.fxml"));
    private Parent espaceOrder = FXMLLoader.load(getClass().getResource("fxml/order.fxml"));

    public Dashbord() throws IOException {
    }

    public void createContent(){
        initPane();
        espaces.getChildren().addAll(btnProduit,btnCategory,btnClient,btnOrder);
        root.setTop(labelTitre);
        root.setLeft(espaces);
        btnClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scene.setRoot(espaceClient);
            }
        });
        btnCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scene.setRoot(espaceCategory);
            }
        });
        btnProduit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scene.setRoot(espaceProduit.getRoot());
            }
        });
        btnOrder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scene.setRoot(espaceOrder);
            }
        });
    }
    public void initPane(){
        paneTop = new HBox();
        espaces = new VBox(10);
        root = new BorderPane();
        labelTitre = new Label("Gestion de magazin");
        btnClient = new Button("Espace Clients");
        btnCategory = new Button("Espace Categories");
        btnProduit = new Button("Espace Produit");
        btnOrder=new Button("Espace Commande");
        scene = new Scene(root);

    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Gestion Magazin");
        stage.setWidth(1000);
        stage.setHeight(600);
        //initPane();
        createContent();
        stage.setScene(scene);
        stage.getScene().getStylesheets().add("Mycss.css");
        stage.show();

    }
    public static void main(String args[]){
        launch(args);
    }
}
