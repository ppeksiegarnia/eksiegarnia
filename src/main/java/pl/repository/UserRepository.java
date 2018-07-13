package pl.repository;

import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.model.User;
import pl.model.UserRoles;
import java.lang.String;

@Repository
public interface UserRepository  extends JpaRepository<User,Long>{
    User findFirstByEmail(String email);
   

    @Query(value="select * from user u left join user_user_roles_set r on u.ID = r.user_id where user_roles_set_id = 0", nativeQuery = true)
	List<User> findAllUsers();
    
    @Query(value="select * from user u left join worker_details r on u.ID = r.user_id where user_id is not null", nativeQuery = true)
	List<User> findAllWorkers();

    @Modifying
    @Transactional
    @Query(value="delete from user_product_set where user_id = ?1", nativeQuery = true)
    void deleteBooks(Long ID);
    
    @Modifying
    @Transactional
    @Query(value="delete from user_product_set where user_id = ?1 and product_set_id = ?2 limit 1;", nativeQuery = true)
    void deleteBook(Long ID, Long BID);
  
}
