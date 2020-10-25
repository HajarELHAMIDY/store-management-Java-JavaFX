package market.fxml;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import market.entities.Category;
import market.entities.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import market.managment.CategoryManagment;
import market.managment.ProductManagment;

import java.util.ArrayList;
import java.util.List;


public class FormeProduit {
    private BorderPane root;
    private GridPane grid;
    private Pane paneTitle;
    private VBox crud;
    private Label labelTitle;
    private Button btnAjouter;
    private Button btnModifier;
    private Button btnSupprimer;
    private Button btnResetForm;
    private Label labelId;
    private Label labelCat;
    private Label labelDes;
    private ChoiceBox<Category>  listCat ;
    private Label labelPrixAchat;
    private Label labelPrixVente;
    private Label labelQuantite;
    private TextField textId;
    private TextField textDes;
    private TextField textPrixAchat;
    private TextField textPrixVente;
    private TextField textQuantite;
    private VBox tablePartie;
    private TextField search;
    private TableView<Produit> table;
    private Label require;
    CategoryManagment categoryManagment= CategoryManagment.getInstance();
    ProductManagment productManagment = ProductManagment.getInstance();
    ObservableList<Category> cats= FXCollections.observableArrayList(categoryManagment.getAll());
    ObservableList<Produit> observableTable = FXCollections.observableArrayList();
    public FormeProduit(){

        initPane();
        createContent();
        initTableProduct();
        updateTable();
        textId.setDisable(true);
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        btnResetForm.setDisable(true);
    }


    public void createContent() {

        paneTitle.getChildren().add(labelTitle);
        crud.getChildren().addAll(btnAjouter, btnModifier, btnSupprimer,btnResetForm);
        grid.add(labelId, 1, 1);
        grid.add(textId, 2, 1);
        grid.add(labelDes, 1, 2);
        grid.add(textDes, 2, 2);
        grid.add(labelPrixAchat, 1, 3);
        grid.add(textPrixAchat, 2, 3);
        grid.add(labelPrixVente, 1, 4);
        grid.add(textPrixVente, 2, 4);
        grid.add(labelQuantite, 1, 5);
        grid.add(textQuantite, 2, 5);
        grid.add(labelCat,1,6);
        grid.add(listCat,2,6);
        grid.add(require,1,7);
        tablePartie.getChildren().addAll(search, table);
        root.setTop(paneTitle);
        root.setCenter(grid);
        root.setTop(paneTitle);
        root.setLeft(crud);
        root.setRight(tablePartie);

    }
    private void resetForm(){
        textId.clear();
        textDes.clear();
        textPrixAchat.clear();
        textPrixVente.clear();
        textQuantite.clear();
        listCat.getSelectionModel().clearSelection();
        require.setText("");
        btnModifier.setDisable(true);
        btnSupprimer.setDisable(true);
        btnResetForm.setDisable(true);
        btnAjouter.setDisable(false);
    }
    public void initPane() {

        labelTitle = new Label("Gestion de Produit");
        table = new TableView();
        search = new TextField();
        tablePartie = new VBox();
        labelTitle.getStyleClass().add("custom-label");
        labelTitle.setTranslateX(300);
        labelCat = new Label("Categorie : ");
        listCat = new ChoiceBox(cats);
        labelCat.getStyleClass().add("custom-label");
        labelCat.setTranslateX(300);

        grid = new GridPane();
        grid.getStyleClass().add("custom-pane-center");
        root = new BorderPane();

        paneTitle = new Pane();
        paneTitle.getStyleClass().add("custom-pane-top");
        crud = new VBox(10);
        crud.getStyleClass().add("custom-pane-left");

        btnAjouter = new Button("Ajouter");
        btnAjouter.getStyleClass().add("custom-button");
        btnModifier = new Button("Modifier");
        btnModifier.getStyleClass().add("custom-button");
        btnSupprimer = new Button("Supprimer");
        btnSupprimer.getStyleClass().add("custom-button");
        btnResetForm = new Button("Reset");
        btnResetForm.getStyleClass().add("custom-button");
        labelId = new Label("Id : ");
        labelId.setFont(new Font("Calibri", 16));

        labelId.getStyleClass().add("custom-label");
        labelId.setTextFill(Color.BLACK);
        labelDes = new Label("Designation : ");
        labelDes.getStyleClass().add("custom-label");
        labelPrixAchat = new Label("Prix Achat : ");
        labelPrixAchat.getStyleClass().add("custom-label");
        labelPrixVente = new Label("Prix Vente : ");
        labelPrixVente.getStyleClass().add("custom-label");
        labelCat = new Label("Categorie : ");
        labelCat.getStyleClass().add("custom-label");
        labelQuantite = new Label("Quantite : ");
        labelQuantite.getStyleClass().add("custom-label");
        textId = new TextField();
        textDes = new TextField();
        textPrixAchat = new TextField();
        textPrixVente = new TextField();
        textQuantite=new TextField();
        require=new Label();
        //listCat.setPrefWidth(150);

        btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(textId.getText().isEmpty()||textPrixVente.getText().isEmpty()||textPrixAchat.getText().isEmpty()||textPrixVente.getText().isEmpty()||textQuantite.getText().isEmpty()||listCat.getValue()==null){
                    require.setText("Tous les champs et obligatoire  il faut selectionee produit modifier apartire tableur");
                }
                {
                    productManagment.add(textDes.getText(), Double.parseDouble(textPrixAchat.getText()), Double.parseDouble(textPrixVente.getText()), Integer.parseInt(textQuantite.getText()), listCat.getValue());
                    resetForm();
                    updateTable();
                }
            }
        });
        btnSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (textId.getText().isEmpty()){
                    require.setText("aant de supprimer il faut selectionne un produit");
                }else {
                    productManagment.delet(Long.parseLong(textId.getText()));
                    resetForm();
                    updateTable();
                }
            }
        });
        btnModifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(textPrixVente.getText().isEmpty()||textPrixAchat.getText().isEmpty()||textPrixVente.getText().isEmpty()||textQuantite.getText().isEmpty()||listCat.getValue()==null){
                    require.setText("Tous les champs et obligatoire");
                }
                else{
                    productManagment.update(productManagment.getById(Integer.parseInt(textId.getText())), textDes.getText(), Double.parseDouble(textPrixAchat.getText()), Double.parseDouble(textPrixVente.getText()), Integer.parseInt(textQuantite.getText()), listCat.getValue());
                    resetForm();
                    updateTable();
                }
            }
        });

        btnResetForm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                resetForm();
            }
        });

        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                List<Produit> lists=new ArrayList();
                for (Produit p:productManagment.getAll()){
                    if (p.getDesignation().toLowerCase().contains(newValue.toLowerCase())||Long.toString(p.getId()).toLowerCase().contains(newValue.toLowerCase())||Double.toString(p.getPrixAchat()).toLowerCase().contains(newValue.toLowerCase())||Double.toString(p.getPrixVente()).toLowerCase().contains(newValue.toLowerCase())||p.getCategory().getNom().toLowerCase().contains(newValue.toLowerCase())){
                        lists.add(p);
                    }
                }
                updateTable(lists);
            }
        });
        table.setRowFactory( tv -> {
            TableRow<Produit> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    Produit rowData = row.getItem();
                    textId.setText(Long.toString(rowData.getId()));
                    textDes.setText(rowData.getDesignation());
                    textPrixAchat.setText(Double.toString(rowData.getPrixAchat()));
                    textPrixVente.setText(Double.toString(rowData.getPrixVente()));
                    textQuantite.setText(Double.toString(rowData.getQuantite()));
                    listCat.getSelectionModel().select(rowData.getCategory());
                    btnModifier.setDisable(false);
                    btnSupprimer.setDisable(false);
                    btnResetForm.setDisable(false);
                    btnAjouter.setDisable(true);

                }
            });
            return row ;
        });
    }

    private void updateTable(){
        observableTable.setAll(productManagment.getAll());
        table.setItems(observableTable);
    }
    private void updateTable(List<Produit> prds){
        observableTable.setAll(prds);
        table.setItems(observableTable);
    }
    public void initTableProduct() {
        TableColumn<Produit, Long> produitIdColon = new TableColumn("Id");
        produitIdColon.setCellValueFactory(new PropertyValueFactory("id"));
        produitIdColon.setPrefWidth(50);

        TableColumn<Produit, String> produitDesignationColon = new TableColumn("Désignation");
        produitDesignationColon.setCellValueFactory(new PropertyValueFactory("designation"));
        produitDesignationColon.setPrefWidth(100);

        TableColumn<Produit, Double> produitPrixAchatColon = new TableColumn("Prix Achat");
        produitPrixAchatColon.setCellValueFactory(new PropertyValueFactory("prixAchat"));
        produitPrixAchatColon.setPrefWidth(70);

        TableColumn<Produit, Double> produitPrixVenteColon = new TableColumn("Prix Vente");
        produitPrixVenteColon.setCellValueFactory(new PropertyValueFactory("prixVente"));
        produitPrixVenteColon.setPrefWidth(70);

        TableColumn<Produit, Double> produitQuantiteColon = new TableColumn("Quantite");
        produitQuantiteColon.setCellValueFactory(new PropertyValueFactory("quantite"));
        produitQuantiteColon.setPrefWidth(60);

        TableColumn<Produit, Category> produitCatColon = new TableColumn("Category");
        produitCatColon.setCellValueFactory(new PropertyValueFactory("category"));
        produitCatColon.setPrefWidth(80);

        table.getColumns().addAll(produitIdColon, produitDesignationColon, produitPrixAchatColon,produitPrixVenteColon,produitQuantiteColon,produitCatColon);


        updateTable();

    }
    public BorderPane getRoot(){
        return root;
    }

}