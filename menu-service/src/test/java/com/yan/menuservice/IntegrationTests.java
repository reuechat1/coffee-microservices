package com.yan.menuservice;

import com.yan.menuservice.controllers.MenuController;
import com.yan.menuservice.models.Menu;
import com.yan.menuservice.repositories.MenuRepository;
import com.yan.menuservice.services.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuController.class)
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    @MockBean
    private MenuRepository menuRepository;

    @Test
    public void testFindAll() throws Exception {
        when(menuService.findAll()).thenReturn(Arrays.asList(new Menu(2L,"Блюдо 1", "Описание блюда", 100)));

        mockMvc.perform(get("/api/menu/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2L))
                .andExpect(jsonPath("$[0].title").value("Блюдо 1"))
                .andExpect(jsonPath("$[0].description").value("Описание блюда"))
                .andExpect(jsonPath("$[0].price").value(100));

        verify(menuService, times(1)).findAll();
    }

    @Test
    void testGetByIdEndpoint() throws Exception {
        Long id = 2L;
        Menu menu = new Menu(2L,"Блюдо 1", "Описание блюда", 100);
        Optional<Menu> optionalMenu = Optional.of(menu);
        when(menuRepository.findById(id)).thenReturn(optionalMenu);

        mockMvc.perform(get("/api/menu/findById?id=2"))
                .andExpect(status().isOk());

        verify(menuService, times(1)).findById(id);
    }
}
