package bg.softuni.usermanagementapptest.service;

import bg.softuni.usermanagementapptest.model.dto.AddUserDto;
import bg.softuni.usermanagementapptest.model.dto.UserInfoDto;

import java.util.List;

public interface UserService {
    UserInfoDto add(AddUserDto addUserDto);

    UserInfoDto getById(Long id);

    List<UserInfoDto> getAll();

    UserInfoDto editUserById(Long id, AddUserDto newUserInfo);

    void deleteById(Long id);
}
