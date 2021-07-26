package business.abstracts;

public interface EmailVerificationService {
	void sendMail(String email);
	void verifyMail(String email);
	boolean isVerificated(String email);
}
