package Kafka;

import Utils.TestDataReader;
import Utils.TestProperties;
import kafka.utils.ShutdownableThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.json.JSONObject;

import java.time.Duration;
import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Slf4j
public class Consumer extends ShutdownableThread {

    private final static String BOOTSTRAP_SERVERS = TestProperties.getProperty("kafka.bootstrap.server");
    private final static String KAFKA_SERVER_PORT = TestProperties.getProperty("kafka.bootstrap.port");
    private final static String GROUP_ID = "kafka-service-messages";
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;

    public Consumer(String topic) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS + ":" + KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID + UUID.randomUUID());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        this.topic = TestDataReader.getDataFromFile(topic);
        log.debug("Created consumer with topic " + this.topic);
    }

    public void subscribeToKafkaTopic() {
        consumer.subscribe(Collections.singleton(topic));
        consumer.poll(Duration.ofSeconds(1));
        log.info("Subscribed to topic: " + topic);
    }

    public List<JSONObject> getMessage() {
        log.debug("Offset before poll: " + consumer.endOffsets(getTopicPartitions(), Duration.ofSeconds(10)));
        ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(10));

        log.debug("Messages received: " + records.count());
        log.debug("Offset after poll: " + consumer.endOffsets(getTopicPartitions(), Duration.ofSeconds(10)));

        return StreamSupport.stream(records.spliterator(), false)
                .map(r -> new JSONObject(r.value()))
                .collect(toList());
    }

    private List<TopicPartition> getTopicPartitions() {
        List<PartitionInfo> partitionInfos = consumer.listTopics().entrySet().stream()
                .filter(e -> e.getKey().equals(topic))
                .findAny()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalStateException("Value is not found"));

        return partitionInfos.stream()
                .map(PartitionInfo::partition)
                .map(partitionNumber -> new TopicPartition(topic, partitionNumber))
                .collect(toList());
    }

    @Override
    public void doWork() {

    }
}
