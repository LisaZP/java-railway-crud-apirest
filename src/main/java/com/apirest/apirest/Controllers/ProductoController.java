package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apirest.apirest.ApirestApplication;
import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    public final ApirestApplication apirestApplication;

    @Autowired
    private ProductoRepository productoRepository;

    ProductoController(ApirestApplication apirestApplication) {
        this.apirestApplication = apirestApplication;
    }

    @GetMapping
    public List<Producto> geProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProdcutobyId(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
    }



    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        Producto producto=  productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el producto con el ID: " + id));
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto= productoRepository.findById(id)
        .orElseThrow(() ->new RuntimeException("No se encontro el prodcuto con le ID: " + id));

        productoRepository.delete(producto);
        return "El producto con el ID: " + id + "fue eliminado correctamente";
    }

}
