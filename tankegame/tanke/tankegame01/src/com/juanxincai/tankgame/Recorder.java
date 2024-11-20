package com.juanxincai.tankgame;

import java.io.*;
import java.util.Set;
import java.util.Vector;

//用于相关信息和文件交互
public class Recorder {

    //定义变量记录我方击毁敌人坦克数量
    private static int allEnemyTankNum = 0;
    //定义IO对象用于写数据到文件
    private static FileWriter fw = null;
    //
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    //把记录文件保存到src下
//    private static String recordFile = "F:\\myRecord.txt";
    private static final String recordFile = "myRecord.txt";
    //定义vector，指向MyPanel对象的敌人坦克的vector
    private static Vector<Enemy> enemyTanks = null;
    //定义一个Node 的Vector ，用于包存敌人坦克的信息
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<Enemy> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //返回记录文件的路径
    public static String getRecordFile() {
        return recordFile;
    }


    //增加一个方法用于读取recordFile文件，将文件中的数据恢复到游戏
    //该方法在启动上局游戏启动
    public static Vector<Node> getNodesAndEnemyTankNum() {

        try {

            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //循环读取文件，生成nodes 集合
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]),
                        Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                nodes.add(node);//放入vector
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
//            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();//关闭流
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return nodes;
    }


//增加一个方法当游戏退出时候将allEnemyTankNum写入到文件中
    //对keepRecord 进行升级，保存敌人坦克的坐标和方向

    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
//            bw.newLine();换行
            //遍历敌人坦克的vector，然后根据情况保存即可
            //oop，定义一个属性通过set方法得到敌人的vector
            for (int i = 0; i< enemyTanks.size();i++) {
                Enemy enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive)  {//建议判断坦克是否还活着
                    //说明该坦克还活着，将xy和方向保存到文件
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirection();
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();//关闭流
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克击毁一个敌人坦克，就应当将击毁的敌人坦克数量加一，allEnemyTankNum++
    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
