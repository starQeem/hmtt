package com.heima.kafka.sample;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * @Date: 2023/7/23 21:46
 * @author: Qeem
 * 消费者
 */
public class ConsumerQuickStart {
    public static void main(String[] args) {
        //1.添加kafka的配置信息
        Properties prop = new Properties();
        //kafka的连接地址
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "180.110.202.50:9092");
        //消费者组
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
        //手动提交偏移量
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        //消息的反序列化器
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        //2.消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);

        //3.订阅主题
        consumer.subscribe(Collections.singletonList("itcast-topic-out"));

        //同步和异步提交偏移量(同步异步一起使用)
        try {
            while (true) {
                //4.获取消息
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(consumerRecord.key());
                    System.out.println(consumerRecord.value());
                    System.out.println(consumerRecord.offset()); //偏移量
                    System.out.println(consumerRecord.partition());//分区
                }
                //异步提交偏移量
                consumer.commitAsync();
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("记录错误的信息:" + e);
        }finally {
            //同步提交偏移量
            consumer.commitSync();
        }



        //当前线程一直处于监听状态
//        while (true) {
//            //4.获取消息
//            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
//            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
//                System.out.println(consumerRecord.key());
//                System.out.println(consumerRecord.value());
//                System.out.println(consumerRecord.offset()); //偏移量
//                System.out.println(consumerRecord.partition());//分区
//                try{
//                    //同步提交偏移量
//                    consumer.commitSync();
//                }catch (CommitFailedException e){
//                    System.out.println("记录提交失败的异常:" + e);
//                }
                //异步提交的偏移量
//                consumer.commitAsync(new OffsetCommitCallback() {
//                    @Override
//                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
//                        if (e != null){
//                            System.out.println("记录错误的提交偏移量" + map + ",异常信息为:" + e);
//                        }
//                    }
//                });

//            }
//        }

    }
}
