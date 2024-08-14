package bg.softuni.usermanagementapptest.service.impl;

import bg.softuni.usermanagementapptest.model.UserEntity;
import bg.softuni.usermanagementapptest.model.dto.AddUserDto;
import bg.softuni.usermanagementapptest.model.dto.UserInfoDto;
import bg.softuni.usermanagementapptest.repository.UserRepository;
import bg.softuni.usermanagementapptest.service.UserService;
import bg.softuni.usermanagementapptest.service.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserInfoDto add(AddUserDto addUserDto) {

        UserEntity userEntity = this.modelMapper.map(addUserDto, UserEntity.class);

        return this.modelMapper.map(this.userRepository.save(userEntity), UserInfoDto.class);
    }

    @Override
    public UserInfoDto getById(Long id) {

        UserEntity userEntity = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No such user exist"));

        return modelMapper.map(userEntity, UserInfoDto.class);


    }

    @Override
    public List<UserInfoDto> getAll() {

        return this.userRepository.findAll().stream().map(user -> modelMapper.map(user, UserInfoDto.class)).toList();
    }

    @Override
    public UserInfoDto editUserById(Long id, AddUserDto newUserInfo) {

        UserEntity userEntity = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No such user exist"));

        userEntity.setFirstName(newUserInfo.getFirstName());
        userEntity.setLastName(newUserInfo.getLastName());
        userEntity.setPhoneNumber(newUserInfo.getPhoneNumber());

        return modelMapper.map(this.userRepository.save(userEntity), UserInfoDto.class);
    }

    @Override
    public void deleteById(Long id) {

        this.userRepository.deleteById(id);
    }
}
