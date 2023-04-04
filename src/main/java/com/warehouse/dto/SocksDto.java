package com.warehouse.dto;

import com.warehouse.Entity.Socks;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * A DTO for the {@link SocksDto} entity  {@link Socks}
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SocksDto {

    /**     * цвет носков     */
    String color;

    /**     * процентное содержание хлопка     */
    int cottonPart;
    /**     * Количество пар носков     */
    int quantity;
}
