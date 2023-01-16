package dao;

import java.sql.SQLException;

import model.Film;
import model.Korisnik;
import model.Ocena;
import model.Zanr;

public interface CRUDDao<T, ID> {

	int count() throws SQLException;

	boolean delete(T entity) throws SQLException;

	int deleteAll() throws SQLException;

	boolean deleteById(ID id) throws SQLException;

	boolean existsById(ID id) throws SQLException;

	Iterable<T> findAll() throws SQLException;

	Iterable<T> findAllById(Iterable<ID> ids) throws SQLException;

	T findById(ID id) throws SQLException;

	boolean save(T entity) throws SQLException;

	int saveAll(Iterable<T> entities) throws SQLException;

	String getFormattedHeader();

	String toString(T t) throws SQLException;
}
