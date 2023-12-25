package org.jsp.foodorder_user.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.foodorder_user.dto.FoodOrder;
import org.jsp.foodorder_user.dto.User;

public class FoodOrderDao {
	static EntityManager manager = Persistence.createEntityManagerFactory("jpa").createEntityManager();
	
	public FoodOrder addFoodOrder(FoodOrder foodorder,int user_id) {
		User u = manager.find(User.class, user_id);
		if(u!=null) {
			List<FoodOrder> orders = u.getOrder();
			orders.add(foodorder);
			u.setOrder(orders);
			foodorder.setUser(u);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(foodorder);
			transaction.begin();
			transaction.commit();
			return foodorder;
		} else {
			return null;
		}
	}
	
	public FoodOrder updateFoodOrder(FoodOrder order,int user_id,int order_id) {
		User u = manager.find(User.class, user_id);
		if(u!=null) {
			FoodOrder temp = manager.find(FoodOrder.class, order.getId());
			temp.setFood_item(order.getFood_item());
			temp.setCost(order.getCost());
			EntityTransaction transaction = manager.getTransaction();
			manager.merge(temp);
			transaction.begin();
			transaction.commit();
			return order;
		} else {
			return null;
		}
	}

	public  List<FoodOrder> findFoodByUserId(int id) {
		String qry = "select o from FoodOrder o where o.user.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, id);
		return q.getResultList();
	}
	
	public  boolean cancelFoodOrder(int order_id) {
		FoodOrder or = manager.find(FoodOrder.class, order_id);
		if(or!=null) {
			manager.remove(or);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}
}
