package warehouse.items;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import warehouse.items.product.Product;
import warehouse.items.product.ProductRepository;
import warehouse.items.product.ProductRepresentation;
import warehouse.items.product.ProductStatus;
import warehouse.items.product.category.ProductCategory;
import warehouse.items.product.category.ProductCategoryRepository;
import warehouse.items.product.category.ProductCategoryRepresentation;
import warehouse.items.product.comment.ProductComment;
import warehouse.items.product.comment.ProductCommentRepository;
import warehouse.items.product.comment.ProductCommentRepresentation;
import warehouse.items.product.parameter.ProductParameter;
import warehouse.items.product.parameter.ProductParameterRepository;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin
public class ProductWarehouseController {

  private ProductRepository productRepository;
  private ProductCategoryRepository categoryRepository;
  private ProductCommentRepository commentRepository;
  private ProductParameterRepository parameterRepository;
  
  @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ProductRepresentation> getAllProducts() {
    return productRepository.findAll().stream().map(product -> product.toRepresentation()).collect(Collectors.toList());
  }
  
  @GetMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ProductCategoryRepresentation> getAllCategories() {
    return categoryRepository.findAll().stream().map(category -> category.toRepresentation()).collect(Collectors.toList());
  }
  
  @Transactional
  @PostMapping(path = "/categories", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ProductCategory createCategory(@RequestBody ProductCategoryRepresentation representation) {
    ProductCategory entity = ProductCategory.of(representation);
    log.info(representation.getName());
    return categoryRepository.save(entity);
  }
  
  @GetMapping(path = "/products/{id}")
  public ProductRepresentation getProduct(@PathVariable Integer id) {
    return productRepository.findById(id).map(prod -> prod.toRepresentation()).orElse(null);
  }
  
  @Transactional
  @PostMapping(path = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public void createProduct(@RequestBody ProductRepresentation representation) {
    ProductCategory category = categoryRepository.getOne(representation.getCategory().getId());
    List<ProductParameter> parameters = representation.getParameters().stream()
        .map(rep -> ProductParameter.of(rep))
        .filter(param -> param.getContent() != null && param.getName() != null)
        .collect(Collectors.toList());
    Product entity = productRepository.save(Product.of(representation, category));
    parameters.forEach(param -> {
      param.setProduct(entity);
      parameterRepository.save(param);
    });
  }
 
  @Transactional
  @PostMapping(path = "/products/{id}/comment", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public void commentProduct(@PathVariable Integer id, @RequestBody ProductCommentRepresentation representation) {
    Product entity = productRepository.getOne(id);
    commentRepository.save(ProductComment.of(representation, entity));
  }
  
  
  @Transactional
  @PutMapping(path = "/products/{id}")
  public void updateProductStatus(@PathVariable Integer id, @RequestBody ProductRepresentation representation) {
    Product entity = productRepository.getOne(id);
    ProductStatus newStatus = entity.getStatus() == ProductStatus.SPRAWNY ? ProductStatus.ZEPSUTY : ProductStatus.SPRAWNY;
    entity.setStatus(newStatus);
  }
  
}
