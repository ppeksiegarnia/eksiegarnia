package pl.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "WorkerDetails")
@Table(name = "worker_details")
public class Pracownik {

    @Id
    private Long user_id;
    
    @Column(name = "hire_date")
    private String data_zatrudnienia;
    
    @Column(name = "pesel")
    private int pesel;
    
    @Column(name = "salary")
    private double pensja;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    
    
    public Pracownik() {
	}
    
    public Pracownik(User user) {
    	this.user = user;
	}
    
    public Pracownik(Long iD) {
    	user_id = iD;
	}
    
    public Pracownik(Long iD, String data_zatrudnienia, int pesel,
			double pensja) {
    	user_id = iD;
		this.data_zatrudnienia = data_zatrudnienia;
		this.pesel = pesel;
		this.pensja = pensja;
	}
    
	public Long getID() {
		return user_id;
	}
	public void setID(Long iD) {
		user_id = iD;
	}
	
	public String getData_zatrudnienia() {
		return data_zatrudnienia;
	}
	public void setData_zatrudnienia(String data_zatrudnienia) {
		this.data_zatrudnienia = data_zatrudnienia;
	}
	
	public int getPesel() {
		return pesel;
	}
	public void setPesel(int pesel) {
		this.pesel = pesel;
	}
	public double getPensja() {
		return pensja;
	}
	public void setPensja(double pensja) {
		this.pensja = pensja;
	}
	@Override
	public String toString() {
		return "Pracownik [ID=" + user_id + ", data_zatrudnienia="
				+ data_zatrudnienia +  ", pesel=" + pesel + ", pensja=" + pensja
				+ "]";
	}
	
	 public String getImie() {
	        return user.getImie();
	    }

	    public void setImie(String imie) {
	        user.setImie(imie);
	    }

	    public String getNazwisko() {
	        return user.getNazwisko();
	    }

	    public void setNazwisko(String nazwisko) {
	        user.setNazwisko(nazwisko);
	    }

	    public String getEmail() {
	        return user.getEmail();
	    }

	    public void setEmail(String email) {
	        user.setEmail(email);
	    }

	    public String getPlec() {
	        return user.getPlec();
	    }

	    public void setPlec(String plec) {
	        user.setPlec(plec);
	    }
    
	    public String getHaslo() {
	        return user.getHaslo();
	    }

	    public void setHaslo(String haslo) {
	        user.setHaslo(haslo);
	    }
	    public void setUser(User u) {
	    	this.user = u;
	    }
	    
	    public User getUser() {
	    	return user;
	    }
}
