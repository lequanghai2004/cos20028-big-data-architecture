package utils;
import utils.Order;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrderGenerator {
	private static final List<String> DRINK_NAMES = Arrays.asList(
			"Earl Grey Tea", "Earl Grey Tea Latte", 
			"Royal English British Tea", "Royal English British Tea Latte",
			"Matcha Tea Latte", "Cappuccino", "Flat White", "Latte", "Mocha", "Macchiato");
	
	private static final List<String> FOOD_NAMES = Arrays.asList(
			"Chicken Sandwich", "Bacon Sandwich", 
			"Sausage & Egg Wrap", "Spinach & Feta Wrap",
			"Ham Croissant", "Butter Croissant", "Chocolate Brownie");
	
	protected int orderId;
	protected String orderFrom;
	protected Random random;
	
	public OrderGenerator(String orderFrom, long randomState){
		this.orderId = 0;
		this.orderFrom = orderFrom;
		this.random = new Random(randomState);
	}
	
	public Order genAnOrder(){
		//throw new UnsupportedOperationException("This method should be implemented");
		int numDrinks = random.nextInt(5) + 1;
		int numFoods = random.nextInt(6);
		orderId += 1;
		List<String> food = randomChoices(FOOD_NAMES, numFoods);
		List<String> drink = randomChoices(DRINK_NAMES, numDrinks);
		
		return new Order(
				orderFrom + "_" + orderId,
				sortOrder(food),
				sortOrder(drink),
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
	}
	
	private List<String> randomChoices(List<String> items, int count){
		return IntStream.range(0, count)
				.mapToObj(i -> items.get(random.nextInt(items.size())))
				.collect(Collectors.toList());
	}
	
	private List<String> sortOrder(List<String> items){
		Map<String, Long> counts = items.stream()
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return counts.entrySet().stream()
				.map(e -> e.getKey() + "*" + e.getValue())
				.collect(Collectors.toList());
	}
	
	public static void main(String[] args){
		OrderGenerator og = new OrderGenerator("test", 123);
		System.out.println(og.genAnOrder());
	}
}	