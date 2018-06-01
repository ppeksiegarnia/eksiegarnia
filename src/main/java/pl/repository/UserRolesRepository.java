package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.model.User;
import pl.model.UserRoles;

@Repository
public interface UserRolesRepository  extends JpaRepository<UserRoles,Long>{

    UserRoles findFirstByRole(String role);
}
