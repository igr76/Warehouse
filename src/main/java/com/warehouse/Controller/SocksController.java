package com.warehouse.Controller;

import com.warehouse.Service.SocksService;
import com.warehouse.dto.SocksDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.IOException;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "Носки")
@Slf4j
public class SocksController {
    SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @Operation(summary = "Получить количество заявленных носков")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = Integer.class)))
                    }
            )
    })
    @GetMapping("/{color}/{operation}/{cottonPart}")
    public ResponseEntity<?> getSocks(@PathVariable(name = "color")
                                                @NotBlank(message = "Цвет носков не должен быть пустым")
                                                @Parameter(description = "Цвет носков",
                                                        example = "red") String color,
                                            @PathVariable(name = "operation")
                                                @NotBlank(message = "диапазон поиска не должен быть пустым")
                                                @Min(value = 1, message = "Идентификатор должен быть больше 0")
                                                @Parameter(description = "Содержание хлопка <>= одно из трех значений:",
                                                        example = "moreThan, lessThan, equal") String operation, @PathVariable(name = "cottonPart")
                                                @NotBlank(message = "% содержания хлопка не должен быть пустым")
                                                @Size(min = 1,max = 100, message = "Идентификатор должен быть от 0 до 100")
                                                @Parameter(description = "% содержания хлопка",
                                                        example = "60") int cottonPart) {
        return ResponseEntity.ok().body(socksService.getSocks(color,operation,cottonPart));
    }
    @Operation(summary = "Получить количество заявленных носков")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = String.class)))
                    }
            )
    })
    @GetMapping("/{color}")
    public ResponseEntity getSock(@PathVariable(name = "color") String color
                                      ) {
        return ResponseEntity.ok("hello"+ color);
    }
    @Operation(summary = "Добавить носки на склад")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = SocksDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Not Found",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            )
    })
    @PostMapping("/income")
    public void addSocks(
            @RequestBody @Valid SocksDto socksDto) throws IOException {
       socksService.addSocks(socksDto);
    }
    @Operation(summary = "Изъять носки со склада")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    array = @ArraySchema(schema = @Schema(implementation = SocksDto.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Not Found",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error",
                    content = {@Content(array = @ArraySchema(schema = @Schema()))}
            )
    })
    @PostMapping("/outcome")
    public ResponseEntity<SocksDto> updateSocks(
            @RequestBody @Valid SocksDto socksDto) throws IOException {
        return ResponseEntity.ok(socksService.updateSocks(socksDto));
    }
}
