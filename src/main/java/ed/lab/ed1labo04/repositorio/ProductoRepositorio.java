package ed.lab.ed1labo04.repositorio;

import ed.lab.ed1labo04.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}