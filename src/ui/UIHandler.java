package ui;

import java.sql.SQLException;
import java.util.Scanner;

public class UIHandler {
	
	public static final Scanner sc= new Scanner(System.in);
	public static final UIFilmovi filmUi= new UIFilmovi();
	public static final UIComplexQuery complexUI= new UIComplexQuery();
	
	
	
	public static void handleMenu() throws SQLException {
		String answer;
		do {
			System.out.println("\nOdaberite opciju:");
			System.out.println("1 - Rukovanje filmovima");
			System.out.println("2 - Pretraga podataka i ocena korisnika preko ID");
			System.out.println("3 - Podaci o filmovima iz zanra zadatog preko ID ");
			System.out.println("4 - Deaktivacija naloga ");
			System.out.println("5 - Ispis ocena korisnika zadatog preko ID ");
			System.out.println("X - Izlazak iz programa");
			answer="X";
			if (sc.hasNext())
				answer = sc.nextLine();
			

			switch (answer) {
			case "1":
				filmUi.selectOption(sc);
				
				break;
			case "2":
				complexUI.showUserReviews(sc);
				break;
			case "3":
				complexUI.genreFilms(sc);
				break;
			case "4":
				complexUI.deacUser(sc);
				break;
			case "5":
				complexUI.userReport(sc);
				break;
			}

		} while (!answer.equalsIgnoreCase("X"));
	}
}
