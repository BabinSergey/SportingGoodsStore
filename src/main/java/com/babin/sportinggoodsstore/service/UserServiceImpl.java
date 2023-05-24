package com.babin.sportinggoodsstore.service;

import com.babin.sportinggoodsstore.dao.UserRepository;
import com.babin.sportinggoodsstore.dto.UserDTO;
import com.babin.sportinggoodsstore.model.Role;
import com.babin.sportinggoodsstore.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(UserDTO userDTO) {
        // сравниваем пароли пользователя на правильность
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new RuntimeException("Неверный пароль!");
        }
        // если верный пароль собираем пользователя
        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();
        // сохраняем пользователя
        userRepository.save(user);
        return true;
    }

    // поиск и загрузка пользователя по имени
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден с таким именем!");
        }

        // вносим роль в список
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        // возвращаем пользователя
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }

    private UserDTO toDto(User user) { // выводим пользователей
        return UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name);
    }

    @Override
    @Transactional
    public void updateProfile(UserDTO dto) {
        User savedUser = userRepository.findFirstByName(dto.getUsername());
        if(savedUser == null){
            throw new RuntimeException("Не найден пользователь по имени " + dto.getUsername());
        }

        // проверки на наличие изменений
        boolean changed = false;
        if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
            savedUser.setPassword(passwordEncoder.encode(dto.getPassword()));
            changed = true;
        }
        if(!Objects.equals(dto.getEmail(), savedUser.getEmail())){
            savedUser.setEmail(dto.getEmail());
            changed = true;
        }
        if(changed){
            userRepository.save(savedUser);
        }
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
