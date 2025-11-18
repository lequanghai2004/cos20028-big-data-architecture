package utils;

import java.util.List;

public class Order{
	String orderId;
	List<String> food;
	List<String> drink;
	String time;
	
	public Order(String orderId, List<String> food, List<String> drink, String time){
		this.orderId = orderId;
		this.drink = drink;
		this.food = food;
		this.time = time;
	}
	
	public List<String> getFood(){
		return this.food;
	}
	
	public List<String> getDrink(){
		return this.drink;
	}
	
	public String getOrderId(){
		return this.orderId;
	}
	
	@Override
	public String toString(){
		return "Order{" + 
				"orderId='" + orderId + '\'' + 
				", food=" + food +
				", drink=" + drink +
				", time='" + time + '\'' +
				'}';
	}
}