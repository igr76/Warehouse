package com.warehouse.Mapper;

import com.warehouse.Entity.Socks;
import com.warehouse.dto.SocksDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
/**
 * маппер для {@link Socks} готовый DTO {@link com.warehouse.dto.SocksDto}
 */
@Mapper(componentModel = "spring")
public interface  SocksMapper {

    @Mapping(target = "id", ignore = true)
   Socks toEntity(SocksDto socksDto);

    SocksDto toDto(Socks socks);
}
