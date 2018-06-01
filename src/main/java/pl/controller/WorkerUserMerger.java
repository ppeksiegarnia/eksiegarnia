package pl.controller;

import pl.model.Pracownik;
import pl.model.User;

public class WorkerUserMerger {
	private User uzytkownik;
	private Pracownik pracownik;
	
	WorkerUserMerger(){
	}
	
	WorkerUserMerger(User uzytkownik, Pracownik pracownik){
		this.uzytkownik = uzytkownik;
		this.pracownik = pracownik;
	}
	
	public void setUzytkownik(User uzytkownik) {
		this.uzytkownik = uzytkownik;
	}
	
	public void setPracownik(Pracownik pracownik) {
		this.pracownik = pracownik;
	}
	
	public Long getID() {
		return uzytkownik.getID();
	}
	
	public String getImie() {
		return uzytkownik.getImie();
	}
	
	public String getNazwisko() {
		return uzytkownik.getNazwisko();
	}
	
	 public String getEmail() {
	        return uzytkownik.getEmail();
	    }

	    public String getPlec() {
	        return uzytkownik.getPlec();
	    }

	    public String getHaslo() {
	        return uzytkownik.getHaslo();
	    }
		
		public String getData_zatrudnienia() {
			return pracownik.getData_zatrudnienia();
		}

		public int getPesel() {
			return pracownik.getPesel();
		}

		public double getPensja() {
			return pracownik.getPensja();
		}
	
	public User getUzytkownik() {
		return uzytkownik;
	}
	
	public Pracownik getPracownik() {
		return pracownik;
	}
}
