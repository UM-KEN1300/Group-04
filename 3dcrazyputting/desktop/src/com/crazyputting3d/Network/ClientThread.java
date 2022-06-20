package com.crazyputting3d.Network;

/**
 * Authors: Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van
 * Gelder, Trinh Le, Gabrijel Radovčić, Elza Strazda
 * version 3.0
 * since 2022-05-11
 */

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
