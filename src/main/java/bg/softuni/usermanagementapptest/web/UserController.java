package bg.softuni.usermanagementapptest.web;

import bg.softuni.usermanagementapptest.model.dto.AddUserDto;
import bg.softuni.usermanagementapptest.model.dto.UserInfoDto;
import bg.softuni.usermanagementapptest.service.impl.UserServiceImpl;
import bg.softuni.usermanagementapptest.util.UserRepresentationModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepresentationModelAssembler userAssembler;

    public UserController(UserServiceImpl userService, UserRepresentationModelAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @PostMapping("/add")
    public ResponseEntity<EntityModel<UserInfoDto>> add(@RequestBody AddUserDto addUserDto){
        UserInfoDto addedUser = this.userService.add(addUserDto);

        EntityModel<UserInfoDto> entityModel = userAssembler.toModel(addedUser);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UserInfoDto>> getById(@PathVariable Long id){

        UserInfoDto userInfoDto = this.userService.getById(id);

        EntityModel<UserInfoDto> userEntity = userAssembler.toModel(userInfoDto);

        return ResponseEntity.ok(userEntity);
    }

    @GetMapping("/all")
    public ResponseEntity<CollectionModel<EntityModel<UserInfoDto>>> getAll(){

        List<UserInfoDto> all = this.userService.getAll();

        CollectionModel<EntityModel<UserInfoDto>> entityModels = CollectionModel.of(all
                        .stream()
                        .map(userAssembler::toModel).toList(),
                linkTo(methodOn(UserController.class).getAll()).withRel("users"));

        return ResponseEntity.ok(entityModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<EntityModel<UserInfoDto>> editUser(@PathVariable Long id, @RequestBody AddUserDto newUserInfo){

        UserInfoDto editedUser = this.userService.editUserById(id, newUserInfo);

        EntityModel<UserInfoDto> entityModel = userAssembler.toModel(editedUser);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){

        this.userService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
