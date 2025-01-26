package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.UserDTO;
import deim.urv.cat.homework2.controller.UserForm;

public interface UserService {
    
    public UserDTO findUserByEmail(String email);
    public boolean addUser(UserForm user);
    public boolean checkPassword(String email, String password);
}
