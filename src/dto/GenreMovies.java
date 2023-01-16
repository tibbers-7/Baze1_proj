package dto;

import model.Film;
import model.Zanr;

public class GenreMovies {
	private Zanr z;
	private Film f;
	public Zanr getZ() {
		return z;
	}
	public void setZ(Zanr z) {
		this.z = z;
	}
	public Film getF() {
		return f;
	}
	public void setF(Film f) {
		this.f = f;
	}
	
	
}
