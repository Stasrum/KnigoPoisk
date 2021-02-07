package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.UserDto;
import com.geekbrains.knigopoisk.entities.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
    @Mapping(target = "username", source = "userName")
    @Mapping(target = "password", ignore = true)
    User getUserFromUserDto(UserDto userDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    void updateUserDetailsFromUserDto(UserDto userDto, @MappingTarget User user);

    @Mapping(target = "userName", source = "username")
    @Mapping(target = "password", ignore = true)
    UserDto getUserDtoFromUser(User user);

    @Mapping(target = "userName", source = "username")
    @Mapping(target = "password", ignore = true)
    List<UserDto> getUserDtoListFromUserList(List<User> users);
}

