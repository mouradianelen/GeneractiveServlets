package am.aca.generactive.generactiveservlets.gen.Repository.springrepo;

import am.aca.generactive.generactiveservlets.gen.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemJPARepository extends JpaRepository<Item,Long> {
    @Query("select i from Item i join fetch i.group" +
            " where lower(i.name) = lower(:name)" +
            " and i.basePrice > :priceFrom")
    List<Item> find(@Param("name") String name, @Param("priceFrom") Integer priceFrom);

    @Query("select i.name from Item i")
    List<String> getAllNames();
}
