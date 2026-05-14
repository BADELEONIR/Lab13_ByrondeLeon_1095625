package ed.lab.ed1labo04.servicio;

import ed.lab.ed1labo04.entidad.Carrito;
import ed.lab.ed1labo04.entidad.ItemCarrito;
import ed.lab.ed1labo04.entidad.Producto;
import ed.lab.ed1labo04.modelo.CarritoSolicitud;
import ed.lab.ed1labo04.modelo.ItemCarritoSolicitud;
import ed.lab.ed1labo04.repositorio.CarritoRepositorio;
import ed.lab.ed1labo04.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServicio {

    private final CarritoRepositorio carritoRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public CarritoServicio(
            CarritoRepositorio carritoRepositorio,
            ProductoRepositorio productoRepositorio
    ) {
        this.carritoRepositorio = carritoRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    public Carrito crear(CarritoSolicitud solicitud) {

        List<ItemCarrito> items = new ArrayList<>();

        double total = 0;

        for (ItemCarritoSolicitud itemSolicitud : solicitud.getItems()) {

            Optional<Producto> optional =
                    productoRepositorio.findById(
                            itemSolicitud.getProductoId()
                    );

            if (optional.isEmpty()) {
                return null;
            }

            Producto producto = optional.get();

            if (itemSolicitud.getCantidad() <= 0) {
                return null;
            }

            if (producto.getCantidad()
                    < itemSolicitud.getCantidad()) {
                return null;
            }

            producto.setCantidad(
                    producto.getCantidad()
                            - itemSolicitud.getCantidad()
            );

            productoRepositorio.save(producto);

            ItemCarrito item = new ItemCarrito(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    itemSolicitud.getCantidad()
            );

            items.add(item);

            total += producto.getPrecio()
                    * itemSolicitud.getCantidad();
        }

        Carrito carrito = new Carrito(items, total);

        return carritoRepositorio.save(carrito);
    }

    public Carrito obtenerPorId(Long id) {

        Optional<Carrito> carrito =
                carritoRepositorio.findById(id);

        return carrito.orElse(null);
    }
}