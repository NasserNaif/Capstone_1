package com.example.capstoneweek3.Services;

import com.example.capstoneweek3.Models.MerchantModel;
import com.example.capstoneweek3.Models.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


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

    public boolean checkPrice(Integer productID, Double money) {
        for (ProductModel prod : products
        ) {
            if (Objects.equals(prod.getId(), productID)) {
                if (prod.getPrice() <= money) {
                    return true;
                }
            }
        }

        return false;
    }

    public Double reduceBalance(Integer ID) {
        for (ProductModel prod : products
        ) {
            if (Objects.equals(prod.getId(), ID)) {
                return prod.getPrice();
            }
        }

        return 0.0;
    }

    public ArrayList<ProductModel> filter(Integer money) {
        ArrayList<ProductModel> result = new ArrayList<>();
        for (ProductModel prod : products
        ) {
            if (prod.getPrice() <= money) {
                result.add(prod);
            }
        }
        return result;
    }

    public ArrayList<ProductModel> category(Integer id) {
        ArrayList<ProductModel> result = new ArrayList<>();
        for (ProductModel prod : products
        ) {
            if (Objects.equals(prod.getCategoryID(), id)) {
                result.add(prod);
            }
        }
        return result;
    }
}
