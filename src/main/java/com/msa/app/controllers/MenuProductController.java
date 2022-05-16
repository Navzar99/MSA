package com.msa.app.controllers;

import com.msa.app.dtos.MenuProductDTO;
import com.msa.app.entities.MenuProduct;
import com.msa.app.services.MenuProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController// This means that this class is a Controller
@RequestMapping(path="/menu") // This means URL's start with /demo (after Application path)
@CrossOrigin("http://localhost:4200")
public class MenuProductController {
    private final MenuProductServices menuProductServices;

    public MenuProductController(MenuProductServices productServices) {
        this.menuProductServices = productServices;
    }

    // POST
    @PostMapping(path = "/addProduct")
    public ResponseEntity<MenuProduct> addProduct(@RequestBody MenuProductDTO productDTO) {
        return new ResponseEntity<MenuProduct>(menuProductServices.addProduct(productDTO), HttpStatus.CREATED);
    }

    // GET
    @GetMapping(path = "/showAllProducts")
    public ResponseEntity<List<MenuProduct>> getProduct(){
        return new ResponseEntity<List<MenuProduct>>(menuProductServices.getAllProducts(), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping(path = "/removeItemById/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable Integer id){
        menuProductServices.deleteProductById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // PUT
    @PutMapping(path = "/editProductById/{id}")
    public ResponseEntity<MenuProduct> editProduct(@RequestBody MenuProductDTO productDTO, @PathVariable("id") Integer id) {
        Optional<MenuProduct> searchedMenuProduct = menuProductServices.editProduct(productDTO, id);
        return searchedMenuProduct
                .map(menuProduct -> new ResponseEntity<>(menuProduct, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path= "/setIsInStockTrueById/{id}")
    public ResponseEntity<MenuProduct> setIsInStockTrueById(@PathVariable("id") Integer id) {
        Optional<MenuProduct> searchedMenuProduct = menuProductServices.setIsInStockTrueById(id);
        return searchedMenuProduct
                .map(menuProduct -> new ResponseEntity<>(menuProduct, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path= "/setIsInStockFalseById/{id}")
    public ResponseEntity<MenuProduct> setIsInStockFalseById(@PathVariable("id") Integer id) {
        Optional<MenuProduct> searchedMenuProduct = menuProductServices.setIsInStockFalseById(id);
        if (searchedMenuProduct.isPresent())
            return new ResponseEntity<MenuProduct>(searchedMenuProduct.get(), HttpStatus.FOUND);

        return new ResponseEntity<MenuProduct>(HttpStatus.NOT_FOUND);
    }
}