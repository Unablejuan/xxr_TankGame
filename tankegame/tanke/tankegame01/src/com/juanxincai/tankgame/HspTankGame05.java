package com.juanxincai.tankgame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class HspTankGame05 extends JFrame {

    MyPanel mp = null;

    public static void main(String[] args) {

        HspTankGame05 game = new HspTankGame05();
    }


    public HspTankGame05() {
        System.out.println("请输入选择，1：新游戏，2：继续上局");
        String key = (new Scanner(System.in)).next();
        mp = new MyPanel(key);
        //启动线程,将mp放入到thread
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(1400, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame 中增加响应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
//                System.out.println("监听到关闭窗口");
                System.exit(0);
            }
        });
    }
}
