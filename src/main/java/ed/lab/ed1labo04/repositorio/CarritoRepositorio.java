package ed.lab.ed1labo04.repositorio;

import ed.lab.ed1labo04.entidad.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepositorio
        extends JpaRepository<Carrito, Long> {
}