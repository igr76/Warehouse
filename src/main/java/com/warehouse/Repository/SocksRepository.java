package com.warehouse.Repository;

import com.warehouse.Entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * Репозиторий учёта носков
 */
@Repository
public interface SocksRepository extends JpaRepository<Socks,Integer> {
    Socks findByColorAndAndCottonPart(String color,int cottonPart);
}
