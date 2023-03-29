package com.warehouse.Service.Impl;

import com.warehouse.Entity.Socks;
import com.warehouse.Mapper.SocksMapper;
import com.warehouse.Repository.SocksRepository;
import com.warehouse.Service.SocksService;
import com.warehouse.dto.SocksDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class SocksServiceImpl implements SocksService {
    Socks socks;
    SocksRepository socksRepository;
    SocksMapper socksMapper;

    @Override
    public Integer getSocks(String color, String operation, int cottonPart) {
        int howMany = 0;
        List<Socks> socks1 = socksRepository.findByColor(color);
        switch (operation) {
            case "moreThan":
                return  socks1.stream()
                        .filter(e -> e.getCottonPart() > cottonPart)
                        .mapToInt(e -> e.getQuantity())
                        .sum();
            case "lessThan":
                return socks1.stream()
                        .filter(e -> e.getCottonPart() < cottonPart)
                        .mapToInt(e -> e.getQuantity())
                        .sum();
            case "equal":
                return socks1.stream()
                        .filter(e -> e.getCottonPart() == cottonPart)
                        .mapToInt(e -> e.getQuantity())
                        .sum();
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }

    }

    @Override
    public SocksDto addSocks(SocksDto socksDto) {
        Socks socks1=socksMapper.toEntity(socksDto);
        Socks socks2 = socksRepository.findByColorAndAndCottonPart(socks1.getColor(), socks1.getCottonPart());
        if (socks2 != null) {
            socks1.setQuantity(socks1.getQuantity() + socks2.getQuantity());
            return socksMapper.toDto(socksRepository.save(socks1));
        }
        return socksMapper.toDto(socksRepository.save(socks2));
    }
}
