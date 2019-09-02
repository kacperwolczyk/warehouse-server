package warehouse.items.product.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CATEGORIES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
  
  private static final String GENERATOR_NAME = "categories.generator";

  @Id
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "T_CATEGORIES_ID_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "ID")
  private Integer id;
  
  @NotNull
  @Column(name = "NAME")
  private String name;
  
  public static ProductCategory of(ProductCategoryRepresentation rep) {
    return new ProductCategory(null, rep.getName());
  }
  
  public ProductCategoryRepresentation toRepresentation() {
    return new ProductCategoryRepresentation(id, name);
  }
}
