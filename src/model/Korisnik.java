package model;

public class Korisnik {
	private String korIme;
	private String imeK;
	private String przK;
	private boolean polK;
	private boolean aktivanK;
	
	public Korisnik(String korIme) {
		this.korIme=korIme;
	}
	
	public Korisnik(String korIme, String imeK, String przK, boolean polK, boolean aktivanK) {
		super();
		this.korIme = korIme;
		this.imeK = imeK;
		this.przK = przK;
		this.polK = polK;
		this.aktivanK = aktivanK;
	}
	public Korisnik() {
	}

	public String getKorIme() {
		return korIme;
	}
	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}
	public String getImeK() {
		return imeK;
	}
	public void setImeK(String imeK) {
		this.imeK = imeK;
	}
	public String getPrzK() {
		return przK;
	}
	public void setPrzK(String przK) {
		this.przK = przK;
	}
	public boolean isPolK() {
		return polK;
	}
	public void setPolK(boolean polK) {
		this.polK = polK;
	}
	public boolean isAktivanK() {
		return aktivanK;
	}
	public void setAktivanK(boolean aktivanK) {
		this.aktivanK = aktivanK;
	}
	
	
	
}
