package com.demo.model;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Application {
	static SessionFactory sessionFactory = null;

	public static void main(String[] args) {
		setUpSessionFactory();
		saveProduct();
		//updateProduct();
		//deleteProduct();
	getProducts();
	}

	public static void setUpSessionFactory() {
		// Use the mappings and properties specified in an application resource
		// named hibernate.cfg.xml.
		Configuration configuration = new Configuration().configure();
		// A registry of services.
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		// The main contract here is the creation of Session instances.
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static void getProducts() {
		// The main runtime interface between a Java application and Hibernate.
		Session session = sessionFactory.openSession();
		//Allows the application to define units of work, while maintaining abstraction from the underlying transaction implementation
		Transaction transaction = session.beginTransaction();
		//Write the Query
		Query selectQuery = session.createQuery("from Product");
		//Get a List
		List<Product> products = (List<Product>)selectQuery.list();
		for(Product product: products) {
			System.out.println(product);
		}
		transaction.commit();
		
	}
	public static void saveProduct() {
		// The main runtime interface between a Java application and Hibernate.
		Session session = sessionFactory.openSession();
		//Allows the application to define units of work, while maintaining abstraction from the underlying transaction implementation
		Transaction transaction = session.beginTransaction();
		Product product = new Product();
//	product.setProductId("P01");
//	product.setProductName("THE PLACEBO EFFECT");
	product.setProductId("P05");
	product.setProductName("EAT ");
		session.save(product);
		transaction.commit();
		
	}
	public static void updateProduct() {
		// The main runtime interface between a Java application and Hibernate.
		Session session = sessionFactory.openSession();
		//Allows the application to define units of work, while maintaining abstraction from the underlying transaction implementation
		Transaction transaction = session.beginTransaction();
		Product product = (Product)session.get(Product.class, "P01");
		product.setProductName("WINGS OF FIRE");
		//session.save(product);
		transaction.commit();
		
	}
	public static void deleteProduct() {
		// The main runtime interface between a Java application and Hibernate.
		Session session = sessionFactory.openSession();
		//Allows the application to define units of work, while maintaining abstraction from the underlying transaction implementation
		Transaction transaction = session.beginTransaction();
		Product product = (Product)session.get(Product.class, "P01");
		product.setProductName("WINGS OF FIRE");
		session.delete(product);
		transaction.commit();
		
	}
}