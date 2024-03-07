package ru.t1academy.supcons.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.t1academy.supcons.dto.FilterProductRequest;
import ru.t1academy.supcons.dto.ProductDto;
import ru.t1academy.supcons.model.Category;
import ru.t1academy.supcons.model.Product;
import ru.t1academy.supcons.repository.ProductRepository;
import ru.t1academy.supcons.service.ProductService;
import ru.t1academy.supcons.service.mapper.ProductMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryServiceImpl categoryService;
    private final ProductMapper productMapper;

    @Transactional
    public ResponseEntity<?> createProduct(ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
        if (Objects.nonNull(productDto.getCategory())) {
            Optional<Category> optionalCategory = categoryService.findCategory(productDto.getCategory());
            optionalCategory.ifPresent(product::setCategory);
        } else {
            product.setCategory(Category.builder().name(productDto.getCategory().getName()).build());
        }

        productRepository.save(product);
        return new ResponseEntity<>("Product was successful saved", HttpStatus.CREATED);
    }

    @Transactional
    public void updateProductById(Long productId, ProductDto product) {
        Product updatableProduct = findProductById(productId);
        updateProduct(updatableProduct, product);
        productRepository.save(updatableProduct);
    }

    @Transactional
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long productId) {
        Product product = findProductById(productId);
        return productMapper.toProductDto(product);
    }

    @Transactional
    public List<ProductDto> getAllProducts(FilterProductRequest request, Pageable pageable) {
        Specification<Product> specification = makeQuerySpecification(request);

        return productMapper.toProductDtos(productRepository.findAll(specification, pageable));
    }

    private Specification<Product> makeQuerySpecification(FilterProductRequest request) {
        Specification<Product> specification = Specification.where(null);
        if (Objects.nonNull(request.getName())) {
            specification = specification.and(
                    (root, query, cb) -> cb.like(cb.lower(root.get("name")),
                            "%".concat(request.getName().toLowerCase()).concat("%")));
        }

        if (Objects.nonNull(request.getMinPrice())) {
            specification = specification.and(
                    (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"),
                            request.getMinPrice()));
        }

        if (Objects.nonNull(request.getMaxPrice())) {
            specification = specification.and(
                    (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"),
                            request.getMaxPrice()));
        }

        if (Objects.nonNull(request.getCategory())) {
            specification = specification.and(
                    (root, query, cb) -> root.get("category").get("name").in(request.getCategory()));
        }
        return specification;
    }

    private Product findProductById(Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product with id='%s' not found".formatted(productId)));
    }

    private void updateProduct(Product updatableProduct, ProductDto srcProduct) {
        if (StringUtils.hasLength(srcProduct.getName())) {
            updatableProduct.setName(srcProduct.getName());
        }
        if (StringUtils.hasLength(srcProduct.getDescription())) {
            updatableProduct.setDescription(srcProduct.getDescription());
        }
        if (Objects.nonNull(srcProduct.getPrice())) {
            updatableProduct.setPrice(srcProduct.getPrice());
        }
    }
}
