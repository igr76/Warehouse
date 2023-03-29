package com.warehouse.Service;

import com.warehouse.dto.SocksDto;

public interface SocksService {
    public Integer getSocks(String color,String operation,int cottonPart);
    public SocksDto addSocks(SocksDto socksDto);
}
