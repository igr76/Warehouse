package com.warehouse.Service.Impl;

import com.warehouse.Entity.Socks;
import com.warehouse.Mapper.SocksMapper;
import com.warehouse.Repository.SocksRepository;
import com.warehouse.Service.SocksService;
import com.warehouse.dto.SocksDto;
import com.warehouse.loger.FormLogInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Service
public class SocksServiceImpl implements SocksService {
    Socks socks;
    @Autowired
    SocksRepository socksRepository;

    SocksMapper socksMapper;
    @Override
    public Integer getSocks(String color, String operation, int cottonPart) {
        log.info(FormLogInfo.getInfo());
        int howMany = 0;
      List<Socks> socks1 = socksRepository.findAll();
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
        log.info(FormLogInfo.getInfo());
        Socks socks1=socksMapper.toEntity(socksDto);
        Socks socks2 = socksRepository.findByColorAndAndCottonPart(socks1.getColor(), socks1.getCottonPart());
        if (socks2 != null) {
            socks1.setQuantity(socks1.getQuantity() + socks2.getQuantity());
            return socksMapper.toDto(socksRepository.save(socks1));
        }
        return socksMapper.toDto(socksRepository.save(socks1));
    }
    @Override
    public SocksDto updateSocks(SocksDto socksDto)  {
        log.info(FormLogInfo.getInfo());
        Socks socks1=socksMapper.toEntity(socksDto);
        Socks socks2 = socksRepository.findByColorAndAndCottonPart(socks1.getColor(), socks1.getCottonPart());
        if (socks2 == null) { return null;
        }
        if ((socks2.getQuantity() - socks1.getQuantity())>=0 ) {
            socks1.setQuantity(socks2.getQuantity() - socks1.getQuantity());
        }else throw new  RuntimeException();
        return socksMapper.toDto(socksRepository.save(socks1));
    }
}
