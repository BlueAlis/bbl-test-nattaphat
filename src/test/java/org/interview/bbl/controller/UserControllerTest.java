package org.interview.bbl.controller;

import org.interview.bbl.dto.response.UserResponse;
import org.interview.bbl.exception.GlobalExceptionHandler;
import org.interview.bbl.exception.UserNotFoundException;
import org.interview.bbl.model.enums.UserStatus;
import org.interview.bbl.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {UserController.class, GlobalExceptionHandler.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private UserService userService;

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setName("Nattaphat Yadfung");
        userResponse.setUsername("blueAlisz");
        userResponse.setEmail("blue@example.com");
        userResponse.setPhone("099999999");
        userResponse.setWebsite("blue.com");
        userResponse.setStatus(UserStatus.ACTIVE);
    }

    @Test
    @DisplayName("GET /users/{userId} - should return 200 OK with UserResponse when user exists")
    void getUserById_WhenUserExists_Returns200() throws Exception {
        // Arrange
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(userResponse);

        // Act & Assert
        mockMvc.perform(get("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Nattaphat Yadfung"))
                .andExpect(jsonPath("$.username").value("blueAlisz"))
                .andExpect(jsonPath("$.email").value("blue@example.com"))
                .andExpect(jsonPath("$.phone").value("099999999"))
                .andExpect(jsonPath("$.website").value("blue.com"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    @DisplayName("GET /users/{userId} - should return 404 Not Found when user does not exist")
    void getUserById_WhenUserNotFound_Returns404() throws Exception {
        // Arrange
        Long userId = 999L;
        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException(userId));

        // Act & Assert
        mockMvc.perform(get("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("User not found with id: " + userId));
    }
}

