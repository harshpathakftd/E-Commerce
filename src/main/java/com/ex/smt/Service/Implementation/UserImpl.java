package com.ex.smt.Service.Implementation;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ex.smt.Exception.ResourceNotFoundException;
import com.ex.smt.Repository.UserRepo;
import com.ex.smt.Service.UserService;
import com.ex.smt.dtos.Userdto;
import com.ex.smt.entities.User;
import com.ex.smt.Config.AppConfig;

@Service
public class UserImpl implements UserService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Userdto createUser(Userdto userDto) {
       String userId = UUID.randomUUID().toString();
       userDto.setUserId(userId);
       User user = mapper.map(userDto, User.class);
       User save = userRepo.save(user);
       return mapper.map(save, Userdto.class);
    }

    @Override
    public Userdto updaUserdto(String userId, Userdto userDto) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(AppConfig.notFoudMessage));
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setActive(true);
        User save = userRepo.save(user);
        return mapper.map(save, Userdto.class);
    }

    @Override
    public Userdto getuserbyId(String userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(AppConfig.notFoudMessage));
        return mapper.map(user, Userdto.class);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException(AppConfig.notFoudMessage));
        userRepo.delete(user);
    }

    @Override
    public Page<Userdto> getAllUser(int pageNumber, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepo.findAll(pageable);
        Page<Userdto> mapData = userPage.map(user -> mapper.map(user, Userdto.class));
        return mapData;
    }
    
}
