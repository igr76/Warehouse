package com.warehouse.Repository;

import com.warehouse.Entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий учёта носков
 */
@Repository
public interface SocksRepository extends JpaRepository<Socks,Integer> {
    Socks findByColorAndAndCottonPart(String color,int cottonPart);
    @Query(nativeQuery = true, value = "SELECT * FROM socks WHERE color = :color1 AND cotton_part > :cottonPart1")
    List<Socks> findCottonPartMoreThan(@Param("cottonPart1")int cottonPart1,@Param("color1")String color);

    @Query(nativeQuery = true, value = "SELECT * FROM socks WHERE color = :color1 AND cotton_part < :cottonPart1")
    List<Socks> findCottonPartLessThan(@Param("cottonPart1")int cottonPart1,@Param("color1")String color);

    @Query(nativeQuery = true, value = "SELECT * FROM socks WHERE color = :color1 AND cotton_part = :cottonPart1")
    List<Socks> findCottonPartEqual(@Param("cottonPart1")int cottonPart1,@Param("color1")String color);

}
