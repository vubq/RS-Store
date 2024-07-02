package com.vubq.rsstore.controllers;

import com.vubq.rsstore.config.security.jwt.JwtUtils;
import com.vubq.rsstore.dtos.InfoDto;
import com.vubq.rsstore.dtos.LoginDto;
import com.vubq.rsstore.dtos.TokenDto;
import com.vubq.rsstore.entities.Role;
import com.vubq.rsstore.entities.User;
import com.vubq.rsstore.enums.ERole;
import com.vubq.rsstore.services.RoleService;
import com.vubq.rsstore.services.UserService;
import com.vubq.rsstore.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Response authenticateUser(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return Response.build().ok().data(TokenDto.builder().token(jwt).build());
    }

    @GetMapping("/info")
    public Response getInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = this.userService.findByUserName(userDetails.getUsername()).orElse(null);
            if (user != null) {
                return Response.build().ok().data(
                        InfoDto.builder()
                                .roles(user.getRoles().stream().map(role -> role.getName().toString()).collect(Collectors.toList()))
                                .name(user.getFirstName() + user.getLastName())
                                .avatar(user.getAvatar())
                                .introduction("")
                                .build()
                );
            }
        }
        return Response.build().code(401);
    }

    @GetMapping("/test")
    public Response test() {
        User user = this.userService.findById("1").orElse(null);
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleService.findByName(ERole.ROLE_WAREHOUSE_MANAGEMENT).orElse(null));
        roles.add(this.roleService.findByName(ERole.ROLE_SALES).orElse(null));

        user.setRoles(roles);
        this.userService.save(user);
        return Response.build().ok().data("");
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
//        if (userService.existsByUserName(signUpRequest.getUserName())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body("Error: Username is already taken!");
//        }
//
//        if (userService.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body("Error: Email is already in use!");
//        }
//
//        // Create new user's account
//        User user = new User(signUpRequest.getUserName(),
//                signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminReorRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
}
