package com.cas;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 7:43 下午
 * @desc
 */
public class CanalClient {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        //todo 获取连接
        CanalConnector canalConnector =
                CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1", 11111), "example", "", "");
        while (true) {
            canalConnector.connect();

            canalConnector.subscribe("cas_blog.*");

            Message message = canalConnector.get(100);

            List<CanalEntry.Entry> entries = message.getEntries();

            if (entries.isEmpty()) {
                try {
                    System.out.println("休息一会儿");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                for (CanalEntry.Entry entry : entries) {
                    // 1、获取表名
                    String tableName = entry.getHeader().getTableName();

                    // 2、获取类型
                    CanalEntry.EntryType entryType = entry.getEntryType();

                    // 3、获取序列化后的数据
                    ByteString storeValue = entry.getStoreValue();

                    // 4、当前entryType类型是否为RowData
                    if (CanalEntry.EntryType.ROWDATA.equals(entryType)) {
                        // 5、反序列化数据
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);

                        // 6、获取当前时间的操作类型
                        CanalEntry.EventType eventType = rowChange.getEventType();

                        // 7、获取数据集
                        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();

                        // 8、遍历rowDataList，并打印数据集
                        for (CanalEntry.RowData rowData : rowDatasList) {
                            JSONObject before = new JSONObject();
                            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                            for (CanalEntry.Column column : beforeColumnsList) {
                                before.put(column.getName(), column.getValue());
                            }

                            JSONObject after = new JSONObject();
                            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                            for (CanalEntry.Column column : afterColumnsList) {
                                after.put(column.getName(), column.getValue());
                            }

                            // 打印数据
                            System.out.println("Table: " + tableName +
                                    ", EventType: " + eventType +
                                    ", Before: " + before +
                                    ", After: " + after);
                        }

                    } else {
                        System.out.println("当前数据类型为: " + entryType);
                    }

                }
            }

        }


    }

}
