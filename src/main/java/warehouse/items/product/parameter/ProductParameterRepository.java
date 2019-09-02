package warehouse.items.product.parameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductParameterRepository extends JpaRepository<ProductParameter, Integer> {

}
