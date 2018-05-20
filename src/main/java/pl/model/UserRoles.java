package pl.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserRoles {

    @Id
    @GeneratedValue
    private Long ID;

    private String role;
    private String description;

    public UserRoles(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public UserRoles() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    
}

