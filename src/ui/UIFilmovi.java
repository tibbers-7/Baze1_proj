package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Film;
import service.FilmService;

public class UIFilmovi {
	public static final FilmService fservice= new FilmService();
	
	
	public static void selectOption(Scanner sc) throws SQLException {
		String answer;
		do {
			System.out.println("\nOdaberite opciju:");
			System.out.println("1 - Prebrojavanje svih filmova ");
			System.out.println("2 - Brisanje filma preko naziva ");
			System.out.println("3 - Brisanje vise filmova ");
			System.out.println("4 - Brisanje filma preko ID ");
			System.out.println("5 - Provera da li film postoji preko ID");
			System.out.println("6 - Prikaz svih filmova");
			System.out.println("7 - Pretraga vise filmova ");
			System.out.println("8 - Pretraga jednog filma ");
			System.out.println("9 - Belezenje jednog novog filma ");
			System.out.println("10 - Belezenje vise filmova ");
			System.out.println("X - Izlazak iz programa\n");
			
			answer="X";
			if(sc.hasNext())
				answer=sc.nextLine();
			
			
			
			switch(answer) {
			case "1":
				count();
				break;
			case "2":
				//Ne radi
				delete(sc);
				break;
			case "3":
				//Ne radi
				dltAll(sc);
				break;
				
			case "4":
				//Ne radi
				dltID();
				break;
			case "5":
				existsID(sc);
				break;
			case "6":
				findAll();
				break;
			case "7":
				findAllID(sc);
				break;
			case "8":
				findID(sc);
				break;
			case "9":
				save(sc);
				break;
				
			case "10":
				saveAll(sc);
				break;
			
			}
			
			
		}while (!answer.equalsIgnoreCase("X"));
		
	}
	
	private static void count() throws SQLException {
		int ret=fservice.getCount();
		System.out.printf("Broj filmova je: %d", ret);
	}
	
	private static void delete(Scanner sc) throws SQLException {
		if (fservice.delFilm(sc)) {
			System.out.printf("Uspesno brisanje");
		} else {
			System.out.printf("Neuspesno brisanje");

		}
		
	}
	private static void dltAll(Scanner sc) throws SQLException {
		int numDel=fservice.delAll(sc);
		if (numDel!=0) {
			System.out.printf("Ukupno obrisanih filmova: %d",numDel);
		} else {
			System.out.printf("Neuspesno brisanje");

		}
	}
	
	private static void dltID() throws SQLException {
		Scanner sc= new Scanner(System.in);
		System.out.printf("Unesite id: ");
		
		int id=Integer.parseInt(sc.nextLine());
		
		sc.close();
		if (fservice.delID(id)) {
			System.out.printf("Uspesno brisanje");
		} else {
			System.out.printf("Neuspesno brisanje");

		}
		
		
	}
	private static void existsID(Scanner sc) throws SQLException {
		System.out.printf("Unesite id: ");
		
		int id=Integer.parseInt(sc.nextLine());
		
		sc.close();
		if (fservice.existsID(id)) {
			System.out.printf("Postoji film sa ID: %d",id);
		} else {
			System.out.printf("Ne postoji film sa ID: %d\",id");

		}
		
		
	}
	
	private static void findAll() {
		
		System.out.println(Film.getFormattedHeader());

		try {
			for (Film f : fservice.findAll()) {
				System.out.println(f.toString());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	private static void findAllID(Scanner sc) throws SQLException {
		
		System.out.println("Unesite ID filmova koje trazite: ");
		
		List<Integer> ids= new ArrayList();
		while(sc.nextLine() != "") {
			ids.add(Integer.parseInt(sc.nextLine()));
		}
		System.out.println(Film.getFormattedHeader());
		
		Iterable<Integer> idsIt=ids;
		for(Film f: fservice.findAllById(idsIt)) {
				System.out.printf(f.toString());
		}

		
	}
	private static void findID(Scanner sc) throws SQLException {
		
		System.out.println("Unesite ID filma: ");
		if (sc.nextLine()!=null) {
			int id=Integer.parseInt(sc.nextLine());
			Film f=fservice.findID(id);
			
			System.out.println(Film.getFormattedHeader());
			System.out.printf(f.toString());
		} 
		
		
		
		
	}
	private static void save(Scanner sc) throws SQLException {
		if(fservice.save(sc)) {
			System.out.printf("Uspesno snimljeno");
			
		}else {
			System.out.printf("Neuspesno snimanje!");
		}
		
	}
	private static void saveAll(Scanner sc) throws SQLException {
		
		if(fservice.saveAll(sc)!=0) {
			System.out.printf("Uspesno snimljeno");
			
		}else {
			System.out.printf("Neuspesno snimanje!");
		}
	}
}
