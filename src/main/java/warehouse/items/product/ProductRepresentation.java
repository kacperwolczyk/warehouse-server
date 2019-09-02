package warehouse.items.product;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import warehouse.items.product.ProductStatus;
import warehouse.items.product.category.ProductCategoryRepresentation;
import warehouse.items.product.comment.ProductCommentRepresentation;
import warehouse.items.product.parameter.ProductParameterRepresentation;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductRepresentation {

  private Integer id;
  
  private String name;
  
  private ProductCategoryRepresentation category;
  
  private List<ProductCommentRepresentation> comments = new ArrayList<>();
  
  private List<ProductParameterRepresentation> parameters = new ArrayList<>();
  
  private ProductStatus status;
}
