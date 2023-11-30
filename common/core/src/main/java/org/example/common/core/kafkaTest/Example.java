package org.example.common.core.kafkaTest;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/16 16:28
 */
public class Example {
//    public static void main(String[] args) {
//        String bootstrapServers = "your-bootstrap-servers";
//        String groupId = "your-consumer-group-id";
//        String topic = "your-topic";
//
//        Properties properties = new Properties();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//
//        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
//
//        // 订阅主题
//        consumer.subscribe(Arrays.asList(topic));
//
//        try {
//            while (true) {
//                // 拉取消息，超时时间为100毫秒
//                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//
//                // 处理从所有分区拉取到的消息
//                records.forEach(record -> {
//                    System.out.printf("Consumer Record: key = %s, value = %s, partition = %d, offset = %d%n",
//                            record.key(), record.value(), record.partition(), record.offset());
//                });
//            }
//        } finally {
//            consumer.close();
//        }
//    }
}
