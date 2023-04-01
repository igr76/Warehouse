package com.warehouse.service;


import com.warehouse.Entity.Socks;
import com.warehouse.Mapper.SocksMapper;
import com.warehouse.Repository.SocksRepository;
import com.warehouse.Service.Impl.SocksServiceImpl;
import com.warehouse.Service.SocksService;
import com.warehouse.dto.SocksDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Юнит тесты для сервиса
 */
@ExtendWith(MockitoExtension.class)
public class SocksServiceTest {
    @InjectMocks
    private SocksServiceImpl socksService;
    @Mock
    private  SocksRepository socksRepository;
    @Mock
    private SocksMapper socksMapper;
    @Mock
    private Socks socks;
    @Mock
    private SocksDto socksDto;
    private  Socks socks1;
    private  Socks socks2;
    private  SocksDto socksDto1;
@BeforeEach
    void init() {
        socks1 = new Socks(1,"red",58,67);
        socks2 = new Socks(2,"red",58,60);
        socksDto1 = new SocksDto("red",58,67);
    }
//    @Test
//    void  getSocksTest() {
//        List<Socks> socksList = List.of(socks1,socks2);
//        when(socksRepository.findByColor(any())).thenReturn(socksList);
//        assertThat(socksService.getSocks("red","moreThan",30)).isEqualTo(127);
//        verify(socksRepository, times(1)).findByColor(any());
//    }
    @Test
    void  addSocksTest() {
        when(socksRepository.findByColorAndAndCottonPart(any(),anyInt())).thenReturn(socks1);
        when(socksMapper.toEntity(any())).thenReturn(socks2);
        when(socksMapper.toDto(any())).thenReturn(socksDto1);
        when(socksRepository.save(any())).thenReturn(socks1);
        socksService.addSocks(socksDto1);
        assertEquals(socks1.getQuantity(), 67);
        verify(socksMapper, times(1)).toDto(any());
    }
    @Test
    void  updateSocksTest() {
        when(socksRepository.findByColorAndAndCottonPart(any(),anyInt())).thenReturn(socks1);
        when(socksMapper.toEntity(any())).thenReturn(socks2);
        when(socksMapper.toDto(any())).thenReturn(socksDto1);
        when(socksRepository.save(any())).thenReturn(socks1);
        assertThat(socksService.updateSocks(socksDto1)).isEqualTo(socksDto1);
        verify(socksRepository, times(1)).findByColorAndAndCottonPart(any(),anyInt());
    }
    @Test
    void  updateSocksNegativeTest() {
        when(socksRepository.findByColorAndAndCottonPart(any(),anyInt())).thenReturn(socks2);
        when(socksMapper.toEntity(any())).thenReturn(socks1);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> socksService.updateSocks(socksDto1));

    }
}
