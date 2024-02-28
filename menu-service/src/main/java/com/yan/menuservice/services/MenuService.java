package com.yan.menuservice.services;

import com.yan.menuservice.dto.MenuDto;
import com.yan.menuservice.exceptions.ResourceNotFoundException;
import com.yan.menuservice.models.Menu;
import com.yan.menuservice.repositories.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public Menu createMenu(MenuDto dto){
        Menu menu = new Menu();
        menu.setTitle(dto.getTitle());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        return menuRepository.save(menu);
    }

    public List<Menu> findAll(){
        List<Menu> menu = menuRepository.findAll();
        if (!menu.isEmpty()){
            return menu;
        } else {
            throw new ResourceNotFoundException("Пустое меню");
        }
    }

    public Menu findById(Long id){
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (menuOptional.isPresent()){
            return menuOptional.get();
        } else {
            throw new ResourceNotFoundException("Такого блюда нет в меню");
        }
    }
}
