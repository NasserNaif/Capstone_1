package com.example.capstoneweek3.Services;

import com.example.capstoneweek3.Models.MerStockModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service

public class MerStockService {
    ArrayList<MerStockModel> stocks = new ArrayList<>();

    public ArrayList<MerStockModel> getStocks() {
        return stocks;
    }

    public void addStock(MerStockModel newStock) {
        stocks.add(newStock);
    }

    public boolean updateStock(Integer id, MerStockModel newStock) {
        for (int i = 0; i < stocks.size(); i++) {
            if (Objects.equals(stocks.get(i).getId(), id)) {
                stocks.set(i, newStock);
                return true;
            }
        }
        return false;
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
