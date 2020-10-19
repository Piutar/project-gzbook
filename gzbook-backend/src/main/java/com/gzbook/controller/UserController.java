package com.gzbook.controller;

import com.gzbook.model.user.User;
import com.gzbook.service.user.IUserService;
import com.gzbook.service.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("")
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }
//
//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
//        System.out.println("Creating User " + user.getUsername());
//        userService.saveUser(user);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @GetMapping("/findUserByEmail")
    public ResponseEntity<User> findByEmail(@RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUserByName")
    public ResponseEntity<Iterable<User>> findByNameContains(@RequestParam("name") String name,@RequestParam("idLogin") Long idLogin) {

        return new ResponseEntity<>(userService.findUserByName(name,idLogin), HttpStatus.OK);
    }

    @PostMapping("/combinePassword/{id}")
    public ResponseEntity<HttpStatus> combinePassword(@PathVariable Long id, @RequestBody String password) {
        User user = this.userService.findUserById(id);
//        String combinedPass = encoder.encode(password);
        if (encoder.matches(password, user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/changePassword/{id}")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody String newPassword) {
        User user = this.userService.findUserById(id);
        user.setPassword(encoder.encode(newPassword));
        this.userService.saveUser(user);
        return new ResponseEntity<>("Password changed", HttpStatus.OK);
    }

    @PostMapping("/exists")
    public ResponseEntity<Boolean> checkUserExist(@RequestBody String email) {
        return new ResponseEntity<>(userService.emailExist(email), HttpStatus.OK);
    }

}
