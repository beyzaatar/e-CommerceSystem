import business.concretes.CustomerCheckManager;
import business.concretes.CustomerManager;
import business.concretes.EmailVerificationManager;
import dataAccess.concretes.HibernateCustomerDao;
import entities.concretes.Customer;

public class Main {

	public static void main(String[] args) {
		Customer customer1 = new Customer();
		customer1.setCustomerId(1);
		customer1.setFirstName("beyza");
		customer1.setLastName("atar");
		customer1.setEmail("beyzaatar50@gmail.com");
		customer1.setPassword("123456");
		
		Customer customer2 = new Customer();
		customer1.setCustomerId(2);
		customer1.setFirstName("aziz");
		customer1.setLastName("avcý");
		customer1.setEmail("azizavci@gmail.com");
		customer1.setPassword("789456");
		
		CustomerManager customerManager = new CustomerManager(new CustomerCheckManager(),new EmailVerificationManager(),new HibernateCustomerDao());
		
		customerManager.signUp(customer1);	
		customerManager.signUp(customer2);		

	}

}
