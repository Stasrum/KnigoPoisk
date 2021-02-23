package com.geekbrains.knigopoisk.services.impl;

import com.geekbrains.knigopoisk.dto.CommentDto;
import com.geekbrains.knigopoisk.entities.*;
import com.geekbrains.knigopoisk.repositories.CommentRepository;
import com.geekbrains.knigopoisk.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentServiceImpl commentService;
    @InjectMocks
    private UserServiceImpl userService;

    private List<Comment> comments = null;
    private User user;
    private Role role;
    private Book book;
    private Comment comment = null;
    private Comment comment1 = null;
    private Comment comment2 = null;

    void init() {

        OffsetDateTime created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        OffsetDateTime updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);
        role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN");
        role.setCreated(created);
        role.setUpdated(updated);
        user = new User();
        user.setId(1L);
        user.setUsername("Nick");
        user.setFirstName("Nick");
        user.setLastName("Vasko");
        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setCredentialsNotExpired(true);
        user.setBirthDay(created.toLocalDate());
        user.setEmail("nick@nick.com");
        user.setPassword("$2a$10$5uECjAbKQ86L.cW8naqBRu2dIue7LiaN9AWxys/B7vmoKHBJDLu7O"); //123
        user.setCreated(created);
        user.setUpdated(updated);
        user.setRoles(Collections.singletonList(role));

        book = new Book();
        book.setId(1L);
        book.setCreated(created);
        book.setUpdated(updated);
        book.setTitle("Одноэтажная Америка");
        book.setAuthors(Arrays.asList(new Author("И. Ильф", ""), new Author("Е. Петров", "")));
        book.setLanguages(Arrays.asList(new Language("Русский", Collections.emptyList()), new Language("Украинский", Collections.emptyList())));
        book.setGenres(Collections.singletonList(new Genre("Документальный", "", Collections.emptyList())));
        book.setPublisher(new Publisher("МИФ", ""));
        book.setDescription("Веселая книга");
        book.setYear(1928);
        book.setIsbn("1234567890123");

        comment = new Comment();
        comment.setId(1L);
        comment.setText("Очень интересно о чем они пишут");
        comment.setUser(user);
        comment.setBook(book);
        comment.setCreated(created);
        comment.setUpdated(updated);

        comment1 = new Comment();
        comment1.setId(1L);
        comment1.setText("Больше интересного!!!");
        comment1.setUser(user);
        comment1.setCreated(created);
        comment1.setUpdated(updated);
        comment1.setBook(book);

        comment2 = new Comment();
        comment2.setId(1L);
        comment2.setText("Ye Очень интересно о чем они пишут");
        comment2.setUser(user);
        comment2.setBook(book);
        comment2.setCreated(created);
        comment2.setUpdated(updated);

        comments = Arrays.asList(comment, comment1);
    }

    @Test
    void getAll() {
        init();
        when(commentRepository.findAll()).thenReturn(comments);

        List<CommentDto> expected = commentService.getAll();
        List<CommentDto> current = comments.stream().map(CommentDto::new).collect(Collectors.toList());
        System.out.println(current);
        assertNotNull(expected);
        assertEquals(expected, current);
    }

    @Test
    void update() {
        init();
        when(commentRepository.findById(any())).thenReturn(Optional.of(comment));
        when(commentRepository.save(any())).thenReturn(comment);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        CommentDto expected = commentService.update(new CommentDto(comment));
        CommentDto current = new CommentDto(comment);
        assertEquals(expected, current);
    }

}