package pl.model;

import lombok.Data;

import javax.persistence.*;



import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
@Inheritance(
    strategy = InheritanceType.JOINED
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long ID;

    private String imie;
    private String nazwisko;
    private String email;
    private String plec;
    private String haslo;
    
    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    private Pracownik pracownik;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Set<UserRoles> userRolesSet=new HashSet<>();
    
    @ManyToMany(cascade= CascadeType.ALL,fetch=FetchType.EAGER.EAGER)
    private List<Produkty> productSet=new LinkedList<>();

    public User(String imie, String nazwisko, String email, String plec, String haslo, Set<UserRoles> userRolesSet) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.plec = plec;
        this.haslo = haslo;
        this.userRolesSet = userRolesSet;
    }

    
    
 




	public User(Long iD, String imie, String nazwisko, String email, String plec, String haslo, Pracownik pracownik,
			Set<UserRoles> userRolesSet, List<Produkty> productSet) {
		super();
		ID = iD;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.plec = plec;
		this.haslo = haslo;
		this.pracownik = pracownik;
		this.userRolesSet = userRolesSet;
		this.productSet = productSet;
	}








	public Pracownik getPracownik() {
		return pracownik;
	}




	public void setPracownik(Pracownik pracownik) {
		this.pracownik = pracownik;
	}








	public List<Produkty> getProductSet() {
		return productSet;
	}








	public void setProductSet(List<Produkty> productSet) {
		this.productSet = productSet;
	}








	public User() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Set<UserRoles> getUserRolesSet() {
        return userRolesSet;
    }

    public void setUserRolesSet(Set<UserRoles> userRolesSet) {
        this.userRolesSet = userRolesSet;
    }




	@Override
	public String toString() {
		return "User [ID=" + ID + ", imie=" + imie + ", nazwisko=" + nazwisko + ", email=" + email + ", plec=" + plec
				+ ", haslo=" + haslo + ", pracownik=" + pracownik + ", userRolesSet=" + userRolesSet + ", productSet="
				+ productSet + "]";
	}
    
    
    
}
