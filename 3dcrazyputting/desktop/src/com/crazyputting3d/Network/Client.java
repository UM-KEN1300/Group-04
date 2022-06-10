package com.crazyputting3d.Network;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Client {

    private final String url = "https://daa2-137-120-131-94.eu.ngrok.io";
    public static ArrayList<ArrayList<Integer>> playerdata;

    private String run(String url){
        try {
            final URLConnection c = new URL(url).openConnection();
            c.connect();
            try (InputStream is = c.getInputStream()) {
                final byte[] buf = new byte[1024];
                final StringBuilder b = new StringBuilder();
                int read = 0;
                while ((read = is.read(buf)) != -1) {
                    b.append(new String(buf, 0, read));
                }
                return b.toString();
        }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<ArrayList<Integer>> GetPlayerData() {
        playerdata =  parse(run(url+"/players"));
        return playerdata;
    }

    public void updatePlayer(int playerId) {
        run(url+"/player/update/"+playerId);
    }

    public void deletePlayer(int playerId) {
        run(url+"/player/delete/"+playerId);
    }

    public void createPlayer(int playerId) {
        run(url+"/player/add/"+playerId);
    }

    public void resetData() {
        run(url+"/reset");
    }


    private ArrayList<ArrayList<Integer>> parse(String original) {
        //Example JSON String to parse:
        //[{"playerScore":0,"playerId":0},{"playerScore":0,"playerId":1}]
        int start;
        int end;
        int score;
        ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
        boolean parsedDone = false;
        
        while (parsedDone == false && !(original.length()<3)) {
            ArrayList<Integer> playerdata = new ArrayList<Integer>();

            start  = original.indexOf(":");
            end = original.indexOf(",");
            score = Integer.parseInt(original.substring(start+1, end));
            original = original.substring(end+1);

            start = original.indexOf(":");
            end = original.indexOf("}");
            
            playerdata.add(Integer.parseInt(original.substring(start+1, end)));
            playerdata.add(score);
            data.add(playerdata);
            
            original = original.substring(end+2);
        }
        return data;
    }

}
