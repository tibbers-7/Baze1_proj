package dto;

import model.Korisnik;
import model.Ocena;

//Klase za z3
public class UserReviewsDTO {
	private Korisnik k;
	private Ocena o;
	
	
	
	public UserReviewsDTO(Korisnik k, Ocena o) {
		super();
		this.k = k;
		this.o = o;
	}
	public Korisnik getK() {
		return k;
	}
	public void setK(Korisnik k) {
		this.k = k;
	}
	public Ocena getO() {
		return o;
	}
	public void setO(Ocena o) {
		this.o = o;
	}
	
}
