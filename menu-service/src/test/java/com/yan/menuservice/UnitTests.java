package com.yan.menuservice;

import com.yan.menuservice.models.Menu;
import com.yan.menuservice.repositories.MenuRepository;
import com.yan.menuservice.services.MenuService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UnitTests {

    @InjectMocks
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;

    @Test
    void testFindAll() {
        List<Menu> expectedMenuList = Arrays.asList(new Menu(2L,"Блюдо 1", "Описание блюда", 100));
        when(menuRepository.findAll()).thenReturn(expectedMenuList);

        List<Menu> actualMenuList = menuRepository.findAll();

        assertEquals(expectedMenuList, actualMenuList);
        verify(menuRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Long id = 2L;
        Menu menu = new Menu(2L,"Блюдо 1", "Описание блюда", 100);
        Optional<Menu> optionalMenu = Optional.of(menu);
        when(menuRepository.findById(id)).thenReturn(optionalMenu);

        Menu result = menuService.findById(id);

        assertEquals(menu, result);
        verify(menuRepository, times(1)).findById(id);
    }
}
