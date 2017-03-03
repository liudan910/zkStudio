/**
 * Created by liudan19 on 2017/2/23.
 */

import org.apache.zookeeper.*;

import static java.lang.System.out;

public class TestZKClient {
    private static String CLIENT_PORT = "2180";

    public static void main(String[] args) {
        try {
            ZooKeeper zk = new ZooKeeper("localhost:" + CLIENT_PORT, 5000, new Watcher() {
                public void process(WatchedEvent event) {
                    out.println("已经触发了" + event.getType() + "事件");
                }
            });

/*

           zk.create("/test", "testData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zk.create("/test/child1", "child1Data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zk.create("/test/child2", "child2Data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
*/


            zk.setData("/test/child1", "modifyChildDataOne".getBytes(), -1);

            out.println(new String(zk.getData("/test", false, null)));
            out.println(zk.getChildren("/test", true));
            System.out.println("目录节点状态：[" + zk.exists("/test", true) + "]");

           out.println(new String(zk.getData("/test/child2", true, null)));

        /*    zk.delete("/test/child1", -1);
            zk.delete("/test/child2", -1);
            zk.delete("/test", -1);*/
            zk.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
