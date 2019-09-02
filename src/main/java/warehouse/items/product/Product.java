package warehouse.items.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import warehouse.items.product.category.ProductCategory;
import warehouse.items.product.comment.ProductComment;
import warehouse.items.product.comment.ProductCommentRepresentation;
import warehouse.items.product.parameter.ProductParameter;
import warehouse.items.product.parameter.ProductParameterRepresentation;

@Entity
@Table(name = "T_PRODUCTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  
  private static final String GENERATOR_NAME = "products.generator";

  @Id
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "T_PRODUCTS_ID_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "ID")
  private Integer id;
  
  @NotNull
  @Column(name = "NAME")
  private String name;
  
  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "T_CATEGORIES_ID")
  private ProductCategory category;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
  private List<ProductComment> comments = new ArrayList<>();
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
  private List<ProductParameter> parameters = new ArrayList<>();
  
  @NotNull
  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private ProductStatus status;
 
  public static Product of(ProductRepresentation rep, ProductCategory category) {
    return new Product(null, rep.getName(), category, null, null, ProductStatus.SPRAWNY);
  }
  
  public ProductRepresentation toRepresentation() {
    List<ProductCommentRepresentation> commentsRep = comments.stream().map(com -> com.toRepresentation()).collect(Collectors.toList());
    List<ProductParameterRepresentation> paramRep = parameters.stream().map(param -> param.toRepresentation()).collect(Collectors.toList());
    return new ProductRepresentation(id, name, category.toRepresentation(), commentsRep, paramRep, status);
  }
}
