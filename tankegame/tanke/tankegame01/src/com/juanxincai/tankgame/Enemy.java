package com.juanxincai.tankgame;

import java.util.Vector;

//@SuppressWarnings({"all"})
public class Enemy extends Tank implements Runnable {
    //在敌人坦克类，使用vector保存多个Shot
    Vector<Shot> shots = new Vector<>();
    //增加一个成员，enemyTank可以得到敌人坦克的vector，查看是否重叠
    Vector<Enemy> enemyTanks = new Vector<>();
    boolean isLive = true;

    public Enemy(int x, int y) {
        super(x, y);
    }

    //这里提供一个方法，可以将MyPanel 的成员Vector<Enemy> enemyTanks = new Vector<>();
    //设置到Enemy 的一个成员 enemyTanks
    public void setEnemyTanks(Vector<Enemy> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法判断当前的敌人坦克是否和enemyTanks 中其他的坦克发生重叠
    public boolean isTouchEnemyTank() {
        //判断当前坦克(this)坦克
        switch (this.getDirection()) {
            case 0: //上
                //让当前this坦克和其他坦克进行比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一个坦克
                    Enemy enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //1. 如果坦克是上/下 x的范围在[enemyTank.getX(), (enemyTank.getX() + 40)]
                        //y的范围在[enemyTank.getY(), (enemyTank.getY() + 60)]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 3) {
                            //2.当前坦克左上角的位置坐标[this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //2.右上角的位置坐标[this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //敌人坦克是左/右
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 2) {
                            //
                            if (this.getX() >= enemyTank.getX() - 10
                                    && this.getX() <= enemyTank.getX() + 50
                                    && this.getY() >= enemyTank.getY() + 10
                                    && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                            //2.右上角的位置坐标[this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX() - 10
                                    && this.getX() + 40 <= enemyTank.getX() + 50
                                    && this.getY() >= enemyTank.getY() + 10
                                    && this.getY() <= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1: //右
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一个坦克
                    Enemy enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //1. 如果坦克是上/下 x的范围在[enemyTank.getX(), (enemyTank.getX() + 40)]
                        //y的范围在[enemyTank.getY(), (enemyTank.getY() + 60)]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 3) {
                            //2.当前坦克右上角的位置坐标[this.getX()+50, this.getY() + 10]
                            if (this.getX() + 50 >= enemyTank.getX()
                                    && this.getX() + 50 <= enemyTank.getX() + 40
                                    && this.getY() + 10 >= enemyTank.getY()
                                    && this.getY() + 10 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //2.右下角的位置坐标[this.getX() + 50, this.getY() + 50]
                            if (this.getX() + 50 >= enemyTank.getX()
                                    && this.getX() + 50 <= enemyTank.getX() + 40
                                    && this.getY() + 50 >= enemyTank.getY()
                                    && this.getY() + 50 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //敌人坦克是左/右
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 2) {
                            //
                            if (this.getX() + 50 >= enemyTank.getX() - 10
                                    && this.getX() + 50 <= enemyTank.getX() + 50
                                    && this.getY() + 10 >= enemyTank.getY() + 10
                                    && this.getY() + 10 <= enemyTank.getY() + 50) {
                                return true;
                            }
                            //2.右下角的位置坐标[this.getX() + 50, this.getY() + 50]
                            if (this.getX() + 50 >= enemyTank.getX() - 10
                                    && this.getX() + 50 <= enemyTank.getX() + 50
                                    && this.getY() + 50 >= enemyTank.getY() + 10
                                    && this.getY() + 50 <= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //下
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一个坦克
                    Enemy enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //1. 如果坦克是上/下 x的范围在[enemyTank.getX(), (enemyTank.getX() + 40)]
                        //y的范围在[enemyTank.getY(), (enemyTank.getY() + 60)]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 3) {
                            //2.当前坦克左下角的位置坐标[this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //2.右下角的位置坐标[this.getX() + 40, this.getY() + 60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //敌人坦克是左/右
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 2) {
                            //
                            if (this.getX() >= enemyTank.getX() - 10
                                    && this.getX() <= enemyTank.getX() + 50
                                    && this.getY() + 60 >= enemyTank.getY() + 10
                                    && this.getY() + 60 <= enemyTank.getY() + 50) {
                                return true;
                            }
                            //2.右下角的位置坐标[this.getX() + 50, this.getY() + 50]
                            if (this.getX() + 40 >= enemyTank.getX() - 10
                                    && this.getX() + 40 <= enemyTank.getX() + 50
                                    && this.getY() + 60 >= enemyTank.getY() + 10
                                    && this.getY() + 60 <= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //左
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector中取出一个坦克
                    Enemy enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if (enemyTank != this) {
                        //如果敌人坦克是上/下
                        //1. 如果坦克是上/下 x的范围在[enemyTank.getX(), (enemyTank.getX() + 40)]
                        //y的范围在[enemyTank.getY(), (enemyTank.getY() + 60)]
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 3) {
                            //2.当前坦克左上角的位置坐标[this.getX() - 10, this.getY() + 10]
                            if (this.getX() -10 >= enemyTank.getX()
                                    && this.getX() -10 <= enemyTank.getX() + 40
                                    && this.getY() + 10 >= enemyTank.getY()
                                    && this.getY() + 10 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //2.左下角的位置坐标[this.getX() -10, this.getY() + 50]
                            if (this.getX() -10 >= enemyTank.getX()
                                    && this.getX() -10 <= enemyTank.getX() + 40
                                    && this.getY() + 50 >= enemyTank.getY()
                                    && this.getY() + 50 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }
                        //敌人坦克是左/右
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 2) {
                            //
                            if (this.getX() -10 >= enemyTank.getX() - 10
                                    && this.getX() -10 <= enemyTank.getX() + 50
                                    && this.getY() + 10 >= enemyTank.getY() + 10
                                    && this.getY() + 10 <= enemyTank.getY() + 50) {
                                return true;
                            }
                            //2.左下角的位置坐标[this.getX() -10, this.getY() + 50]
                            if (this.getX() -10 >= enemyTank.getX() - 10
                                    && this.getX() -10 <= enemyTank.getX() + 50
                                    && this.getY() + 50 >= enemyTank.getY() + 10
                                    && this.getY() + 50 <= enemyTank.getY() + 50) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            //判断if shots size = 0 ,创建一颗子弹放入
            if (isLive && shots.isEmpty()) { //==0
                Shot shot = null;
                //判断坦克方向并启动
                switch (getDirection()) {
                    case 0:
                        shot = new Shot(getX() + 20, getY() + 20, 0);
                        break;
                    case 1:
                        shot = new Shot(getX() + 30, getY() + 30, 1);
                        break;
                    case 2:
                        shot = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        shot = new Shot(getX() - 10, getY() + 30, 3);
                        break;

                }
                shots.add(shot);
                //启动
                new Thread(shot).start();
            }


            //根据坦克方向来继续移动
            switch (getDirection()) {
                case 0:
                    //让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0 && !isTouchEnemyTank()) {
                            moveUp();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750 && !isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0 && !isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //随机改变方向0-3,random0-4小数4取不到，取整是3
            setDirection((int) (Math.random() * 4));
            //写并发程序，一定要考虑清楚，线程什么时候结束，什么时候销毁，线程的优先级，线程的礼让
            if (!isLive) {
                break;
            }
        }
    }
}
