package pl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.model.Produkty;

@Repository
public interface ProduktyRepository extends JpaRepository<Produkty,Long> {
}
