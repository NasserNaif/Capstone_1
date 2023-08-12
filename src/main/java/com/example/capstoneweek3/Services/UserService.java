package com.example.capstoneweek3.Services;

import com.example.capstoneweek3.APIresponse.ApiResponse;
import com.example.capstoneweek3.Models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerStockService merStockService;
    private final ProductService productService;
    ArrayList<UserModel> users = new ArrayList<>();

    public ArrayList<UserModel> getUsers() {
        return users;
    }

    public void addUser(UserModel newUser) {
        users.add(newUser);
    }

    public boolean updateUser(Integer id, UserModel newUser) {
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getId(), id)) {
                users.set(i, newUser);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(Integer id) {
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getId(), id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public ApiResponse buyProduct(Integer userID, Integer productID, Integer merchantID) {
        boolean checkProduct_merchant = merStockService.checkProduct_merchant(productID, merchantID);
        boolean checkPrice;
        Double productPrice = productService.reduceBalance(productID);


        for (UserModel user : users
        ) {
            if (Objects.equals(user.getId(), userID)) {
                if (checkProduct_merchant) {
                    checkPrice = productService.checkPrice(productID, user.getBalance());
                    if (checkPrice) {
                        user.setBalance(user.getBalance() - productPrice);
                        merStockService.reduceStock(productID, merchantID);
                        return new ApiResponse("buy successfully");
                    } else {
                        return new ApiResponse("you have no enough cash to buy this product");
                    }
                } else {
                    return new ApiResponse("wrong product ID or merchant ID , Or you don't have enough stock");
                }
            }
        }

        return new ApiResponse("wrong user ID");
    }
}
