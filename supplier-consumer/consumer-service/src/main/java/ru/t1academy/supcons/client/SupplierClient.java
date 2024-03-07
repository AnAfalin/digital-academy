package ru.t1academy.supcons.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.t1academy.supcons.dto.CategoryDto;
import ru.t1academy.supcons.dto.FilterProductRequest;
import ru.t1academy.supcons.dto.ProductDto;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SupplierClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    @Value("${supplier.url}")
    private String urlSupplier;

    public List<ProductDto> getAllProducts(FilterProductRequest request, Integer from, Integer size) {


        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if (Objects.nonNull(request.getCategories())) {
            for (String category : request.getCategories()) {
                params.add("category", category);
            }
        }
        if (Objects.nonNull(request.getName())) {
            params.add("name", request.getName());
        }

        if (Objects.nonNull(from)) {
            params.add("from", Integer.toString(from));
        }
        if (Objects.nonNull(size)) {
            params.add("size", Integer.toString(size));
        }
        if (Objects.nonNull(request.getMinPrice())) {
            params.add("minPrice", Double.toString(request.getMinPrice()));
        }
        if (Objects.nonNull(request.getMaxPrice())) {
            params.add("maxPrice", Double.toString(request.getMaxPrice()));
        }

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urlSupplier.concat("/products"))
                .queryParams(params);

        return restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<ProductDto>>() {
                        })
                .getBody();

    }

    public List<CategoryDto> getAllCategories() {
        String url = urlSupplier.concat("/categories");

        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url, Object[].class);

        return Arrays.stream(Objects.requireNonNull(responseEntity.getBody()))
                .map(object -> mapper.convertValue(object, CategoryDto.class))
                .collect(Collectors.toList());
    }

    public void deleteProductById(Long id) {
        String url = urlSupplier.concat("/products/").concat(Long.toString(id));
        restTemplate.delete(url);
    }

    public ResponseEntity<?> addProduct(ProductDto product) {
        String url = urlSupplier.concat("/products");
        return restTemplate.postForObject(url, product, ResponseEntity.class);
    }

    public void updateProductById(Long id, ProductDto product) {
        String url = urlSupplier.concat("/products/").concat(Long.toString(id));
        restTemplate.put(url, product);
    }

    public List<ProductDto> getProductsByFilterParam(FilterProductRequest request) {
        String url = urlSupplier.concat("/products");

        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url, Object[].class, request);

        return Arrays.stream(Objects.requireNonNull(responseEntity.getBody()))
                .map(object -> mapper.convertValue(object, ProductDto.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getProductById(Long id) {
        String url = urlSupplier.concat("/products/").concat(Long.toString(id));
        return restTemplate.getForEntity(url, ResponseEntity.class);

    }
}
