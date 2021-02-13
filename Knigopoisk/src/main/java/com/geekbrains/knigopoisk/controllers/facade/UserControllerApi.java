package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.RoleDto;
import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

public interface UserControllerApi {

    @GetMapping("/user/profile")
    UserDetailsDto getUserProfile(@NotNull Principal principal);

    @GetMapping("/users")
    List<UserDetailsDto> getAllUser();

    @GetMapping("/users/{id}")
    UserDetailsDto getUser(@PathVariable("id") @NotNull Long id);

//    @GetMapping("/users/{id}/delete")
//    void deleteUserById(@PathVariable("id") @NotNull Long id);

    @PostMapping("/users/register")
    void register(@Valid @RequestBody UserRegistrationDto userRegistrationDto, BindingResult theBindingResult);

    @PostMapping("/users/update")
    void update(@Valid @RequestBody UserDetailsDto userDetailsDto, BindingResult theBindingResult);

    @PostMapping("/users/{id}/changePassword")
    void changePassword(@Valid @RequestBody UserPasswordDto userPasswordDto, BindingResult theBindingResult,
                        @NotNull @PathVariable Long id);

    @GetMapping("/users/{id}/assignedRoles")
    List<RoleDto> getAssignedRoles(@NotNull @PathVariable Long id);

    @GetMapping("/users/{id}/unAssignedRoles")
    List<RoleDto> getUnAssignedRoles(@NotNull @PathVariable Long id);

    @PostMapping("/users/{id}/addRole")
    List<RoleDto> addRole(@Valid @RequestBody RoleDto roleDto, BindingResult theBindingResult,
                        @NotNull @PathVariable Long id);

    @PostMapping("/users/{id}/removeRole")
    List<RoleDto> removeRole(@Valid @RequestBody RoleDto roleDto, BindingResult theBindingResult,
                        @NotNull @PathVariable Long id);
}
