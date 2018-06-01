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

    @Query(value="select * from user u left join worker_details r on u.ID = r.user_id where user_id is null", nativeQuery = true)
	List<User> findAllUsers();
    
    @Query(value="select * from user u left join worker_details r on u.ID = r.user_id where user_id is not null", nativeQuery = true)
	List<User> findAllWorkers();

}
