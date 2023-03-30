package com.warehouse.Entity;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**  Сущность носков   */
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "socks")
@Entity
public class Socks {
    /**     * id носков     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
   int id;
    /**     * цвет носков     */
    @Column(name = "color")
    String color;

    /**     * процентное содержание хлопка     */
    @Column(name = "cotton_part")
    int cottonPart;
    /**     * Количество пар носков     */
    @Column(name = "quantity")
    int quantity;
}
