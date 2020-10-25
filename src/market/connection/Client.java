package market.connection;

import bank.DataSend;
import bank.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    Socket client;
    ObjectOutputStream output;
    ObjectInputStream input;
    public Client(){
        try {
            client=new Socket("127.0.0.1",3333);
            output=new ObjectOutputStream(client.getOutputStream());
            input=new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendData(DataSend d){
        try {
            output.writeObject(d);
            Response r= (Response) input.readObject();
            System.out.println(r);
            output.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //DataSend dataSend=new DataSend(numCart.getText(),anneeFin.getText(),moisFin.getText(),cvv.getText(), montant, Configuration.numAccount);

        Client c=new Client();
        //c.sendData(dataSend);
    }
}
