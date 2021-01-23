package Kafka;

import Utils.TestProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.json.JSONObject;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
public class Producer {

    private final KafkaProducer<Integer, String> producer;
    private final String topic;

    public Producer(String topic) {
        Properties props = new Properties();
        String BOOTSTRAP_SERVERS = TestProperties.getProperty("kafka.bootstrap.server");
        String KAFKA_SERVER_PORT = TestProperties.getProperty("kafka.bootstrap.port");
        String CLIENT_ID = TestProperties.getProperty("kafka.client.id");

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS + ":" + KAFKA_SERVER_PORT);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        this.topic = topic;
        producer = new KafkaProducer<>(props);
        log.debug("Topic is: " + this.topic);
    }

    public void sendMessage(JSONObject message) throws ExecutionException, InterruptedException {
        String messageString = message.toString();
        RecordMetadata metadata = producer.send(new ProducerRecord<>(topic, messageString)).get();
        log.info("Sent message is: " + metadata.toString());
    }

}
