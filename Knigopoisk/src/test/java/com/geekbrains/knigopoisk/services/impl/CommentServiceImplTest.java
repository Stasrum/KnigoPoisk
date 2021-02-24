package com.geekbrains.knigopoisk.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.knigopoisk.dto.BookDto;
import com.geekbrains.knigopoisk.dto.CommentDto;
import com.geekbrains.knigopoisk.dto.GenreDto;
import com.geekbrains.knigopoisk.dto.UserForAdminsEditDto;
import com.geekbrains.knigopoisk.entities.*;
import com.geekbrains.knigopoisk.repositories.CommentRepository;
import com.geekbrains.knigopoisk.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CommentServiceImplTest {

    @Autowired
    private MockMvc mockMvc;


    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Comment> comments;
    private User user;
    private Role roleAdmin, roleUser;
    private Book book;
    private Comment comment;
    private Publisher publisher;
    private CommentDto commentDto, updateCommentDto;


    @Test
    void createComment() throws Exception {
        //не работает предварительно из-за формата даты рождения
//        init();
//        commentDto.setId(null);
//        String content = mockMvc.perform(post("/api/v1/comments/create")
//                .content(objectMapper.writeValueAsString(commentDto))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        System.out.println(content);
    }


    @Test
    void getAllComments() throws Exception {

        mockMvc.perform(get("/api/v1/comments"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].text").value("Какая классная книга"));
    }

    @Test
    void getAllCommentsForBook() throws Exception {

        mockMvc.perform(get("/api/v1/comments/book/{id}",1L))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].text").value("Какая классная книга"));
    }



    @Test
    void updateComment() throws Exception {
        init();
        commentDto.setId(13L);
        commentDto.setText("13L");
        String content = mockMvc.perform(put("/api/v1/comments/update")
                .content(objectMapper.writeValueAsString(commentDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    void deleteComment() throws Exception {
        mockMvc.perform(delete("/api/v1/comments/delete/{id}", 13L))
                .andExpect(status().isOk());
    }


    void init() {
        OffsetDateTime created = OffsetDateTime.of(2000, 1, 1, 1, 1, 1, 1, ZoneOffset.UTC);
        OffsetDateTime updated = OffsetDateTime.of(2000, 1, 1, 1, 2, 1, 1, ZoneOffset.UTC);
        roleAdmin = new Role();
        roleAdmin.setId(1L);
        roleAdmin.setName("ADMIN");
        roleAdmin.setCreated(created);
        roleAdmin.setUpdated(updated);
        roleUser = new Role();
        roleUser.setId(2L);
        roleUser.setName("USER");
        roleUser.setCreated(created);
        roleUser.setUpdated(updated);
        user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setFirstName("alex");
        user.setLastName("alex");
        user.setAccountNotExpired(true);
        user.setAccountNotLocked(true);
        user.setCredentialsNotExpired(true);
        //user.setBirthDay(created.toLocalDate());
        user.setEmail("admin@adm.co");
        user.setPassword("$2a$10$5uECjAbKQ86L.cW8naqBRu2dIue7LiaN9AWxys/B7vmoKHBJDLu7O"); //123
        user.setCreated(created);
        user.setUpdated(updated);
        user.setRoles(Collections.singletonList(roleAdmin));

        publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("Эксмо — АСТ");
        publisher.setDescription("");
        Author author = new Author();
        author.setId(1L);
        author.setName("Ник Перумов");
        author.setDescription("");
        book = new Book();
        book.setId(1L);
        book.setCreated(created);
        book.setUpdated(updated);
        book.setTitle("Череп на рукаве");
        book.setAuthors(Arrays.asList(author));
        Language language = new Language();
        language.setId(1L);
        language.setName("Русский");
        language.setBooks(Collections.emptyList());
        book.setLanguages(Arrays.asList(language));
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Фантастика");
        genre.setBooks(Collections.emptyList());
        book.setGenres(Arrays.asList(genre));
        book.setPublisher(publisher);
        book.setDescription("Этот роман – фантастический боевик, написанный в лучших традициях жанра. Герой книги – Руслан Фатеев, уроженец планеты Новый Крым, – идеальный солдат грядущей войны с Чужими, жуткими и загадочными монстрами, остановить которых не в силах никто и ничто. Но это будет потом, а пока форма имперского десантника, до боли напоминающая форму солдат вермахта времен Второй мировой, ложится на его плечи как клеймо предателя, покинувшего свой дом и вступившего в ряды оккупантов. ISBN 978-5-699-11050-6");
        book.setYear(2002);
        book.setIsbn("9785699110506");

        comment = new Comment();
        comment.setId(1L);
        comment.setText("Очень очень интересно пишут");
        comment.setUser(user);
        comment.setBook(book);
        comment.setCreated(created);
        comment.setUpdated(updated);

        commentDto = new CommentDto(comment);

    }
}