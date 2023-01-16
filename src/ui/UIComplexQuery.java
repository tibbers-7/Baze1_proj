package ui;

import java.sql.SQLException;
import java.util.Scanner;

import service.ComplexService;

public class UIComplexQuery {

	private static final ComplexService cservice=new ComplexService();
	public static void showUserReviews(Scanner sc) throws SQLException {
		
		String username=null;
		
		System.out.printf("Ukucajte username: ");
		username=sc.nextLine();
		
		cservice.UserReviews(username);
		
	}
	
	public static void genreFilms(Scanner sc) throws SQLException {
		
		String zanrID=null;
		
		System.out.printf("Ukucajte naziv zanra: ");
		zanrID=sc.nextLine();
		
		cservice.GenreData(zanrID);
	}
	
	public static void deacUser(Scanner sc) throws SQLException {
		System.out.printf("Ukucajte username: ");
		String username=sc.nextLine();
		
		if(cservice.DeactivateUser(username)) {
			System.out.printf("Uspesno deaktiviran korisnik!");
		} else System.out.printf("Deaktivacija neuspesna!");

		
	}
	
	public static void userReport(Scanner sc) throws SQLException {
		System.out.printf("Ukucajte username: ");
		String username=sc.nextLine();
		
		cservice.userReviewReport(username);
	}
}
