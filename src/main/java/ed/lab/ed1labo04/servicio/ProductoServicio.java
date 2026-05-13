package ed.lab.ed1labo04.servicio;

import ed.lab.ed1labo04.entidad.Producto;
import ed.lab.ed1labo04.modelo.ProductoActualizarSolicitud;
import ed.lab.ed1labo04.modelo.ProductoSolicitud;
import ed.lab.ed1labo04.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    private final ProductoRepositorio repositorio;

    public ProductoServicio(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Producto crear(ProductoSolicitud solicitud) {

        if (solicitud.getPrecio() <= 0) {
            return null;
        }

        Producto producto = new Producto(
                solicitud.getNombre(),
                solicitud.getPrecio(),
                0
        );

        return repositorio.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return repositorio.findAll();
    }

    public Producto obtenerPorId(Long id) {

        Optional<Producto> producto = repositorio.findById(id);

        return producto.orElse(null);
    }

    public Producto actualizar(
            Long id,
            ProductoActualizarSolicitud solicitud
    ) {

        if (solicitud.getPrecio() <= 0 || solicitud.getCantidad() < 0) {
            return null;
        }

        Optional<Producto> optional = repositorio.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Producto producto = optional.get();

        producto.setPrecio(solicitud.getPrecio());
        producto.setCantidad(solicitud.getCantidad());

        return repositorio.save(producto);
    }
}