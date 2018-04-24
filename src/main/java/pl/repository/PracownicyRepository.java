package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.model.Pracownik;

import javax.persistence.Entity;

@Repository
public interface PracownicyRepository extends JpaRepository<Pracownik,Long> {
}
