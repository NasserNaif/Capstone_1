package com.example.capstoneweek3.Services;

import com.example.capstoneweek3.Models.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service

public class UserService {
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
}
