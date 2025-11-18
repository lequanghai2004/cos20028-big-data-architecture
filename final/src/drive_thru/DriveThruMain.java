package drive_thru;

import utils.OrderGenerator;
import utils.Order;
import static utils.Constants.*;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

public class DriveThruMain {
	
	public static void main(String[] args){
		// Create a random order generator
		Random random = new Random();
		OrderGenerator dt_og = new OrderGenerator("DT", DT_SEED);
		
		// Create Kafka producer
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
		
		try{
			for (int i = 0; i < NUM_ORDERS; i++){
				// Wait ...
				try{
					Thread.sleep((random.nextInt(6) + 10) * 1000);
				} catch(InterruptedException e){
					e.printStackTrace();
				}
				
				Order order = dt_og.genAnOrder();
				System.out.println("Drive Thru is sending orders ...");
				
				// Send order to FOOD
				if (order.getFood() != null && !order.getFood().isEmpty()){
					System.out.println(order.getOrderId());
					System.out.println(order.getFood().toString() + " to " + FOOD_TOPIC);
					producer.send(new ProducerRecord<String, String>(FOOD_TOPIC, order.getOrderId(), order.getFood().toString()));					
				}
				// Send order to DRINK
				if (order.getDrink() != null && !order.getDrink().isEmpty()){
					System.out.println(order.getOrderId());
					System.out.println(order.getDrink().toString() + " to " + DRINK_TOPIC);
					producer.send(new ProducerRecord<String, String>(DRINK_TOPIC, order.getOrderId(), order.getDrink().toString()));					
				}
			}
		}
		finally{
			producer.close();
		}
	}
}
