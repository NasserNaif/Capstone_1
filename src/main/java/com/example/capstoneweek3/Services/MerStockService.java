package com.example.capstoneweek3.Services;

import com.example.capstoneweek3.APIresponse.ApiResponse;
import com.example.capstoneweek3.Models.MerStockModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MerStockService {
    ArrayList<MerStockModel> stocks = new ArrayList<>();

    private final MerchantService merchantService;
    private final ProductService productService;

    public ArrayList<MerStockModel> getStocks() {
        return stocks;
    }

    public ApiResponse addStock(MerStockModel newStock) {
        if (merchantService.checkMerchant(newStock.getMerchantID())) {
            if (productService.checkProduct(newStock.getProductID())) {
                stocks.add(newStock);
                return new ApiResponse("stock added");
            }
            return new ApiResponse("wrong product ID");
        }
        return new ApiResponse("wrong merchant ID");
    }


    public ApiResponse updateStock(Integer id, MerStockModel newStock) {
        if (merchantService.checkMerchant(newStock.getMerchantID())) {
            if (productService.checkProduct(newStock.getProductID())) {
                for (int i = 0; i < stocks.size(); i++) {
                    if (Objects.equals(stocks.get(i).getId(), id)) {
                        stocks.set(i, newStock);
                        return new ApiResponse("stock updated");
                    }
                }
                return new ApiResponse("wrong stock ID");
            }
            return new ApiResponse("wrong product ID");
        }
        return new ApiResponse("wrong merchant ID");
    }

    public boolean deleteStock(Integer id) {
        for (int i = 0; i < stocks.size(); i++) {
            if (Objects.equals(stocks.get(i).getId(), id)) {
                stocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addMoreStocks(Integer productID, Integer merchantID, Integer amount) {
        for (MerStockModel stock : stocks
        ) {
            if (Objects.equals(stock.getProductID(), productID) && Objects.equals(stock.getMerchantID(), merchantID)) {
                stock.setStock(stock.getStock() + amount);
                return true;
            }
        }
        return false;
    }

    public boolean checkProduct_merchant(Integer productID, Integer merchantID) {
        for (MerStockModel stock : stocks
        ) {
            if (Objects.equals(stock.getMerchantID(), merchantID) && Objects.equals(stock.getProductID(), productID) && stock.getStock() > 0) {
                return true;
            }
        }
        return false;
    }

    public void reduceStock(Integer productID, Integer merchantID) {
        for (MerStockModel stock : stocks
        ) {
            if (Objects.equals(stock.getMerchantID(), merchantID) && Objects.equals(stock.getProductID(), productID)) {
                stock.setStock(stock.getStock() - 1);
            }
        }

    }


}
