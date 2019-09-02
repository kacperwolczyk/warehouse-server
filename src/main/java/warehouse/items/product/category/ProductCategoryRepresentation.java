package warehouse.items.product.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductCategoryRepresentation {

  private Integer id;
  
  private String name;
}
