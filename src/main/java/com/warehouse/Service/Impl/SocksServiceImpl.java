package com.warehouse.Service.Impl;

import com.warehouse.Entity.Socks;
import com.warehouse.Mapper.SocksMapper;
import com.warehouse.Repository.SocksRepository;
import com.warehouse.Service.SocksService;
import com.warehouse.dto.SocksDto;
import com.warehouse.loger.FormLogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * Сервис учёта носков
 */
@Slf4j
@Service
public class SocksServiceImpl implements SocksService {
   private SocksRepository socksRepository;
    private SocksMapper socksMapper;
    public SocksServiceImpl( SocksRepository socksRepository, SocksMapper socksMapper) {
        this.socksRepository = socksRepository;
        this.socksMapper = socksMapper;
    }
    /**
     * Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.
     */
    @Override
    public Integer getSocks(String color, String operation, int cottonPart) {
        log.info(FormLogInfo.getInfo());
        switch (operation) {
            case "moreThan":
                return  socksRepository.findCottonPartMoreThan(cottonPart,color).stream()
                        .mapToInt(e -> e.getQuantity())
                        .sum();
            case "lessThan":
                return socksRepository.findCottonPartLessThan(cottonPart,color).stream()
                        .mapToInt(e -> e.getQuantity())
                        .sum();
            case "equal":
                return socksRepository.findCottonPartEqual(cottonPart,color).stream()
                        .filter(e -> e.getCottonPart() == cottonPart)
                        .mapToInt(e -> e.getQuantity())
                        .sum();
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
    }
    /**
     * Регистрирует приход носков на склад
     */
    @Override
    public void addSocks(SocksDto socksDto) {
        log.info(FormLogInfo.getInfo());
        Socks socks1=socksMapper.toEntity(socksDto);
        Socks socks2 = socksRepository.findByColorAndAndCottonPart(socks1.getColor(), socks1.getCottonPart());
        if (socks2 != null) {
            socks1.setQuantity(socks1.getQuantity() + socks2.getQuantity());
             socksMapper.toDto(socksRepository.save(socks1));
        }else         socksMapper.toDto(socksRepository.save(socks1));
    }
    /**
     * Регистрирует отпуск носков со склада.
     */
    @Override
    public SocksDto updateSocks(SocksDto socksDto)  {
        log.info(FormLogInfo.getInfo());
        Socks socks1=socksMapper.toEntity(socksDto);
        Socks socks2 = socksRepository.findByColorAndAndCottonPart(socks1.getColor(), socks1.getCottonPart());
        if (socks2 == null) { return null;
        }
        if ((socks2.getQuantity() - socks1.getQuantity())>=0 ) {
            socks1.setQuantity(socks2.getQuantity() - socks1.getQuantity());
        }else throw new  RuntimeException("На складе нет такого количества носков");
        return socksMapper.toDto(socksRepository.save(socks1));
    }
}
