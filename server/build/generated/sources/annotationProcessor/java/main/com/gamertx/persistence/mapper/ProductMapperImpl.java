package com.gamertx.persistence.mapper;

import com.gamertx.domain.Product;
import com.gamertx.persistence.entity.products_view.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T08:27:29-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        Product product = new Product();

        if ( producto.getIdProducto() != null ) {
            product.setProductId( producto.getIdProducto() );
        }
        if ( producto.getIdCategoria() != null ) {
            product.setCategoryId( producto.getIdCategoria() );
        }
        if ( producto.getIdMarca() != null ) {
            product.setBrandId( producto.getIdMarca() );
        }
        if ( producto.getIdOferta() != null ) {
            product.setOfferId( producto.getIdOferta() );
        }
        product.setName( producto.getNombre() );
        product.setStockQuantity( producto.getStock() );
        product.setDescription( producto.getDescripcion() );
        product.setPrice( producto.getPrecio() );
        product.setCreationDate( producto.getFechaCreacion() );
        product.setRating( producto.getValoracion() );
        product.setColor( producto.getColor() );

        return product;
    }

    @Override
    public List<Product> toProducts(List<Producto> productos) {
        if ( productos == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productos.size() );
        for ( Producto producto : productos ) {
            list.add( toProduct( producto ) );
        }

        return list;
    }

    @Override
    public Producto toProducto(Product product) {
        if ( product == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setIdProducto( product.getProductId() );
        producto.setIdCategoria( product.getCategoryId() );
        producto.setIdMarca( product.getBrandId() );
        producto.setIdOferta( product.getOfferId() );
        producto.setNombre( product.getName() );
        producto.setStock( product.getStockQuantity() );
        producto.setDescripcion( product.getDescription() );
        producto.setPrecio( product.getPrice() );
        producto.setFechaCreacion( product.getCreationDate() );
        producto.setValoracion( product.getRating() );

        return producto;
    }
}
