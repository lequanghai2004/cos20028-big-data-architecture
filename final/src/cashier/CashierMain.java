package cashier;

import utils.OrderGenerator;
import utils.Order;
import static utils.Constants.*;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

public class CashierMain {
	
	public static void main(String[] args){
		// Create a random order generator
		Random random = new Random();
		OrderGenerator dt_og = new OrderGenerator("C", C_SEED);
		
		// Create Kafka producer
		/*
	     * TODO implement
	     */
		
		// publish to topic(s)
		/*
	     * TODO implement
	     */
	}
}
