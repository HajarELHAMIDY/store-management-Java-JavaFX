package market.controller;

import bank.Response;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import market.connection.Client;
import bank.DataSend;
import market.connection.Configuration;
import market.entities.payement.Check;
import market.entities.payement.Online;
import market.entities.payement.Payment;
import market.managment.payment.OnlineManagment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;

public class ControllerOnline {
    @FXML
    TextField idOnline;
    @FXML
    TextField numCart;
    @FXML
    TextField anneeFin;
    @FXML
    TextField moisFin;
    @FXML
    TextField cvv;
    @FXML
    Label require;
    @FXML
    Button btnvalid;
    @FXML
    Button btncancel;
    private ControllerPayment paymentControler;
    private double montant;
    private Payment payment;
    private OnlineManagment onlineManagment=OnlineManagment.getInstance();
    public void ajoutOnline(ActionEvent actionEvent) {
        DataSend dataSend=new DataSend(numCart.getText(),anneeFin.getText(),moisFin.getText(),cvv.getText(), montant, Configuration.numAccount);
        Socket client;
        ObjectOutputStream output;
        ObjectInputStream input;
        try {
            client=new Socket("127.0.0.1",3333);
            output=new ObjectOutputStream(client.getOutputStream());
            input=new ObjectInputStream(client.getInputStream());
            output.writeObject(dataSend);
            Response res= (Response) input.readObject();
            output.flush();
            System.out.println(res);
            if (res.getState()){
                alertDeletAndUpdate("Payment par Cart",res.getMsg());
                Online o=onlineManagment.add(numCart.getText(),Integer.parseInt(anneeFin.getText()),Integer.parseInt(moisFin.getText()),Integer.parseInt(cvv.getText()));
                paymentControler.setPayementCreat(o);
            }else {
                require.setTextFill(Color.RED);
                require.setText(res.getMsg());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    public void cancel(ActionEvent actionEvent) {
        ((Stage)((Node)actionEvent.getSource()).getScene().getWindow()).close();
    }

    public boolean alertDeletAndUpdate(String titel,String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
        return true;
    }
    public void setData(ControllerPayment paymentControler, double m, Payment payment) {
        this.paymentControler = paymentControler;
        this.montant=m;
        this.payment=payment;
        iniWindow();
    }
    private void iniWindow() {
        if (this.payment!=null){
            Online on=(Online) this.payment.getPayementItem();
            idOnline.setText(Long.toString(on.getId()));
            numCart.setText(on.getNumCart());
            anneeFin.setText(Integer.toString(on.getAnnee_fin()));
            moisFin.setText(Integer.toString(on.getMois_fin()));
            cvv.setText(Integer.toString(on.getCvv()));
            idOnline.setDisable(true);
            numCart.setDisable(true);
            anneeFin.setDisable(true);
            moisFin.setDisable(true);
            cvv.setDisable(true);
            btnvalid.setDisable(true);
        }
    }
}
