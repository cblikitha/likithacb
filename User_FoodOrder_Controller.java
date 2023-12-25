package org.jsp.foodorder_user.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.foodorder_user.dao.FoodOrderDao;
import org.jsp.foodorder_user.dao.UserDao;
import org.jsp.foodorder_user.dto.FoodOrder;
import org.jsp.foodorder_user.dto.User;

public class User_FoodOrder_Controller {
	static Scanner sc = new Scanner(System.in);
	static UserDao u_dao = new UserDao();
	static FoodOrderDao f_dao = new FoodOrderDao();
	
	public static void main(String[] args) {
		System.out.println("1.save User");
		System.out.println("2.Update User");
		System.out.println("3.Find User by Id");
		System.out.println("4.Verify User by Phone and Passowrd");
		System.out.println("5.Verify User by Email and Password");
		System.out.println("6.Delete User");
		System.out.println("7.Add Food Order");
		System.out.println("8.update Food Order");
		System.out.println("9.Find Food Order By User Id");
		System.out.println("10.cancel the order");
		
		System.out.println("Enter your Option");
		switch(sc.nextInt()) {
		case 1:
			saveUser();
			break;
		case 2:
			updateUser();
			break;
		case 3:
			findUserById();
			break;
		case 4:
			findUserByPhAndPswd();
			break;
		case 5:
			findUserByEmailAndPswd();
			break;
		case 6:
			delUser();
			break;
		case 7:
			addFoodOrder();
			break;
		case 8:
			updateFoodOrder();
			break;
		case 9:
			findFoodByUserId();
			break;
		case 10:
			delFoodOrder();
			break;
		}
	}
	
	public static void saveUser() {
		System.out.println("Enter the name,phone,email,password to save the user");
		User u = new User();
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = u_dao.saveUser(u);
		System.out.println("User saved with id:"+u.getId());
	}
	
	public static void updateUser() {
		System.out.println("Enter the user Id to update user");
		int id = sc.nextInt();
		System.out.println("Enter the name,phone,email,password to save the user");
		User u = new User();
		u.setId(id);
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = u_dao.updateUser(u);
		System.out.println("User updated with id:"+u.getId());
	}
	
	public static void findUserById() {
		System.out.println("Enter user id to find the details of user");
		int id = sc.nextInt();
		User u = new User();
		u = u_dao.findUserById(id);
		if(u!=null) {
			System.out.println("Id:"+u.getId());
			System.out.println("Name:"+u.getName());
			System.out.println("Phone:"+u.getPhone());
			System.out.println("Email:"+u.getEmail());
			System.out.println("Password:"+u.getPassword());
		} else {
			System.out.println("You have entered an invalid id");
		}
	}
	
	public static void findUserByPhAndPswd() {
		System.out.println("Enter Phone number and Password to find the user details");
		long phone = sc.nextLong();
		String password = sc.next();
		User u = u_dao.findUserByPhnAndPswd(phone, password);
		if(u!=null) {
			System.out.println("Id:"+u.getId());
			System.out.println("Name:"+u.getName());
			System.out.println("Phone:"+u.getPhone());
			System.out.println("Email:"+u.getEmail());
			System.out.println("Password:"+u.getPassword());
		} else {
			System.out.println("You have entered an invalid phone number or password");
		}
	}
	
	public static void findUserByEmailAndPswd() {
		System.out.println("Enter Email and Password to find the user details");
		String email = sc.next();
		String password = sc.next();
		User u = u_dao.findUserByEmailAndPswd(email, password);
		if(u!=null) {
			System.out.println("Id:"+u.getId());
			System.out.println("Name:"+u.getName());
			System.out.println("Phone:"+u.getPhone());
			System.out.println("Email:"+u.getEmail());
			System.out.println("Password:"+u.getPassword());
		} else {
			System.out.println("You have entered an invalid Email or password");
		}
	}
	
	public static void addFoodOrder() {
		System.out.println("Enter the user id to order the food");
		int id = sc.nextInt();
		System.out.println("Enter the food item and cost to add food order");
		FoodOrder order = new FoodOrder();
		order.setFood_item(sc.next());
		order.setCost(sc.nextDouble());
		order = f_dao.addFoodOrder(order, id);
		if(order!=null) {
			System.out.println("Food has been ordered with id:"+order.getId());
		} else {
			System.out.println("Food has not been ordered because user id is not valid");
		}
	}
	
	public static void updateFoodOrder() {
		System.out.println("Enter the user id and order id to order the food");
		int user_id = sc.nextInt();
		int order_id = sc.nextInt();
		System.out.println("Enter the id,food item and cost to add food order");
		FoodOrder order = new FoodOrder();
		order.setId(order_id);
		order.setFood_item(sc.next());
		order.setCost(sc.nextDouble());
		order = f_dao.updateFoodOrder(order, user_id,order_id);
		if(order!=null) {
			System.out.println("order has been updated with id:"+order.getId());
		} else {
			System.out.println("Invalid User id ...cannot ordered");
		}
	}
	
	public static void delUser() {
		System.out.println("Enter user id to delete the user");
		int id = sc.nextInt();
		boolean del = u_dao.deleteUser(id);
		if(del) {
			System.out.println("User with Id "+id+" has been deleted");
		} else {
			System.out.println("You have entered an invalid id");
		}
	}
	
	public static void findFoodByUserId() {
		System.out.println("Enter User Id to display food orders");
		int id = sc.nextInt();
		List<FoodOrder> orders = f_dao.findFoodByUserId(id);
		if(orders.size()>0) {
			for(FoodOrder order : orders) {
				System.out.println("Food Order Id:"+order.getId());
				System.out.println("Food Item:"+order.getFood_item());
				System.out.println("Cost:"+order.getCost());
				System.out.println("Ordered Time:"+order.getOrdered_time());
				System.out.println("Delivery Time:"+order.getDelivery_time());
				System.out.println("--------------------");
			}
		} else {
			System.err.println("There are no orders with given user id");
		}
	}
	
	public static void delFoodOrder() {
		System.out.println("Enter the order id to delete food order");
		int order_id = sc.nextInt();
		boolean order = f_dao.cancelFoodOrder(order_id);
		if(order) {
			System.out.println("order has been deleted");
		} else {
			System.err.println("Order Id is incorrect..so order has not been canceled");
		}
	}
}
