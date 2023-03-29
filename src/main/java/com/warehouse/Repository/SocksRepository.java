package com.warehouse.Repository;

import com.warehouse.Entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocksRepository extends JpaRepository<Socks,Integer> {
    List<Socks> findByColor(String color);
    Socks findByColorAndAndCottonPart(String color,int cottonPart);
}
