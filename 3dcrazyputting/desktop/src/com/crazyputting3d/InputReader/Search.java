package com.crazyputting3d.InputReader;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;

/**
 * The Search class is used to read the Input file and make get_methods for each variable
 * author  Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 * Gabrijel Radovčić, Elza Strazda
 * version 2.0
 * since   2022-05-11
 */

public class Search {
    public ArrayList<String[]> words =  new ArrayList<>();
    String filename;
    private int cnt_x_tree = 1;
    private int cnt_y_tree = 1;
    private int cnt_r_tree = 1;
    private int cnt_x_sand = 1;
    private int cnt_y_sand = 1;
    private int cnt_v0x = 1;
    private int cnt_v0y = 1;

    /**
     * Constructor method calls run() as a start-up.
     */

    public Search(String filename) throws IOException {
        this.filename = filename;
        run();
    }

    /**
     * Run() method reads trough the input file and stores the variable names at index 0,
     * and the corresponding values at index 1. Skipping "=" == st[1].
     */

    public void run() throws IOException{
        FileReader read=new FileReader("3dcrazyputting\\assets\\"+filename);
        BufferedReader buffer_reader = new BufferedReader(read);
        String string;

        while((string = buffer_reader.readLine()) != null) {
            String[] st = string.split(" ");
            String [] a = new String[2];
            a[1]=st[2];
            a[0]=st[0];
            words.add(a);
        }
        buffer_reader.close();
    }

    /**
     * get_x0() runs through string aiming to find "x0"
     * return  double value for x0
     */

    public double get_x0(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("x0")){
                x0 = Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_y0() runs through string aiming to find "y0"
     * return  double value for y0
     */

    public double get_y0(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("y0")){
                x0 = Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_xt() runs through string aiming to find "xt"
     * return  double value for xt
     */

    public double get_xt(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("xt")){
                x0 = Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_yt() runs through string aiming to find "yt"
     * return  double value for yt
     */

    public double get_yt(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("yt")){
                x0 = Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_r() runs through string aiming to find "r"
     * return  double value for r
     */

    public double get_r(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("r")){
                x0 = Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_muk() runs through string aiming to find "muk"
     * return  double value for muk
     */

    public double get_muk(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("muk")){
                x0 = Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_treeX() runs through string aiming to find "treeX" + it's corresponding number. eg: treeX1, treeX2
     * return  double [] with all values for treeX
     */

    public double [] get_treeX(){
        double [] x0 = new double [20];
        int cnt = 0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("TreeX" + cnt_x_tree)){
                x0[cnt] = Double.parseDouble(words.get(i)[1]);
                cnt++;
                cnt_x_tree++;
            }
        }
        double [] temp_FINAL = new double[cnt_x_tree-1];
        for (int s = 0; s < temp_FINAL.length; s++){
            temp_FINAL[s] = x0[s];
        }
        return temp_FINAL;
    }

    /**
     * get_treeY() runs through string aiming to find "treeY" + it's corresponding number. eg: treeY1, treeY2
     * return  double [] with all values for treeY
     */

    public double [] get_treeY(){
        double [] x0 = new double [20] ;
        int cnt = 0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("TreeY" + cnt_y_tree)){
                x0[cnt] = Double.parseDouble(words.get(i)[1]);
                cnt++;
                cnt_y_tree++;
            }
        }
        double [] temp_FINAL = new double[cnt_y_tree-1];
        for (int s = 0; s < temp_FINAL.length; s++){
            temp_FINAL[s] = x0[s];
        }
        return temp_FINAL;
    }

    /**
     * get_treeR() runs through string aiming to find "treeR" + it's corresponding number. eg: treeR1, treeR2
     * return  double [] with all values for treeR
     */

    public double [] get_treeR(){
        double [] x0 = new double [20] ;
        int cnt = 0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("TreeR" + cnt_r_tree)){
                x0[cnt] = Double.parseDouble(words.get(i)[1]);
                cnt++;
                cnt_r_tree++;
            }
        }
        double [] temp_FINAL = new double[cnt_r_tree-1];
        for (int s = 0; s < temp_FINAL.length; s++){
            temp_FINAL[s] = x0[s];
        }
        return temp_FINAL;
    }

    /**
     * get_mus() runs through string aiming to find "mus"
     * return  double value for mus
     */

    public double get_mus(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("mus")){
                x0 = Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_heightProfile() runs through string aiming to find "heightProfile"
     * return  double value for heightProfile
     */

    public String get_heightProfile(){
        String x0 = "";
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("heightProfile")){
                x0 = words.get(i)[1];
            }
        }
        return x0;
    }

    /**
     * get_sandPitX() runs through string aiming to find "sandPitX" + it's corresponding number. eg: sandPitX1, sandPitX2
     * return  double [] with all values for sandPitX, where sandPitX_start and sandPitX_end are stored after eachother;
     * eg {sandPitX1_start, sandPitX1_end, sandPitX2_start, sandPitX2_end}
     */

    public double [] get_sandPitX(){
        double [] temp = new double[30];
        int tempd = 0;
        for(int i = 0;i < words.size();i++){
            String x0 = "";
            String x01 = "";
            int cnt = 0;
            if(words.get(i)[0].equals("sandPitX" + cnt_x_sand)){
                for(int k = 0; k < words.get(i)[1].length(); k++){
                    if(words.get(i)[1].charAt(k) == '<' && cnt == 0){
                        for(int j = 0; j < k; j++){
                            x0 += words.get(i)[1].charAt(j);
                        }
                        temp[tempd] = Double.parseDouble(x0);
                        cnt++;
                        tempd++;
                    }else if(words.get(i)[1].charAt(k) == '<' && cnt == 1){
                        for(int j = k+1; j < words.get(i)[1].length(); j++){
                            x01 += words.get(i)[1].charAt(j);
                        }
                        temp[tempd] = Double.parseDouble(x01);
                        cnt++;
                        tempd++;
                    }
                }
                cnt_x_sand++;
            }
        }
        double [] temp_FINAL = new double[tempd];
        for (int s = 0; s < temp_FINAL.length; s++){
            temp_FINAL[s] = temp[s];
        }
        return temp_FINAL;
    }

    /**
     * get_sandPitY() runs through string aiming to find "sandPitY" + it's corresponding number. eg: sandPitY1, sandPitY2
     * return  double [] with all values for sandPitY, where sandPitY_start and sandPitY_end are stored after eachother;
     * eg {sandPitY1_start, sandPitY1_end, sandPitY2_start, sandPitY2_end}
     */

    public double [] get_sandPitY(){
        double [] temp = new double[30];
        int tempd = 0;
        for(int i = 0;i < words.size();i++){
            String x0 = "";
            String x01 = "";
            int cnt = 0;
            if(words.get(i)[0].equals("sandPitY" + cnt_y_sand)){
                for(int k = 0; k < words.get(i)[1].length(); k++){
                    if(words.get(i)[1].charAt(k) == '<' && cnt == 0){
                        for(int j = 0; j < k; j++){
                            x0 += words.get(i)[1].charAt(j);
                        }
                        temp[tempd] = Double.parseDouble(x0);
                        cnt++;
                        tempd++;
                    }else if(words.get(i)[1].charAt(k) == '<' && cnt == 1){
                        for(int j = k+1; j < words.get(i)[1].length(); j++){
                            x01 += words.get(i)[1].charAt(j);
                        }
                        temp[tempd] = Double.parseDouble(x01);
                        cnt++;
                        tempd++;
                    }
                }
                cnt_y_sand++;
            }

        }
        double [] temp_FINAL = new double[tempd];
        for (int s = 0; s < temp_FINAL.length; s++){
            temp_FINAL[s] = temp[s];
        }
        return temp_FINAL;
    }

    /**
     * get_muks() runs through string aiming to find "muks"
     * return  double value for muks
     */

    public double get_muks(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("muks")){
                x0 += Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_muss() runs through string aiming to find "muss"
     * return  double value for muss
     */

    public double get_muss(){
        double x0 = 0.0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("muss")){
                x0 = Double.parseDouble(words.get(i)[1]);
            }
        }
        return x0;
    }

    /**
     * get_v0x() runs through string aiming to find "v0x" + it's corresponding number. eg: v0x1, v0x2
     * return double [] with all values for v0x
     */

    public double [] get_v0x(){
        double [] x0 = new double [100*(int)Math.pow(2,15)];
        int cnt = 0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("v0x" + cnt_v0x)){
                x0[cnt] = Double.parseDouble(words.get(i)[1]);
                cnt++;
                cnt_v0x++;
            }
        }
        double [] temp_FINAL = new double[cnt_v0x-1];
        for (int s = 0; s < temp_FINAL.length; s++){
            temp_FINAL[s] = x0[s];
        }
        return temp_FINAL;
    }

    /**
     * get_v0y() runs through string aiming to find "v0y" + it's corresponding number. eg: v0y1, v0y2
     * return double [] with all values for v0y
     */

    public double [] get_v0y(){
        double [] x0 = new double [100*(int)Math.pow(2,15)];
        int cnt = 0;
        for(int i = 0;i < words.size();i++){
            if(words.get(i)[0].equals("v0y" + cnt_v0y)){
                x0[cnt] = Double.parseDouble(words.get(i)[1]);
                cnt++;
                cnt_v0y++;
            }
        }
        double [] temp_FINAL = new double[cnt_v0y-1];
        for (int s = 0; s < temp_FINAL.length; s++){
            temp_FINAL[s] = x0[s];
        }
        return temp_FINAL;
    }
}