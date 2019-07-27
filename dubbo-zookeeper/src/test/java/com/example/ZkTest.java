package com.example;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class ZkTest{

    String url = "47.98.113.136:2181";

    ZooKeeper zooKeeper;

    @Before
    public void setUp() throws Exception {

        zooKeeper = new ZooKeeper(url, 10000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                String path = watchedEvent.getPath();
                Event.EventType type = watchedEvent.getType();
                Event.KeeperState state = watchedEvent.getState();
                System.out.println("路径：" + path + " ,状态：" + state + " ,事件类型：" + type);
            }
        });
    }

    @Test
    public void testCreateNode(){
        try {
            zooKeeper.create("/zk-path","helloworld".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.create("/zk-path/m1","helloworld1".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.create("/zk-path/m2","helloworld2".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testDeleteNode(){
        try {
            //-1表示不用考虑版本
            zooKeeper.delete("/zk-path/m1",-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateNode(){
        try {
            zooKeeper.setData("/zk-path/m2","我是架构师".getBytes(),-1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGetData(){
        try {
            try {
                for(int i = 0;i<10;i++){
                    Thread.sleep(1000);
                    byte[] data = zooKeeper.getData("/zk-path/m2", true, null);
                    String ret = new String(data,"UTF-8");
                    System.out.println("getData = " + ret);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
