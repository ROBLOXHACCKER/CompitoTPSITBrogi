package com.example;

import java.io.*;
import java.net.*;

public class Client {

    //colori
    String colNormale = "\u001B[0m";

    String colViolaAcceso = "\u001B[95m";
    String colGiallo = "\u001B[33m";
    String colNero = "\u001B[30m";
    String colRosso = "\u001B[31m";
    String colVerde = "\u001B[32m";
    String colBlu = "\u001B[34m";
    String colMagenta = "\u001B[35m";
    String colCiano = "\u001B[36m";
    String colBianco = "\u001B[37m";
    String colViola =  "\u001B[35m";
    

    Socket mioSocket = null;
    int porta = 6789; // porta server
    BufferedReader tastiera;
    String messaggio;

    DataOutputStream out; 
    DataInputStream in;


    public void Comunica()
    {
        try {

            do{    
                tastiera = new BufferedReader(new InputStreamReader(System.in));
                System.out.print(colGiallo + "[CLIENT] - Inviare operazione con il seguente formato" +colMagenta+" NUM1[SPAZIO]OPERAZIONE[SPAZIO]NUM2: " + colNormale);
                messaggio = tastiera.readLine();
                System.out.println(colVerde +"[-] Invio: " + colNormale);
                out.writeBytes( messaggio + "\n");
                System.out.println(colVerde +"[-] Attesa calcolo in esecuzione" + colNormale);
                String ricevuta = in.readLine();
                System.out.println(colVerde +"[-] Riposta del server: " + ricevuta + colNormale);
            }while(!messaggio.toLowerCase().equals("CLOSE"));   
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Socket connetti()
    {
        try {
            System.out.println(colViola +"[PROCEDURE] - provo connessione server" + colNormale);
            mioSocket = new Socket("localhost", porta);
            System.out.println(colViolaAcceso +"[-] - connessione effettuata" + colNormale);
            in = new DataInputStream(mioSocket.getInputStream());
            out = new DataOutputStream(mioSocket.getOutputStream());
            
        
        } catch (UnknownHostException e) {
            System.err.println(colRosso + "[ERR] - Host sconosciuto" + colNormale);
        } catch (IOException e) {
            System.err.println(colRosso +"[ERR] - Impossibile stabilire connessione" + colNormale);
        }

        return mioSocket;


    }

}
