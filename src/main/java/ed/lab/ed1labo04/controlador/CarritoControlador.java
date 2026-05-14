package ed.lab.ed1labo04.controlador;

import ed.lab.ed1labo04.entidad.Carrito;
import ed.lab.ed1labo04.modelo.CarritoSolicitud;
import ed.lab.ed1labo04.servicio.CarritoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class CarritoControlador {

    private final CarritoServicio servicio;

    public CarritoControlador(CarritoServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Carrito> crear(
            @RequestBody CarritoSolicitud solicitud
    ) {

        Carrito carrito = servicio.crear(solicitud);

        if (carrito == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carrito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> obtenerPorId(
            @PathVariable Long id
    ) {

        Carrito carrito =
                servicio.obtenerPorId(id);

        if (carrito == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carrito);
    }
}