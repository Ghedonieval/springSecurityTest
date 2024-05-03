//package com.example.springSecurity2.Test2;
//
//import com.example.springSecurity2.Test2.Config.JwtUtils;
//import com.example.springSecurity2.Test2.dao.UserDao;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthenticationController {
//
//    private final AuthenticationManager authenticationManager;
//
//    private final UserDao userDao;
//
//    private final JwtUtils jwtUtils;
//
//    @PostMapping("/authentication")
//    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail() , request.getPassword())
//        );
//        final UserDetails user = userDao.findByEmail(request.getEmail());
//        if (user != null){
//            return ResponseEntity.ok(jwtUtils.generateToken(user));
//        }
//        return ResponseEntity.status(400).body("some error has occured");
//    }
//
//
//}
