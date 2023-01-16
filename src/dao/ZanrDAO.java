package dao;

import java.sql.SQLException;

import model.Zanr;

public interface ZanrDAO extends CRUDDao<Zanr, Integer> {

	Zanr findById(String zanrIme) throws SQLException;
	
	
}
