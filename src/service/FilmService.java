package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


import dao.FilmDAO;
import dao.FilmDAOImpl;
import model.Film;

public class FilmService {
	
	
	private static final FilmDAO filmDAO = new FilmDAOImpl();

	
	public int getCount() throws SQLException {
		return filmDAO.count();
	}
	
	public boolean delFilm(Scanner sc) throws SQLException {
	
		Film f= new Film();
		
		System.out.printf("Unesite naziv filma: ");
		String name=sc.nextLine();
		
		f=filmDAO.findByName(name);
		return filmDAO.delete(f);
	}
	
	public int delAll(Scanner sc) throws SQLException {
		int ret=0;
		System.out.printf("Koliko filmova zelite da obrisete?");
		int numIt=Integer.parseInt(sc.nextLine());
		Film f= new Film();
		String name=null;
		
		for (int i=0; i<numIt;) {
			System.out.printf("Naziv filma:\n");
			name=(sc.nextLine());
			f=filmDAO.findByName(name);
			
			if (filmDAO.delete(f)) {
				ret++;
			}
			return ret;
		}
		
		int numDel=filmDAO.deleteAll();
		System.out.printf("Broj obrisanih filmova je: %d",numDel);
		return numDel;
		
	}
	
	public boolean delID(int id) throws SQLException {
		return filmDAO.deleteById(id);
	}
	
	
	
	public boolean existsID(int id) throws SQLException {
		return filmDAO.existsById(id);
	}
	
	public Iterable<Film> findAll() throws SQLException{
		return filmDAO.findAll();
	}
	
	public Iterable<Film> findAllById(Iterable<Integer> ids) throws SQLException{
		return filmDAO.findAllById(ids);
	}
	
	public Film findID(int id) throws SQLException {
		return filmDAO.findById(id);
	}
	
	public boolean save(Scanner sc) throws SQLException{
	
		Film newFilm= new Film();
		
		System.out.printf("Naziv filma:\n");
		newFilm.setNazivF(sc.nextLine());
		System.out.printf("ID filma:\n");
		newFilm.setIdF(Integer.parseInt(sc.nextLine()));
		System.out.printf("Trajanje filma:\n");
		newFilm.setTrajanjeF(Integer.parseInt(sc.nextLine()));
		System.out.printf("Zanr filma:\n");
		System.out.printf("10-SciFi\n20-Romanticni\n30-Horor\n40-Akcija\n50-Komedija\n60-Deciji\n");
		newFilm.setZanrF(Integer.parseInt(sc.nextLine()));
		System.out.printf("Godina filma:\n");
		newFilm.setIdF(Integer.parseInt(sc.nextLine()));
		
		
		
		return filmDAO.save(newFilm);
		
	}
	
	public int saveAll(Scanner sc) throws SQLException {
		
		Film newFilm= new Film();
		int ret=0;
		List<Film> saveFilms= new ArrayList<Film>();
		
		
		
		System.out.printf("Koliko filmova planirate da unesete?\n");
		int numIt= Integer.parseInt(sc.nextLine());
		
		
		System.out.printf("Zanrovi:\n10-SciFi\n20-Romanticni\n30-Horor\n40-Akcija\n50-Komedija\n60-Deciji\n");
				
				for (int i=0; i<numIt; i++) {
					System.out.printf("\nID filma:");
					newFilm.setIdF(Integer.parseInt(sc.nextLine()));
					System.out.printf("Trajanje filma:\n");
					newFilm.setTrajanjeF(Integer.parseInt(sc.nextLine()));
					System.out.printf("Zanr filma:\n");
					System.out.printf("10-SciFi\n20-Romanticni\n30-Horor\n40-Akcija\n50-Komedija\n60-Deciji\n");
					newFilm.setZanrF(Integer.parseInt(sc.nextLine()));
					System.out.printf("Godina filma:\n");
					newFilm.setIdF(Integer.parseInt(sc.nextLine()));
					
					saveFilms.add(newFilm);
					
					}
				
				Iterable<Film> it= saveFilms;
				sc.close();
				return filmDAO.saveAll(it);
		}
		
				
				
				
}
	
