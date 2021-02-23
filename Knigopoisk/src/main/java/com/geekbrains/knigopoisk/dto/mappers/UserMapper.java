package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.entities.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
    @Mapping(target = "username", source = "userName")
    @Mapping(target = "password", ignore = true)
    User getUserFromUserRegistrationDto(UserRegistrationDto userRegistrationDto);

    @Mapping(target = "userName", source = "username")
    UserForAdminsEditDto getUserForAdminsEditDtoFromUser(User userForAdminsEditDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromUserDetailsDto(UserDetailsDto userDetailsDto, @MappingTarget User user);

    @Mapping(target = "userName", source = "username")
    UserDetailsDto getUserDetailsDtoFromUser(User user);

    @Mapping(target = "userName", source = "username")
    List<UserDetailsDto> getUserDetailsDtoListFromUserList(List<User> users);
}

