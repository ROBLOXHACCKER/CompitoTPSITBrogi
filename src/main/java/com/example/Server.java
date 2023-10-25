package com.example;

import java.io.*;
import java.net.*;

public class Server {

    
    //colori
    String colNormale = "\u001B[0m";

    String colGiallo = "\u001B[33m";
    String colNero = "\u001B[30m";
    String colRosso = "\u001B[31m";
    String colVerde = "\u001B[32m";
    String colBlu = "\u001B[34m";
    String colMagenta = "\u001B[35m";
    String colCiano = "\u001B[36m";
    String colBianco = "\u001B[37m";
    String colViola =  "\u001B[35m";
    String colViolaAcceso = "\u001B[95m";

    //var
    ServerSocket server = null;
    Socket socketClient = null;
    int porta = 6789; // porta server
    String letto;
    float calcolo = 0;

    DataOutputStream out; 
    DataInputStream in;

     
    

    public void Comunica()
    {
        try {
                do{        
                    //Integer.ParseInt 
                    //valueOf
                    System.out.println();
                    System.out.println(colGiallo + "[SERVER] - aspetto operazione" + colNormale);
                    letto = in.readLine();
                    System.out.println(colVerde + "[-] - operazione ricevuta " + colNormale);
                    String risposta = letto;
                    try {
                        //suddivisone operazione in array partiFormato
                        String partiFormato[]= risposta.split(" ");
                        //partiFormato[0] = NUM 1
                        //partiFormato[1] = OPERAZIONE
                        //partiFormato[2] = NUM 2
                        float num1 = Float.parseFloat(partiFormato[0]);
                        float num2 = Float.parseFloat(partiFormato[2]);
                            switch (partiFormato[1]) {
                                case "+":
                                    calcolo = num1 + num2;
                                    break;
                                case "-":
                                    calcolo = num1-num2;
                                    break;
                                case "/":
                                    calcolo = num1/num2;
                                    break;
                                case "*":
                                    calcolo = num1*num2; 
                                }
                            System.out.println(colVerde + "[-] - operazione effettuata " + colNormale);
                            System.out.println(colVerde + "[-] - rispondo con " + String.valueOf(calcolo) + colNormale);
                            //mando risposta al client
                            out.writeBytes(calcolo + "\n");
                    } catch (Throwable e) {
                        out.writeBytes(colRosso + "[ERR] - Errore Formattazione\n");
                    }
                    
                
                
                }while(!letto.toLowerCase().equals("CLOSE"));

                   socketClient.close(); //chiusura connessione con client
                  System.out.println(colRosso + "[!] - CONNECTION CLOSED" + colNormale);    
                   
            } catch (IOException e) {

            e.printStackTrace();
        }

    }


    public Socket attendi() {
        try {
            System.out.println(colViola + "[PROCEDURE] - inizializzazione" + colNormale);
            server = new ServerSocket(porta);
            
            System.out.println(colViolaAcceso + "[-] Server in ascolto sulla porta: " + porta + colNormale);
            socketClient = server.accept();
            System.out.println(colViolaAcceso + "[-] connessione effettuata" + colNormale);
            server.close();
            
            in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
            
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return socketClient;


    }

    
}
