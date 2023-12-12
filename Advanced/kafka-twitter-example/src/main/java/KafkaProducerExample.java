import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) throws Exception {
        String topicName = "tweets";
        String bootstrapServers = "kafka-server:9092";

        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(properties);

        try (BufferedReader br = new BufferedReader(new FileReader("twcs.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                ProducerRecord<String, String> record = new ProducerRecord<>(topicName, line);
                producer.send(record);
            }
        }

        producer.close();
    }
}
