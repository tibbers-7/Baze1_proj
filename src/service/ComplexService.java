package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.FilmDAO;
import dao.FilmDAOImpl;
import dao.KorisnikDAO;
import dao.KorisnikDAOImpl;
import dao.OcenaDAO;
import dao.OcenaDAOImpl;
import dao.ZanrDAO;
import dao.ZanrDAOImpl;
import dto.UserReviewsDTO;
import model.Film;
import model.Korisnik;
import model.Ocena;
import model.Zanr;

public class ComplexService {
	private static final KorisnikDAO korisnikDAO = new KorisnikDAOImpl();
	private static final OcenaDAO ocenaDAO = new OcenaDAOImpl();
	private static final ZanrDAO zanrDAO= new ZanrDAOImpl();
	private static final FilmDAO filmDAO=new FilmDAOImpl();
		
	
	public static void UserReviews(String korIme) throws SQLException {
		Korisnik k=korisnikDAO.findById(korIme);
		Iterable<Ocena> ocene=ocenaDAO.findAllByU(korIme);
		
		System.out.print(korisnikDAO.getFormattedHeader());
		System.out.printf(korisnikDAO.toString(k));
		
		
		System.out.printf("\n OCENE KORISNIKA \n");
		System.out.printf(" -------------------------------- \n");
		System.out.printf("%s \n",ocenaDAO.getFormattedHeader());
		for(Ocena o:ocene) {
			System.out.printf(ocenaDAO.toString(o));
		}
	}
	
	public static void GenreData(String nameG) throws SQLException {
		Zanr z=zanrDAO.findById(nameG);
		Iterable<Film> filmovi=filmDAO.findAllbyG(z.getIdZ());
		
		int ukTraj=0;
		int cnt=0;
		int mostRecent=0;
		
		
		System.out.printf("\n FILMOVI U ZANRU: %s \n",z.getNazivZ());
		System.out.printf(" -------------------------------- \n");
		System.out.printf("%s \n",filmDAO.getFormattedHeader());
		for(Film f: filmovi) {
			System.out.printf(filmDAO.toString(f));
			ukTraj+=f.getTrajanjeF();
			cnt++;
			if (Integer.parseInt(f.getGodF())>mostRecent) {
				mostRecent=Integer.parseInt(f.getGodF());
			}
			}
		
		float prosek=ukTraj/cnt;
		System.out.printf(" -------------------------------- \n");
		System.out.printf("Prosecno trajanje filmova: %.2f\nGodina najnovijeg filma: %d \n",prosek,mostRecent);
	
	
	}
	
	public static boolean DeactivateUser(String username) throws SQLException {
		Korisnik k=korisnikDAO.findById(username);
		try {
			korisnikDAO.deactivateUser(username);
		} catch(SQLException e) {
			return false;
		}
		
		Iterable<Ocena> ocene=ocenaDAO.findAllByU(username);
		for(Ocena o:ocene) {
			try {
				ocenaDAO.dismissReview(o.getIdO());
			} catch(SQLException e){
				e.printStackTrace();
				return false;
			}
			
		}
		return true;
	}
	
	public static void userReviewReport(String username) throws SQLException {
		Korisnik k=korisnikDAO.findById(username);
		Iterable<Ocena> ocene=ocenaDAO.findAllByU(username);
		int rating;
		int minRating=10;
		Film f= new Film();
		
		System.out.printf("\n IZVESTAJ: \n");
		System.out.printf(" -----------------------------\n");
		for(Ocena o:ocene) {
			String ratingStars="";
			rating=o.getOcenaO();
			for (int i=0;i<rating;i++) {
				ratingStars=ratingStars+"*";
			}
			
			if (o.getOcenaO()<minRating) {
				minRating=o.getOcenaO();
			}
			f=filmDAO.findById(o.getFilmO());
			System.out.printf("Film %s: - %s\n",f.getNazivF(),ratingStars);
		}
		System.out.printf(" -----------------------------\n");
		
		System.out.printf("Minimalna ocena je %d.",minRating);
	}
}
