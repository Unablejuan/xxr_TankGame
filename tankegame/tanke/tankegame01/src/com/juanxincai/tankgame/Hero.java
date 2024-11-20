package com.juanxincai.tankgame;


import java.util.Vector;

public class Hero extends Tank {
    //定义一个shot对象,表示一个线程
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }
    //射击
    public void shotEnemyTank() {
        //创建shot对象,根据当前hero对象方向来创建shot对象
        //最多发5颗
        if (shots.size() == 5) {
            return;
        }

        switch (getDirection()) { //得到hero方向
            case 0 ->//向上
                    shot = new Shot(getX() + 20, getY(), 0);
            case 1 ->//向右
                    shot = new Shot(getX() + 50, getY() + 30, 1);
            case 2 ->//向下
                    shot = new Shot(getX() + 20, getY() + 40, 2);
            case 3 ->//向左
                    shot = new Shot(getX() + 10, getY() + 30, 3);
        }
        //把新创建的shot放入到集合中shots；
        shots.add(shot);
        //启动shot线程
        new Thread(shot).start();

    }

}


