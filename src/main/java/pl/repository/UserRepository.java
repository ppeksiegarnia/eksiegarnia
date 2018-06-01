package pl.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.model.User;
import pl.model.UserRoles;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{
    User findFirstByEmail(String email);

    @Query(value="SELECT * FROM user u LEFT JOIN user_user_roles_set r ON u.ID = r.user_id WHERE user_roles_set_id IS NULL", nativeQuery = true)
	List<User> findAllUsers();

    @Query(value="select * from user u join worker_details p on u.id = p.user_id;", nativeQuery = true)
	List<User> findAllWorkers();
    
    

}
