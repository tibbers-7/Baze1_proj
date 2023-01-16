package dao;

import java.sql.SQLException;

import model.Korisnik;

public interface KorisnikDAO extends CRUDDao<Korisnik, Integer> {

	boolean deactivateUser(String username) throws SQLException;

	Korisnik findById(String korIme) throws SQLException;

}
