package com.warehouse.Service;

import com.warehouse.dto.SocksDto;
/**
 * Сервис учёта носков
 */
public interface SocksService {
    public Integer getSocks(String color,String operation,int cottonPart);
    public void addSocks(SocksDto socksDto);
    public SocksDto updateSocks(SocksDto socksDto);
}
