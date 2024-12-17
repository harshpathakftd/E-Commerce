package com.ex.smt.Service;

import org.springframework.data.domain.Page;
import com.ex.smt.dtos.Userdto;


public interface UserService {
    Userdto createUser(Userdto userDto);
    Userdto updaUserdto(String userId , Userdto userDto);
    Userdto getuserbyId(String userId);
    // Userdto getAllUser();
    Page<Userdto> getAllUser(int pageNumber , int pageSize);
    void deleteUser(String userId);
}
