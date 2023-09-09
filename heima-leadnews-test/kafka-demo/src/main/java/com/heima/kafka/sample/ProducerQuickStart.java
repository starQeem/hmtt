package com.heima.kafka.sample;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Date: ${DATE} ${TIME}
 * @author: Qeem
 * 生产者
 */
public class ProducerQuickStart {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.kafka连接配置信息
        Properties prop = new Properties();
        //kafka连接地址
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"180.110.202.50:9092"); //ctrl进入ProducerConfig源码里可以查看
        //key和value的序列化  (org.apache.kafka.common.serialization.StringSerializer在jar包里找)
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //2.创建kafka生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);
        //3.发送消息
        /*
        * 第一个参数: topic
        * 第二个参数: 分区
        * 第三个参数: 消息的key
        * 第四个参数: 消息的value
        * */
        for (int i = 0; i < 5; i++) {
            ProducerRecord<String, String> kvProducerRecord = new ProducerRecord<>("itcast-topic-input", "hello kafka");
            producer.send(kvProducerRecord);
        }
        //同步发送消息
//        RecordMetadata recordMetadata = producer.send(kvProducerRecord).get();
//        System.out.println(recordMetadata.offset());
        //异步消息发送
//        producer.send(kvProducerRecord, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                if (e != null){
//                    System.out.println("记录异常信息到日志表中");
//                }
//                System.out.println(recordMetadata.offset());
//            }
//        });
        //4.关闭消息通道  必须要关闭,否则消息发送不成功
        producer.close();
    }
}