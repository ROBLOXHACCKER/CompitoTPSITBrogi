package com.example;

public class mainServer {
    public static void main( String[] args )
    {
        Server server = new Server();
        do{
        server.attendi();
        server.Comunica();
        }while(true);
    }
}
