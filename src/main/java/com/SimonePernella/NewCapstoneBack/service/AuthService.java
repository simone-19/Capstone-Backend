package com.SimonePernella.NewCapstoneBack.service;

import com.SimonePernella.NewCapstoneBack.dto.UserDTO;
import com.SimonePernella.NewCapstoneBack.dto.UserLoginDTO;
import com.SimonePernella.NewCapstoneBack.dto.UserUpdateInfoDTO;
import com.SimonePernella.NewCapstoneBack.entities.User;
import com.SimonePernella.NewCapstoneBack.exception.BadRequestException;
import com.SimonePernella.NewCapstoneBack.exception.UnauthorizedException;
import com.SimonePernella.NewCapstoneBack.repository.UserRepository;
import com.SimonePernella.NewCapstoneBack.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;
//    @Autowired
//    private CartService cartService;

    public String authenticateUser(UserLoginDTO body) {
        User user = userService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Email or password invalid.");
        }
    }


    public User save(UserDTO body) {
        userRepository.findByEmail(body.email()).ifPresent(a -> {
            throw new BadRequestException("The email " + a.getEmail() + " is alredy used.");
        });
        User user = new User();

        user.setEmail(body.email());
        user.setName(body.name());
        user.setSurname(body.surname());
        user.setPassword(bcrypt.encode(body.password()));
        user.setUsername(body.username());
        user.setAvatarUrl("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        userRepository.save(user);
//        Cart cart = cartService.createCart(user);
//        user.setCart(cart);
//        userRepository.save(user);
        return user;
    }

    public User update(long id, UserUpdateInfoDTO body) {
        User found = userService.getById(id);
        if (!body.email().isEmpty()) {
            found.setEmail(body.email());
        }
        if (!body.name().isEmpty()) {
            found.setName(body.name());
        }
        if (!body.surname().isEmpty()) {
            found.setSurname(body.surname());
        }
        if (!body.password().isEmpty()) {
            found.setPassword(bcrypt.encode(body.password()));
        }
        return userRepository.save(found);
    }

//    public User updateRole(long id, RoleUpdateDTO body) {
//        User found = userService.getById(id);
//        found.setRole(Role.valueOf(body.role()));
//        return userRepository.save(found);
//    }


}
