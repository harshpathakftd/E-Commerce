package com.ex.smt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ex.smt.Service.UserService;
import com.ex.smt.dtos.Userdto;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Userdto> create(@RequestBody Userdto userdto){
        Userdto user = userService.createUser(userdto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Userdto>> getAll(
        @RequestParam(defaultValue = "0") int pageNumber ,
        @RequestParam(defaultValue = "0") int pageSize
    ){
        Page<Userdto> allUser = userService.getAllUser(pageNumber, pageSize);
        return new ResponseEntity<>(allUser , HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Userdto> getSingleEntity(@PathVariable String userId){
        Userdto getuserbyId = userService.getuserbyId(userId);
        return new ResponseEntity<>(getuserbyId , HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Userdto> deleteRecord(@PathVariable String userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Userdto> updateRecord(@RequestBody Userdto userdto, @PathVariable String userId){
        Userdto updaUserdto = userService.updaUserdto(userId, userdto);
        return new ResponseEntity<>(updaUserdto , HttpStatus.OK);
    }
}
