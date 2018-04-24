package pl.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.model.Klient;

@Repository
public interface KlientRepository extends JpaRepository<Klient,Long> {
}
