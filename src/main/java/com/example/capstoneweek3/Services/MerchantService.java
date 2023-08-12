package com.example.capstoneweek3.Services;

import com.example.capstoneweek3.Models.CategoryModel;
import com.example.capstoneweek3.Models.MerStockModel;
import com.example.capstoneweek3.Models.MerchantModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class MerchantService {
    ArrayList<MerchantModel> merchants = new ArrayList<>();

    public ArrayList<MerchantModel> getAllMerchants() {
        return merchants;
    }

    public void addMerchant(MerchantModel newMerchant) {
        merchants.add(newMerchant);
    }

    public boolean updateMerchant(Integer id, MerchantModel newMerchant) {
        for (int i = 0; i < merchants.size(); i++) {
            if (Objects.equals(merchants.get(i).getId(), id)) {
                merchants.set(i, newMerchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(Integer id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (Objects.equals(merchants.get(i).getId(), id)) {
                merchants.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean checkMerchant(Integer id) {
        for (MerchantModel merchant : merchants) {
            if (Objects.equals(merchant.getId(), id)) {
                return true;
            }
        }
        return false;
    }
}
