package bg.softuni.usermanagementapptest.util;

import bg.softuni.usermanagementapptest.model.dto.AddUserDto;
import bg.softuni.usermanagementapptest.model.dto.UserInfoDto;
import bg.softuni.usermanagementapptest.web.UserController;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<UserInfoDto, EntityModel<UserInfoDto>> {

    private final ModelMapper modelMapper;

    public UserRepresentationModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EntityModel<UserInfoDto> toModel(UserInfoDto entity) {

        AddUserDto addUserDto = modelMapper.map(entity, AddUserDto.class);

        return EntityModel.of(
                entity,
                linkTo(methodOn(UserController.class).getById(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("users"),
                linkTo(methodOn(UserController.class).editUser(entity.getId(), addUserDto)).withRel("update"),
                linkTo(methodOn(UserController.class).deleteUserById(entity.getId())).withRel("delete")
        );
    }
}
