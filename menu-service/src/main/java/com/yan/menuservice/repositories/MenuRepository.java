package com.yan.menuservice.repositories;

import com.yan.menuservice.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
