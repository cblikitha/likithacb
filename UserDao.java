package org.jsp.foodorder_user.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.foodorder_user.dto.User;

public class UserDao {
	EntityManager manager = Persistence.createEntityManagerFactory("jpa").createEntityManager();

	public User saveUser(User u) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(u);
		transaction.begin();
		transaction.commit();
		return u;
	}

	public User updateUser(User u) {
		EntityTransaction transaction = manager.getTransaction();
			manager.merge(u);
			transaction.begin();
			transaction.commit();
			return u;
	}

	public User findUserByPhnAndPswd(long phone, String password) {
		String qry = "select u from User u where u.phone=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User findUserById(int id) {
		String qry = "select u from User u where u.id=?1";
		Query q = manager.createQuery(qry);
		q.setParameter(1, id);
		// User u = manager.find(User.class, id);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public User findUserByEmailAndPswd(String email, String password) {
		String qry = "select u from User u where u.email=?1 and u.password=?2";
		Query q = manager.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public boolean deleteUser(int user_id) {
		User u = manager.find(User.class, user_id);
		if (u != null) {
			manager.remove(u);
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}
}
