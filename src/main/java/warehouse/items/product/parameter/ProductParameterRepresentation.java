package warehouse.items.product.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductParameterRepresentation {
  
  private Integer id;
 
  private String name;

  private String content;
}
