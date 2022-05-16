package com.msa.app.services;

import com.msa.app.dtos.CustomerTableDTO;
import com.msa.app.dtos.MenuProductDTO;
import com.msa.app.dtos.UserDTO;
import com.msa.app.entities.CustomerTable;
import com.msa.app.entities.MenuProduct;
import com.msa.app.entities.User;
import com.msa.app.repositories.MenuProductRepository;
import com.msa.app.security.PasswordManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuProductServices {
    private final MenuProductRepository menuProductRepository;

    public MenuProductServices(MenuProductRepository menuProductRepository) {
        this.menuProductRepository = menuProductRepository;
    }

    public MenuProduct addProduct(MenuProductDTO menuProductDTO){
        return menuProductRepository.save(menuProductDTO.mapToMenuProduct());
    }

    public List<MenuProduct> getAllProducts() { return menuProductRepository.findAll();}

    public List<MenuProduct> getProductsInStock(){ return menuProductRepository.findMenuProductsByIsInStockTrue(); }

    public void deleteProductById(Integer id) {
        menuProductRepository.deleteById(id);
    }

    public Optional<MenuProduct> editProduct(MenuProductDTO userDTO, Integer id) {
        Optional<MenuProduct> menuProduct = menuProductRepository.findById(id);
        if (menuProduct.isPresent())
        {
            MenuProduct newProduct = menuProduct.get();
            newProduct.setName(userDTO.name);
            newProduct.setDescription(userDTO.description);
            newProduct.setIsInStock(userDTO.isInStock);
            newProduct.setPrice(userDTO.price);

            return Optional.of(menuProductRepository.save(newProduct));
        }
        return Optional.empty();
    }

    public Optional<MenuProduct> setIsInStockTrueById(Integer id) {

        Optional<MenuProduct> menuProduct = menuProductRepository.findById(id);//Optional.ofNullable(menuProductRepository.findById(id));

        if (menuProduct.isPresent())
        {
            MenuProduct newMenuProduct = menuProduct.get();
            newMenuProduct.setIsInStock(true);

            return Optional.of(menuProductRepository.save(newMenuProduct));
        }

        return Optional.empty();
    }

    public Optional<MenuProduct> setIsInStockFalseById(Integer id) {

        Optional<MenuProduct> menuProduct = menuProductRepository.findById(id);

        if (menuProduct.isPresent())
        {
            MenuProduct newMenuProduct = menuProduct.get();
            newMenuProduct.setIsInStock(false);

            return Optional.of(menuProductRepository.save(newMenuProduct));
        }

        return Optional.empty();
    }

}
