package pl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.model.Produkty;

import java.util.List;
import java.util.Set;

@Repository
public interface ProduktyRepository extends JpaRepository<Produkty,Long> {


    Page<Produkty> findAllByCzyEbook(boolean ebook,Pageable pageRequest);
    Page<Produkty> findAllByCzyEbookOrderByCenaBruttoAsc(boolean ebooks,Pageable pageRequest);
    Page<Produkty> findAllByCzyEbookOrderByCenaBruttoDesc(boolean ebooke,Pageable pageRequest);

    Page<Produkty>  findAllByKategoriaAndCzyEbook(String kategoria,boolean ebook,Pageable pageRequest);
    Page<Produkty>  findAllByKategoriaAndCzyEbookOrderByCenaBruttoAsc(String kategoriaa,boolean ebooks,Pageable pageRequest);
    Page<Produkty>  findAllByKategoriaAndCzyEbookOrderByCenaBruttoDesc(String kategoriae,boolean ebooka,Pageable pageRequest);

    Page<Produkty> findAllByTytulContaining(String word,Pageable pageable);

}
