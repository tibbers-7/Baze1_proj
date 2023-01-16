package dao;
import java.sql.SQLException;

import model.*;

public interface FilmDAO extends CRUDDao<Film,Integer>{

	Film findByName(String name);

	Film findById(String korIme) throws SQLException;

	String toString(Film f) throws SQLException;

	Iterable<Film> findAllbyG(int zanrID) throws SQLException;

}
