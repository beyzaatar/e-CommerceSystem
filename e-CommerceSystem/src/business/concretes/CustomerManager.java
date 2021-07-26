package business.concretes;

import business.abstracts.CustomerCheckService;
import business.abstracts.CustomerService;
import business.abstracts.EmailVerificationService;
import dataAccess.abstracts.CustomerDao;
import entities.concretes.Customer;

public class CustomerManager implements CustomerService {

	CustomerCheckService customerCheckService;
	EmailVerificationService emailVerificationService;
	CustomerDao customerDao;

	public CustomerManager(
			CustomerCheckService customerCheckService, 
			EmailVerificationService emailVerificationService,
			CustomerDao customerDao) {
		this.customerCheckService = customerCheckService;
		this.emailVerificationService = emailVerificationService;
		this.customerDao = customerDao;
	}
	
	@Override
	public void signUp(Customer customer) {
		if (customerCheckService.isValid(customer) == true) {
			System.out.print(customer.getFirstName() + " kullanýcýsý baþarýyla sisteme eklendi.\n");
			emailVerificationService.sendMail(customer.getEmail());
			customerDao.add(customer);
		}		
	}

	@Override
	public void signIn(Customer customer) {
		emailVerificationService.verifyMail(customer.getEmail());
		if (customerDao.getEmail(customer.getEmail()) && customerDao.getPassword(customer.getPassword())
				&& emailVerificationService.isVerificated(customer.getEmail()) == true) {
			System.out.println("Kullanýcý giriþi baþarýyla yapýldý.");

		} else if (emailVerificationService.isVerificated(customer.getEmail()) == false) {
			System.out.println("Kullanýcý bilgileri doðru ama mail adresi doðrulanmadýðý için giriþ yapýlamýyor.");
		} else {
			System.out.println("Kullanýcý bilgileri yanlýþ, lütfen kontrol ediniz.");
		}
	}

}
