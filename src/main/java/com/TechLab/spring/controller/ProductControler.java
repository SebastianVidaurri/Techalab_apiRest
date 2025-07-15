package com.TechLab.spring.controller;
import com.TechLab.spring.entity.Producto;
import com.TechLab.spring.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //definimos que la clase será un controlador de produc
@RequestMapping("/producto") //especificamos en que ruta vamos a encontrar la clase que vamos a crear
public class ProductControler {

    private ProductoService ps; //productoService

    public ProductControler(ProductoService ps) {
        this.ps = ps;
    }

    @GetMapping("/list") //en esta ruta vamos a obtener todos los productos
    public List<Producto> listaProductos(){ 
        return ps.listarProductos();
    }

    @PostMapping("/")
    //@RequestBody por que voy a recibir el producto por JSON (ose de modo Body)
    public String crearProducto(@RequestBody Producto producto){
        return ps.crearProducto(producto);
    }


    @GetMapping("/find/{idProd}")
    //en buscar producto vamos a recibir el id como parametro
    public Producto buscarProductos(@PathVariable Long idProd){
        return ps.buscarPorId(idProd);
    }

    @GetMapping("/buscar") //Crear find/{productName} con path variable
    public String buscarProdcuto(@RequestParam String nombre, @RequestParam (required = false, defaultValue = "asc") String orden){
        return "buscando... nombre: " + nombre + " orden: " + orden;
    }


    // ..find/3422 ->  ejemplo: le le decimos que busque por el id
    // ..find/{idProd} -> es mejor usar  pathvariable
}
