package warehouse.items.product.comment;

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
@Table(name = "T_COMMENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductComment {
  
  private static final String GENERATOR_NAME = "comments.generator";
  
  @Id
  @SequenceGenerator(name = GENERATOR_NAME, sequenceName = "T_COMMENTS_ID_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
  @Column(name = "ID")
  private Integer id;
  
  @NotNull
  @Column(name = "CONTENT")
  private String content;
  
  @JoinColumn(name = "T_PRODUCTS_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private Product product;

  public static ProductComment of(ProductCommentRepresentation rep, Product product) {
    return new ProductComment(null, rep.getContent(), product);
  }
  
  public ProductCommentRepresentation toRepresentation() {
    return new ProductCommentRepresentation(id, content);
  }
}
