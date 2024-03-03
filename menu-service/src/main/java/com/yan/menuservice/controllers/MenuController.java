package com.yan.menuservice.controllers;

import com.yan.menuservice.dto.MenuDto;
import com.yan.menuservice.models.Menu;
import com.yan.menuservice.services.MenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/menu")
@AllArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/add")
    public Menu createMenu(@RequestBody MenuDto dto){
        log.info("Добавлено меню");
        return menuService.createMenu(dto);
    }

    @GetMapping("/findAll")
    public List<Menu> findAll(){
        return menuService.findAll();
    }

    @GetMapping("/findById")
    public Menu findById(@RequestParam Long id){
        return menuService.findById(id);
    }
}
