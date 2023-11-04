package com.gamertx.persistence;

import com.gamertx.domain.Product;
import com.gamertx.domain.dto.Response;
import com.gamertx.domain.repository.ProductRepository;
import com.gamertx.persistence.crud.ProductoCrudRepository;
import com.gamertx.persistence.entity.products_view.Imagen;
import com.gamertx.persistence.entity.products_view.Producto;
import com.gamertx.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public Response getAll(int pageNumber, int size,String sortBy,String sortField){
        Sort sort = sortField.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,size,sort);
        Page<Producto> productosPaginados = productoCrudRepository.findAll(pageable);

        List<Producto> productos = productosPaginados.getContent();
        List<Product> products = new ArrayList<>(productos.size());

        for (Producto producto : productos) {
            List<String> urls = getImagenUrls(producto);

            Product product = mapper.toProduct(producto);
            product.setUrlsImages(urls);
            products.add(product);
        }

        Response response = new Response();
        response.setContent(products);
        response.setPageNumber(productosPaginados.getNumber());
        response.setSizeContent(productosPaginados.getSize());
        response.setProductsTotal(productosPaginados.getTotalElements());
        response.setPagesTotal(productosPaginados.getTotalPages());
        response.setLast(productosPaginados.isLast());

        return response;
    }


    // Función para obtener las URLs de las imágenes de un producto
    private List<String> getImagenUrls(Producto producto) {
        List<Imagen> imagenes = producto.getImagenes();
        List<String> urls = new ArrayList<>(imagenes.size());

        for (Imagen imagen : imagenes) {
            urls.add(imagen.getUrl());
        }
        return urls;
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> {
            List<String> urls = getImagenUrls(producto); // Utiliza la función
            Product product = mapper.toProduct(producto);
            product.setUrlsImages(urls);
            return product;
        });
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional <List<Producto>> productos = productoCrudRepository.findByStockLessThan(quantity);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Product saveProduct(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    @Override
    public Product updateProduct(Product newProduct, int id) {
        Producto newProducto = mapper.toProducto(newProduct);
        return productoCrudRepository.findById(id).map(
                producto -> {
                    producto.setIdCategoria(newProducto.getIdCategoria());
                    producto.setIdMarca(newProducto.getIdMarca());
                    producto.setIdOferta(newProducto.getIdOferta());
                    producto.setNombre(newProducto.getNombre());
                    producto.setStock(newProducto.getStock());
                    producto.setDescripcion(newProducto.getDescripcion());
                    producto.setPrecio(newProducto.getPrecio());
                    producto.setValoracion(newProducto.getValoracion());
                    producto.setColor(newProducto.getColor());
                    return mapper.toProduct(productoCrudRepository.save(producto));
                }
        ).get();
    }

    @Override
    public void deleteProduct(int productId) {
        productoCrudRepository.deleteById(productId);
    };
}
