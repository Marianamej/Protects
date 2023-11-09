package com.gamertx.persistence;

import com.gamertx.domain.Item;
import com.gamertx.domain.Order;
import com.gamertx.domain.Product;
import com.gamertx.domain.repository.OrderRepository;
import com.gamertx.exceptions.ResourceAlreadyExist;
import com.gamertx.persistence.crud.PedidoCrudRepository;
import com.gamertx.persistence.crud.ProductoCrudRepository;
import com.gamertx.persistence.entity.products_view.DetallePedido;
import com.gamertx.persistence.entity.products_view.Imagen;
import com.gamertx.persistence.entity.products_view.Pedido;
import com.gamertx.persistence.entity.products_view.Producto;
import com.gamertx.persistence.mapper.ItemMapper;
import com.gamertx.persistence.mapper.OrderMapper;
import com.gamertx.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Repository
public class PedidoRespository implements OrderRepository {
    @Autowired
    private PedidoCrudRepository pedidoCrudRepository;
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private OrderMapper mapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Order> getAll() {
        return mapper.toOrders((List<Pedido>) pedidoCrudRepository.findAll());
    }

    @Override
    public Optional<List<Order>> getByClient(String email) {
        return pedidoCrudRepository.findByEmailUsuarioOrderByIdPedido(email).map(
                pedidos -> mapper.toOrders(pedidos)
        );
    }

    @Override
    public List<Product> getItemsByClient(String email){
        List<Integer> idProductos = new ArrayList<>();
        List<Producto> productos = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<Order> ord =  pedidoCrudRepository.findByEmailUsuarioOrderByIdPedido(email).map(
                pedidos -> mapper.toOrders(pedidos)).orElse(null);

        for (Order orden : ord){
            List<Item> items =  orden.getItems();
            for (Item item : items){
                idProductos.add(item.getItemId());
            }
        }

        for (int id : idProductos ) {
            Producto producto = productoCrudRepository.findById(id).orElse(null);
            productos.add(producto);
        }

        for (Producto producto : productos){
            List<String> urls = getImagenUrls(producto);

            Product product = productMapper.toProduct(producto);
            product.setUrlsImages(urls);
            products.add(product);
        }
        return products;
    }

    private List<String> getImagenUrls(Producto producto) {
        List<Imagen> imagenes = producto.getImagenes();
        List<String> urls = new ArrayList<>(imagenes.size());

        for (Imagen imagen : imagenes) {
            urls.add(imagen.getUrl());
        }
        return urls;
    }

    @Override
    public Order addItem(Item item, String email) {
        DetallePedido detallePedido = itemMapper.toDetallePedido(item);

        return pedidoCrudRepository.findByEmailUsuario(email).map(pedido -> {

            List<DetallePedido> lista = pedido.getProductos();
            for (DetallePedido detallePedido1 : lista) {
                int id = detallePedido1.getId().getIdProducto();
                if (detallePedido.getId().getIdProducto() == id) {
                    throw new ResourceAlreadyExist("Producto","Existe");
                }
            }

            pedido.getProductos().add(detallePedido);
            detallePedido.setPedido(pedido);
            Producto producto = productoCrudRepository.findById(detallePedido.getId().getIdProducto()).orElse(null);


            if (producto != null) {
                detallePedido.setTotal(new BigDecimal(detallePedido.getCantidad()).multiply(producto.getPrecio()));
            }

            pedido.setTotal(calcularTotal(pedido.getProductos()));
            return mapper.toOrder(pedidoCrudRepository.save(pedido));
        }).orElseThrow(NoSuchElementException::new);
    }

    private BigDecimal calcularTotal(List<DetallePedido> items) {
        BigDecimal total = BigDecimal.ZERO;
        for (DetallePedido detalle : items) {
            total = total.add(detalle.getTotal());
        }
        return total;
    }

    @Override
    public Order save(Order order) {
        Pedido pedido = mapper.toPedido(order);
        pedido.getProductos().forEach(producto -> producto.setPedido(pedido));
        return mapper.toOrder(pedidoCrudRepository.save(pedido));
    }
}
