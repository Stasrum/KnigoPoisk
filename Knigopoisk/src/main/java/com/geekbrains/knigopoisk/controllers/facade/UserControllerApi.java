package com.geekbrains.knigopoisk.controllers.facade;

import com.geekbrains.knigopoisk.dto.RoleDto;
import com.geekbrains.knigopoisk.dto.UserDetailsDto;
import com.geekbrains.knigopoisk.dto.UserPasswordDto;
import com.geekbrains.knigopoisk.dto.UserRegistrationDto;
import com.geekbrains.knigopoisk.exceptions.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RequestMapping("/api/v1")
public interface UserControllerApi {

    @GetMapping("/user/profile")
    UserDetailsDto getUserProfile(@NotNull Principal principal);

//    @GetMapping("/user")
//    List<UserDetailsDto> getAllUser();

    @GetMapping("/user/{id}")
    UserDetailsDto getUser(@PathVariable("id") @NotNull Long id);

//    @GetMapping("/user/{id}/delete")
//    void deleteUserById(@PathVariable("id") @NotNull Long id);

    @PostMapping("/users/register")
    UserDetailsDto register(@Valid @RequestBody UserRegistrationDto userRegistrationDto, BindingResult theBindingResult);

    @PostMapping("/user/update")
    UserDetailsDto update(@Valid @RequestBody UserDetailsDto userDetailsDto, BindingResult theBindingResult, @NotNull Principal principal);

    @PostMapping("/user/{id}/changePassword")
    ResponseEntity<ApiMessage> changePassword(@Valid @RequestBody UserPasswordDto userPasswordDto, BindingResult theBindingResult,
                                              @NotNull @PathVariable Long id);

    @GetMapping("/user/{id}/assignedRoles")
    List<RoleDto> getAssignedRoles(@NotNull @PathVariable Long id);

    @GetMapping("/user/{id}/unAssignedRoles")
    List<RoleDto> getUnAssignedRoles(@NotNull @PathVariable Long id);

    //=> AdminController
   /* @PostMapping("/user/{id}/addRole")
    List<RoleDto> addRole(@Valid @RequestBody RoleDto roleDto, BindingResult theBindingResult,
                        @NotNull @PathVariable Long id);

    @PostMapping("/user/{id}/removeRole")
    List<RoleDto> removeRole(@Valid @RequestBody RoleDto roleDto, BindingResult theBindingResult,
                        @NotNull @PathVariable Long id);*/
}
