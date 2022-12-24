package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.Message;
import entities.Order;
import entities.Product;
import entities.Subscription;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class Database {
	@PersistenceContext(unitName = "JPADefaultDataBaseConnection")
	private EntityManager em;

	private static ArrayList<Order> orders = new ArrayList<Order>();
	private static ArrayList<Product> products = initProducts();
	private static ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();

	private static ArrayList<Product> initProducts() {
		ArrayList<Product> products = new ArrayList<Product>();

		products.add(new Product(0, "Марио", 1.2, "mario_keychain.png"));
		products.add(new Product(1, "Луиджи", 1.3, "luigi_keychain.png"));
		products.add(new Product(2, "Гъбата", 1.5, "mushroom_keychain.png"));

		return products;
	}

	public void addMessage(Message msg) {
		em.persist(msg);
	}

	public static ArrayList<Order> getOrders() {
		return orders;
	}

	public static void addOrder(Order order) {
		order.setId(orders.size());
		orders.add(order);
	}

	public static ArrayList<Product> getProducts() {
		return products;
	}

	public static ArrayList<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public static void addSubscription(Subscription subscription) {
		subscriptions.add(subscription);
	}

	public static List<String> getSubscribtionCategories() {
		return Arrays.asList("Новини", "Продукти");
	}

	public static List<String> getSubscribtionSubCategories(String subscribtionCategories) {
		if (subscribtionCategories.equals(getSubscribtionCategories().get(0)))
			return Arrays.asList("Новини от последните 24 ч", "Новини от последните 48 часа");
		else
			return Arrays.asList("Нови продукти", "Промоции на продукти");
	}

//Връща всички съобщения по зададен имейл на изпращача
	public List<Message> getMessagesFilteredByEmail(String filterText) {
		TypedQuery<Message> q = em.createQuery("Select m from Message m WHERE m.email =:emailFilter", Message.class);
		q.setParameter("emailFilter", filterText);

		List<Message> result = q.getResultList();
		return result;
	}

//Връща всички съобщения по зададено име на изпращача
	public List<Message> getMessagesFilteredByName(String filterText) {
		TypedQuery<Message> q = em.createQuery("Select m from Message m WHERE m.senderName=:nameFilter", Message.class);
		q.setParameter("nameFilter", filterText);

		List<Message> result = q.getResultList();
		return result;
	}

//Връща всички съобщения съдържащи определен 
//текст в имейла на изпращача
	public List<Message> getMessagesFilteredByEmailWithLike(String filterText) {
		TypedQuery<Message> q = em.createQuery("Select m from Message m WHERE m.email LIKE :emailFilter",
				Message.class);
		q.setParameter("emailFilter", "%" + filterText + "%");

		List<Message> result = q.getResultList();
		return result;
	}

//Връща всички съобщения съдържащи определен 
//текст в името на изпращача
	public List<Message> getMessagesFilteredByNameWithLike(String filterText) {

		TypedQuery<Message> q = em.createQuery("Select m from Message m WHERE m.senderName LIKE :nameFilter",
				Message.class);
		q.setParameter("nameFilter", "%" + filterText + "%");

		List<Message> result = q.getResultList();
		return result;
	}

//Всички съобщения
	public List<Message> getAllMessages() {
		TypedQuery<Message> q = em.createQuery("Select m from Message m", Message.class);

		List<Message> result = q.getResultList();
		return result;
	}

//Изтрива съобщение по зададено id
	public void deleteMessage(int id) {
		Message m = em.find(Message.class, id);
		em.remove(m);
	}

	public Message getMessage(int id) {

		Message m = em.find(Message.class, id);

		return m;
	}

	public void editFlight(int id, Message msg) {
		
		msg.setId(id);
		em.merge(msg);
		
		//		Message m = em.find(Message.class, id);
		//		
		//		m.setDate( msg.getDate() );
		//		m.setEmail( msg.getEmail());
		//		m.setSenderName(msg.getSenderName());
		//		m.setPrice(msg.getPrice());
		//		m.setMessage(msg.getMessage());
		//		//.....
		
	}

}
