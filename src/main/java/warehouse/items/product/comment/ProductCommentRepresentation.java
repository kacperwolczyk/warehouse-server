package warehouse.items.product.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import warehouse.items.product.ProductRepresentation;

@AllArgsConstructor
@Data
public class ProductCommentRepresentation {

  private Integer id;
  
  private String content;
}
