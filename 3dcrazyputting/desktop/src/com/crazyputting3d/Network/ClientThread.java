package com.crazyputting3d.Network;

public class ClientThread extends Thread {
    private Client client = new Client();

    public void run() {
        client.GetPlayerData();
        while(true) {
            client.GetPlayerData();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Client getClient() {
        return client;
    }
}
