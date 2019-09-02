package warehouse.items.product.parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import warehouse.items.product.Product;

@Entity
@Table(name = "T_PARAMETERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductParameter {
  
  private static final String GENERATOR_NAME = "parameters.generator";
  
  @Id
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "T_PARAMETERS_ID_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "ID")
  private Integer id;
  
  @NotNull
  @Column(name = "NAME")
  private String name;
  
  @NotNull
  @Column(name = "CONTENT")
  private String content;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "T_PRODUCTS_ID")
  private Product product;
  
  public static ProductParameter of(ProductParameterRepresentation rep) {
    return new ProductParameter(null, rep.getName(), rep.getContent(), null);
  }
  
  public ProductParameterRepresentation toRepresentation() {
    return new ProductParameterRepresentation(id, name, content);
  }
}
