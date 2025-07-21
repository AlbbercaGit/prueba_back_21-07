package com.example.similar_products_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class SimilarProductsController {
    // RestTemplate simple y thread pool local para máxima eficiencia
    private final RestTemplate restTemplate = new RestTemplate();
    private final String SIMILAR_IDS_URL = "http://localhost:3001/product/{productId}/similarids";
    private final String PRODUCT_DETAIL_URL = "http://localhost:3001/product/{productId}";
    private final ExecutorService executor = Executors.newFixedThreadPool(200);

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDetail>> getSimilarProducts(@PathVariable String productId) {
        try {
             // Obtener IDs de productos similares
            ResponseEntity<String[]> idsResponse = restTemplate.getForEntity(SIMILAR_IDS_URL, String[].class, productId);
            String[] similarIds = idsResponse.getBody();
            if (similarIds == null || similarIds.length == 0) {
                return ResponseEntity.ok(new ArrayList<>());
            }
            // Llamadas concurrentes para obtener el detalle de cada producto
            List<CompletableFuture<ProductDetail>> futures = new ArrayList<>();
            for (String id : similarIds) {
                futures.add(CompletableFuture.supplyAsync(() -> {
                    try {
                        ResponseEntity<ProductDetail> detailResponse = restTemplate.getForEntity(PRODUCT_DETAIL_URL, ProductDetail.class, id);
                        return detailResponse.getBody();
                    } catch (Exception e) {
                        return null;
                    }
                }, executor));
            }
            // Recoger resultados y filtrar nulos
            List<ProductDetail> details = futures.stream()
                .map(CompletableFuture::join)
                .filter(p -> p != null)
                .collect(Collectors.toList());
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
