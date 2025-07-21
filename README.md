# Similar Products API
La API expone el endpoint `/product/{productId}/similar`, que recibe el ID de un producto y devuelve una lista con el detalle de los productos similares.
- Uno que devuelve los IDs de productos similares.
- Otro que devuelve el detalle de cada producto por su ID.

El backend está preparado para soportar alta concurrencia, usando un pool de hilos y llamadas paralelas para obtener los detalles de los productos de forma eficiente.

## ¿Cómo se ejecuta?

1. **Arranca los mocks y servicios necesarios:**
   ```sh
   docker-compose up -d simulado influxdb grafana
   ```
2. **Comprueba que los mocks funcionan:**
   - Abre en el navegador: `http://localhost:3001/product/1/similarids`

3. **Arranca el backend:**
   ```sh
   mvn spring-boot:run
   ```
   - El API estará disponible en `http://localhost:5000`.

4. **Ejecuta el test de carga:**
   ```sh
   docker-compose run --rm k6 run scripts/test.js
   ```
5. **Consulta los resultados en Grafana:**
   - `http://localhost:3000/d/Le2Ku9NMk/k6-performance-test`

## Sobre el código

- El endpoint principal está en `SimilarProductsController.java`.
- El modelo de datos es `ProductDetail.java`.
- El pool de hilos está ajustado para soportar el test de carga (200 hilos).
- El código es sencillo, directo y fácil de mantener.

## Notas

El proyecto está pensado para la prueba técnica, pero la estructura permite adaptarlo fácilmente a producción. 
