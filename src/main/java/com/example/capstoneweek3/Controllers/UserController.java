package com.example.capstoneweek3.Controllers;

import com.example.capstoneweek3.APIresponse.ApiResponse;
import com.example.capstoneweek3.Models.UserModel;
import com.example.capstoneweek3.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ArrayList<UserModel> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid UserModel newUser, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(newUser);
        return ResponseEntity.status(201).body(new ApiResponse("user added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid UserModel newUser, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = userService.updateUser(id, newUser);
        if (isUpdated) {
            return ResponseEntity.status(201).body(new ApiResponse("user updated"));

        } else {
            return ResponseEntity.status(400).body(new ApiResponse("wrong user ID"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(201).body(new ApiResponse("user deleted"));

        } else {
            return ResponseEntity.status(400).body(new ApiResponse("wrong user ID"));
        }
    }

    @PutMapping("/buy/{userid}/{productid}/{merchantid}")
    public ResponseEntity buyProduct(@PathVariable Integer userid, @PathVariable Integer productid, @PathVariable Integer merchantid) {
        ApiResponse result = userService.buyProduct(userid, productid, merchantid);

        if (result.getMessage().equals("buy successfully")) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(400).body(result);

        }
    }


}
