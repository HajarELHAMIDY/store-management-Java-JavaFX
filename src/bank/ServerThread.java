package bank;

import bank.data.Card;
import bank.data.User;
import bank.managment.AccountManagment;
import bank.managment.CardManagment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerThread extends Thread {

    private Socket server;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private CardManagment cardManagment=CardManagment.getInstance();
    private AccountManagment accountManagment=AccountManagment.getInstance();
    public ServerThread(Socket serverEnd) {
        try {

            this.server = serverEnd;
            input = new ObjectInputStream(server.getInputStream());
            output = new ObjectOutputStream(server.getOutputStream());
        }catch (IOException  e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        Response response=null;
        try {
            DataSend data= (DataSend) input.readObject();
            System.out.println(data);
            Card card=cardManagment.getCard(data.getCardNum(),data.getCvv(),data.getMonth(),data.getYear());
            if (card==null)
                response=new Response(false,"Information Incorrect");
            else {
                if (!card.verificationDate())
                    response = new Response(false, "Carte Expire");
                else {
                    if (accountManagment.moneyTransfer(card, data.getMontant(), data.getNumAccount()))
                        response = new Response(true, "Payement fait");
                    else response = new Response(false, "Sold insufaisant");

                }
            }
            output.writeObject(response);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            response = new Response(false, "problime dans serveur");
        }
        finally {
            try {
                output.writeObject(response);
                output.flush();
                output.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
