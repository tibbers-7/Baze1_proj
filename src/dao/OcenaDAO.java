package dao;

import java.sql.SQLException;

import model.Ocena;

public interface OcenaDAO extends CRUDDao<Ocena, Integer> {

	boolean dismissReview(int id) throws SQLException;

	Iterable<Ocena> findAllByU(String korIme) throws SQLException;

}
