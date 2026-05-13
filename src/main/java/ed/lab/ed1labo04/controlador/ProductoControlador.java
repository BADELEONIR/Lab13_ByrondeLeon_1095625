package ed.lab.ed1labo04.controlador;

import ed.lab.ed1labo04.entidad.Producto;
import ed.lab.ed1labo04.modelo.ProductoActualizarSolicitud;
import ed.lab.ed1labo04.modelo.ProductoSolicitud;
import ed.lab.ed1labo04.servicio.ProductoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoControlador {

    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Producto> crear(
            @RequestBody ProductoSolicitud solicitud
    ) {

        Producto producto = servicio.crear(solicitud);

        if (producto == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public List<Producto> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(
            @PathVariable Long id
    ) {

        Producto producto = servicio.obtenerPorId(id);

        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @PathVariable Long id,
            @RequestBody ProductoActualizarSolicitud solicitud
    ) {

        Producto producto =
                servicio.actualizar(id, solicitud);

        if (producto == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(producto);
    }
}