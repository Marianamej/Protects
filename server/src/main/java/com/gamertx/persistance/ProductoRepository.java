package com.gamertx.persistance;

import com.gamertx.persistance.crud.ProductoCrudRepository;
import com.gamertx.persistance.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//Con esta etiqueta le estamos indicando a Spring que la clase esta interactuando con la Base de datos realizando operaciones
@Repository
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    //Se crea un metodo que retorna una lista de productos, el metodo onsiste, en utilizar
    //el metodo findAll() de la interfaz ProductoCrudRepository, que me devuelve un iterable con todos los registros de la
    //la tabla
    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategory(int idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getLastUnits(int unidadesMaximas){
        return productoCrudRepository.findByStockLessThan(unidadesMaximas);
    }

    public Optional<Producto> getProduct(int idProducto){
        return productoCrudRepository.findById(idProducto);
    };

    public Producto saveProduct(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void deleteProduct(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }

    public Producto updateProduct(Producto newProducto, int id){
        return productoCrudRepository.findById(id).map(
                producto -> {
                    producto.setIdCategoria(newProducto.getIdCategoria());
                    producto.setIdMarca(newProducto.getIdMarca());
                    producto.setIdModelo(newProducto.getIdModelo());
                    producto.setIdOferta(newProducto.getIdOferta());
                    producto.setNombre(newProducto.getNombre());
                    producto.setStock(newProducto.getStock());
                    producto.setDescripcion(newProducto.getDescripcion());
                    producto.setPrecio(newProducto.getPrecio());
                    producto.setFechaCreacion(newProducto.getFechaCreacion());
                    producto.setValoracion(newProducto.getValoracion());
                    producto.setEstado(newProducto.getEstado());
                    return productoCrudRepository.save(producto);
                }
        ).get();
    }
}
