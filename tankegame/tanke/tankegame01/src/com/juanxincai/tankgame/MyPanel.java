package com.juanxincai.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//为了让panel不停的重绘子弹，需要将MyPanel实现Runnable接口，当作一个线程的使用
public class MyPanel extends JPanel implements KeyListener, Runnable {

    Hero hero = null;
    //    Enemy enemy = null;
    Vector<Enemy> enemyTanks = new Vector<>();
    //定义一个存放node对象的Vector用于恢复敌人坦克坐标和方向
    Vector<Node> nodes = new Vector<>();
    //定义一个vector,用于存放蛋蛋
    //当子弹击中坦克时就加入一个蛋蛋对象到bombs中
    Vector<Bomb> bombs = new Vector<>();
    int enemyTanksSize = 3;
    //定义三张炸弹的图片，用于显示爆炸效果
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;


    public MyPanel(String key) {
        //读文件之前需要判断记录文件是否存在
        //如果存在就正常执行，如果不存在，提示只能开新游戏，key ==“1”
        File file = new File(Recorder.getRecordFile());
        if (file.exists()) {
            nodes =Recorder.getNodesAndEnemyTankNum();
        } else {
            System.out.println("无存档，请开启新游戏");
            key = "1";
        }

        //直接调文件，文件没有会抛出异常
//        nodes = Recorder.getNodesAndEnemyTankNum();
        //姜MyPanel对象的enemyTanks 设置给Recorder 的enemyTanks
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(500, 100);//初始化位置
        hero.setSpeed(10);

        switch (key) {
            case "1":
                for (int i = 0; i < enemyTanksSize; i++) {
//            enemyTanks.add(new Enemy(100 * (i + 1), 0));
                    Enemy enemyTank = new Enemy(100 * (i + 1), 0);
                    //将enemyTanks设置给enemyTank对象
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirection(2);
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给该enemyTank 加入一个子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    //加入到enemy的Vector成员
                    enemyTank.shots.add(shot);
                    //启动线程
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2": //继续上局游戏
                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
//            enemyTanks.add(new Enemy(100 * (i + 1), 0));
                    Enemy enemyTank = new Enemy(node.getX(), node.getY());
                    //将enemyTanks设置给enemyTank对象
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirection(node.getDirection());
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //给该enemyTank 加入一个子弹
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
                    //加入到enemy的Vector成员
                    enemyTank.shots.add(shot);
                    //启动线程
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("输入有误");
        }
        //初始化敌人坦克
//        for (int i = 0; i < enemyTanksSize; i++) {
////            enemyTanks.add(new Enemy(100 * (i + 1), 0));
//            Enemy enemyTank = new Enemy(100 * (i + 1), 0);
//            //将enemyTanks设置给enemyTank对象
//            enemyTank.setEnemyTanks(enemyTanks);
//            enemyTank.setDirection(2);
//            //启动敌人坦克线程
//            new Thread(enemyTank).start();
//            //给该enemyTank 加入一个子弹
//            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
//            //加入到enemy的Vector成员
//            enemyTank.shots.add(shot);
//            //启动线程
//            new Thread(shot).start();
//            enemyTanks.add(enemyTank);
//        }
//        enemy =new Enemy(100,100);
        //初始化图片对象
        img1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        img3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
        //播放我的音乐
//        new AePlayWave("src\\111.wav").start();
    }
    //编写方法显示我放击毁敌方坦克信息
    public void showInfo(Graphics g) {
        g.setColor(Color.black);
        Font font = new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("您累计击毁敌方坦克数量：",1020,30);
        //画出一个敌方坦克
        drawTank(1020,60,g,0,0);
        //因为画坦克画笔更改颜色了，所以要重置
        g.setColor(Color.black);
//        Recorder.getAllEnemyTankNum()是int转换成字符串
        g.drawString(Recorder.getAllEnemyTankNum() + "",1080,100);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        showInfo(g);

        //画出自己的坦克
        if (hero != null && hero.isLive){
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);
        }
        //画出hero射击的子弹
//        if (hero.shot != null && hero.shot.islive) {
//            System.out.println("子弹被绘制");
//            //g.fill3DRect(hero.shot.x,hero.shot.y,1,1,false);
//            g.draw3DRect(hero.shot.x, hero.shot.y, 1, 1, false);
//        }
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.islive) {
//                System.out.println("子弹被绘制");
//                g.fill3DRect(shot.x,shot.y,1,1,false);
                g.draw3DRect(shot.x, shot.y, 1, 1, false);
            } else {//如果shot无效，就从shots集合中删除
                hero.shots.remove(i);
            }
        }

//       drawTank(enemy.getX() + 120, enemy.getY() + 120, g, enemy.getDirection(), 0);

        //如果bombs集合中有对象就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            //根据life取出炸弹
            if (bomb.life > 12) {
                g.drawImage(img1, bomb.x, bomb.y, 60, 60, this);

            } else if (bomb.life > 9) {
                g.drawImage(img2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(img3, bomb.x, bomb.y, 60, 60, this);
            }
            //炸弹生命值减少
            bomb.lifeDown();
            //如果bombs的 life 为0，就从bombs的集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }

        //画出敌人的坦克，遍历vector
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克遍历Vector
            Enemy enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
                //画出 enemy tank 所有子弹

                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.islive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);

                    } else {
                        //从Vector移除
                        enemyTank.shots.remove(shot);
                    }
                }
            }

        }
    }


    public void drawTank(int x, int y, Graphics g, int direction, int type) {

        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        switch (direction) {

            case 0:
                g.fill3DRect(x, y, 10, 60, false);//坦克左侧
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右侧
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克身体
                g.fillOval(x + 10, y + 20, 20, 20);//坦克圆形盖子
                g.drawLine(x + 20, y + 20, x + 20, y);//坦克炮筒
                break;

            case 1:
                g.fill3DRect(x - 10, y + 10, 60, 10, false);//
                g.fill3DRect(x - 10, y + 40, 60, 10, false);//
                g.fill3DRect(x, y + 20, 40, 20, false);//
                g.fillOval(x + 10, y + 20, 20, 20);//
                g.drawLine(x + 30, y + 30, x + 50, y + 30);
                break;

            case 2:
                g.fill3DRect(x, y, 10, 60, false);//
                g.fill3DRect(x + 30, y, 10, 60, false);//
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 60, x + 20, y + 40);
                break;

            case 3:
                g.fill3DRect(x - 10, y + 10, 60, 10, false);
                g.fill3DRect(x - 10, y + 40, 60, 10, false);
                g.fill3DRect(x, y + 20, 40, 20, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x - 10, y + 30, x + 10, y + 30);
                break;


            default:
                System.out.println("暂时没有处理");
        }
    }

    //编写方法判断我方子弹是否击中敌人坦克
    //如果发射多个子弹，判断时候应该取出所以子弹和所以坦克判断

    public void hitEnemyTank() {

        //遍历我们的子弹
        for (int j = 0; j < hero.shots.size(); j++) {
            Shot shot = hero.shots.get(j);
            if (shot != null && shot.islive) {//当前子弹存活
                for (int i = 0; i < enemyTanks.size(); i++) {
                    Enemy enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);//传入shot，而不是hero.shot
//                    System.out.println("攻击坦克");
                }
            }
        }
    }

    //编写方法判断敌人坦克是否击中我的坦克
    public void  hitHero() {
        //遍历所以的敌人坦克
        for (int i = 0 ; i<enemyTanks.size();i++) {
            //取出坦克
            Enemy enemyTank = enemyTanks.get(i);
            //遍历此坦克所有子弹
            for (int j = 0; j < enemyTank.shots.size();j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断shot是否击中坦克
                if (hero.isLive && shot.islive) {
                    hitTank(shot, hero);
                }

            }
        }
    }

    //判断是否击中了敌人坦克
    public void hitTank(Shot s, Tank tank) {
        //判断坦克是否击中坦克
        switch (tank.getDirection()) {
            case 0://向上
            case 2://向下
                if (s.x > tank.getX() && s.x < tank.getX() + 40
                        && s.y > tank.getY() && s.y < tank.getY() + 60) {
                    s.islive = false;
                    tank.isLive = false;//并没有从vector拿出
                    //如果是enemyTank，就从enemyTanks中删除
                    if (tank instanceof Enemy) {
                        enemyTanks.remove(tank);
                        //当我方击毁一个敌人坦克对allEnemyTankNum++
                        Recorder.addAllEnemyTankNum();

                    }
//                    enemyTanks.remove(tank);//从vector中拿走
                    System.out.println("成功击中555555555555");
                    //创建一个bomb对象加入到bombs集合
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);

                }
//                System.out.println("未击中");
                break;
            case 1:
            case 3:
                if (s.x > tank.getX() - 10 && s.x < tank.getX() + 50
                        && s.y > tank.getY() + 10 && s.y < tank.getY() + 50) {
                    s.islive = false;
                    tank.isLive = false;
                    if (tank instanceof Enemy) {
                        enemyTanks.remove(tank);
                        Recorder.addAllEnemyTankNum();
                    }
//                    enemyTanks.remove(tank);
                    System.out.println("成功击中555555555555");
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
//                System.out.println("未击中");
                break;
            default:
                System.out.println("未击中");
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {

            hero.setDirection(0);
            if (hero.getY() > 0) {
                hero.moveUp();
            }
//            hero.setY(hero.getY() - 1);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirection(1);
            if (hero.getX() + 60 < 1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirection(2);
            if (hero.getY() + 60 < 750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirection(3);
            if (hero.getX() > 0) {
                hero.moveLeft();
            }
        }
        //如果用户按下的是J，就需要发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //判断hero的子弹是否销毁，发射一颗子弹的情况
//            if (hero.shot == null || !hero.shot.islive) { //结束以后shot线程消亡但是对象没空，这里是判断对象是否为空
//                hero.shotEnemyTank();
//            }
            //发射多课
            hero.shotEnemyTank();
//            System.out.println("用户按下了J，开始射击");

        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    //shot那里重绘可以吗？
    public void run() {//每隔10毫秒重绘，刷新绘图区
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断是否击中了敌人坦克单颗子弹
//            if (hero.shot!=null && hero.shot.islive) {//当前子弹存活
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    Enemy enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot, enemyTank);
////                    System.out.println("攻击坦克");
//                }
//
//            }
            hitHero();
            hitEnemyTank();
            this.repaint();
        }

    }
}
