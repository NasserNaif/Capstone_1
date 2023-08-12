package com.example.capstoneweek3.Services;

import com.example.capstoneweek3.Models.MerchantModel;
import com.example.capstoneweek3.Models.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {


    //   private final CategoryService categoryService;
    ArrayList<ProductModel> products = new ArrayList<>();

    public ArrayList<ProductModel> getProducts() {
        return products;
    }

    public void addProduct(ProductModel newProduct) {
        products.add(newProduct);
    }

    public boolean updateProduct(Integer id, ProductModel newProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getId(), id)) {
                products.set(i, newProduct);
                return true;
            }
        }
        return false;
    }


    public boolean deleteProduct(Integer id) {
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getId(), id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean checkProduct(Integer id) {
        for (ProductModel product : products) {
            if (Objects.equals(product.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}
