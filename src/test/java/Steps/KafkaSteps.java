package Steps;

import Kafka.Consumer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class KafkaSteps extends BaseSteps {
    private final Context context;
    Map<String, Consumer> consumers = new HashMap<>();

    public KafkaSteps(Context context) {
        this.context = context;
    }

    @And("^I subscribe to '(.*)' kafka topic$")
    public void subscribeToKafkaTopic(String topic) {
        Consumer consumer = new Consumer(topic);
        consumer.subscribeToKafkaTopic();
        context.consumer = consumer;

        consumers.put(topic, consumer);
        context.consumers = consumers;
        log.info("Consumer is saved into context for topic: " + topic);
    }

    @And("^I subscribe to the following kafka topics with separate consumers$")
    public void subscribeToKafkaTopics(DataTable table) {
        for (Map<String, String> row : basePage.getTableAsStringMaps(table)) {
            String topic = row.get("TOPIC");
            Consumer consumer = new Consumer(topic);
            consumer.subscribeToKafkaTopic();
            consumers.put(topic, consumer);
            context.consumers = consumers;
            log.info("Consumer is saved into context for topic: " + topic);
        }
    }
}
