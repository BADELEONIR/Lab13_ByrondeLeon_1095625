package ed.lab.ed1labo04.entidad;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemCarrito> items;

    private double precioTotal;

    public Carrito() {
    }

    public Carrito(List<ItemCarrito> items, double precioTotal) {
        this.items = items;
        this.precioTotal = precioTotal;
    }

    public Long getId() {
        return id;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }
}