package com.juanxincai.tankgame;

public class Bomb {
    int x, y;//炸弹坐标
    int life = 12;//蛋蛋生命周期
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown() {//配合出现爆炸效果
        if (life > 0) {
            life--;
        } else {
            isLive = false;
        }

    }
}
