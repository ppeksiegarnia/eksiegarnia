package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.model.Zdjecia;

public interface ZdjeciaRepository extends JpaRepository<Zdjecia,Long> {
}
