package com.juanxincai.tankgame;

public class Shot implements Runnable {
    int x;//子弹的x坐标
    int y;
    int direction = 0;
    int speed = 2;
    boolean islive = true;//子弹是否存活
    //构造器

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {//射击
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //根据方向来改变x,y坐标
            switch (direction) {
                case 0://向上
                    y -= speed;
                    break;
                case 1://向右
                    x += speed;
                    break;
                case 2://向下
                    y += speed;
                    break;
                case 3://向左
                    x -= speed;
                    break;
            }
            //输出一下测试
//            System.out.println("子弹 x =" + x + " y = " + y);
            //当子弹移动到面板的边界时，子弹结束
            //当子弹碰到敌人坦克时候也应该结束线程
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)|| !islive) {
                System.out.println("子弹已经结束");
                islive = false;
                break;
            }
        }

    }

}
