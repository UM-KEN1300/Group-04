package com.crazyputting3d.Sounds;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * The Sounds class is used for sound outputs during game play and simulations.
 * author Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
 *          Gabrijel Radovčić, Elza Strazda
 * version 1.0
 * since   2021-03-11
 */

public class Sounds implements Runnable{

    private String filePath = "3dcrazyputting\\assets\\";

    private File fileName0;
    private File fileName1;
    private File fileName2;
    private File fileName3;
    private File fileName4;
    private File fileName5;
    private File fileName6;
    private File fileName7;

    /**
     * Constructor assigns each file it's corresponding .wav file name as a start-up.
     */

    public Sounds(){
        fileName0 = new File(filePath+"ballmove.wav");
        fileName1 = new File(filePath+"water1.wav");
        fileName2 = new File(filePath+"holein.wav");
        fileName3 = new File(filePath+"tree.wav");
        fileName4 = new File(filePath+"background1.wav");
        fileName5 = new File(filePath+"gabrijel_god.wav");
        fileName6 = new File(filePath+"wallhit.wav");
        fileName7 = new File(filePath+"victory.wav");

    }

    /**
     * ball_hit() creates a new thread in every run, where the clip can run without interrupting the main thread.
     * Outputs the sound for the initial ball hit.
     */

    public void ball_hit(){
        new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip_temp = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileName0);
                    clip_temp.open(inputStream);
                    clip_temp.start();
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println(" ");
                }
            }
        }).start();
    }

    /**
     * ball_water() creates a new thread in every run, where the clip can run without interrupting the main thread.
     * Outputs the sound when the ball falls in the water.
     */

    public void ball_water(){
        new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip_temp = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileName1);
                    clip_temp.open(inputStream);
                    clip_temp.start();
                    Thread.sleep(900);
                } catch (Exception e) {
                    System.out.println(" ");
                }
            }
        }).start();
    }

    /**
     * ball_inhole() creates a new thread in every run, where the clip can run without interrupting the main thread.
     * Outputs the sound when the ball falls in the hole.
     */

    public void ball_inhole(){
        new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip_temp = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileName2);
                    clip_temp.open(inputStream);
                    clip_temp.start();
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * tree_hit() creates a new thread in every run, where the clip can run without interrupting the main thread.
     * Outputs the sound when the ball hits the tree.
     */

    public void tree_hit(){
        new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip_temp = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileName3);
                    clip_temp.open(inputStream);
                    clip_temp.start();
                    Thread.sleep(200);
                    clip_temp.close();
                } catch (Exception e) {
                    System.out.println(" ");
                }
            }
        }).start();
    }

    /**
     * background() creates a new thread in every run, where the clip can run without interrupting the main thread.
     * Outputs background music.
     */

    public Thread background(){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip_temp = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileName4);
                    clip_temp.open(inputStream);
                    clip_temp.start();
                    clip_temp.loop(1000);
                    Thread.sleep(1300);
                } catch (Exception e) {
                }
            }
        });
        return thread;
    }

    /**
     * background2() creates a new thread in every run, where the clip can run without interrupting the main thread.
     * Outputs easter egg. "press G in-game"
     */

    public Thread background2(){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip_temp = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileName5);
                    clip_temp.open(inputStream);
                    clip_temp.start();
                    Thread.sleep(1300);
                } catch (Exception e) {
                }
            }
        });
        return thread;
    }

    /**
     * wall_hit() creates a new thread in every run, where the clip can run without interrupting the main thread.
     * Outputs the sound when the ball hits the edges of the map.
     */

    public void wall_hit(){
        new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip_temp = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileName6);
                    clip_temp.open(inputStream);
                    clip_temp.start();
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.out.println(" ");
                }
            }
        }).start();
    }

    /**
     * victory() creates a new thread in every run, where the clip can run without interrupting the main thread.
     * Outputs the sound when the game is over and the victory screen pops out.
     */

    public void victory(){
        new Thread(new Runnable(){
            public void run(){
                try{
                    Clip clip_temp = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(fileName7);
                    clip_temp.open(inputStream);
                    clip_temp.start();
                    Thread.sleep(900);
                } catch (Exception e) {
                    System.out.println(" ");
                }
            }
        }).start();
    }

    @Override
    public void run() {
    }
}