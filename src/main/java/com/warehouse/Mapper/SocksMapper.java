package com.warehouse.Mapper;

import com.warehouse.Entity.Socks;
import com.warehouse.dto.SocksDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
@Mapper
public interface  SocksMapper {

    @Mapping(target = "id", ignore = true)
   Socks toEntity(SocksDto socksDto);

    SocksDto toDto(Socks socks);
}
