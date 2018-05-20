package pl.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long ID;

    private String imie;
    private String nazwisko;
    private String email;
    private String plec;
    private String haslo;
    private String haslo2;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Set<UserRoles> userRolesSet=new HashSet<>();

    public User(String imie, String nazwisko, String email, String plec, String haslo, String haslo2, Set<UserRoles> userRolesSet) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.plec = plec;
        this.haslo = haslo;
        this.haslo2 = haslo2;
        this.userRolesSet = userRolesSet;
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

    public String getHaslo2() {
        return haslo2;
    }

    public void setHaslo2(String haslo2) {
        this.haslo2 = haslo2;
    }

    public Set<UserRoles> getUserRolesSet() {
        return userRolesSet;
    }

    public void setUserRolesSet(Set<UserRoles> userRolesSet) {
        this.userRolesSet = userRolesSet;
    }
}
