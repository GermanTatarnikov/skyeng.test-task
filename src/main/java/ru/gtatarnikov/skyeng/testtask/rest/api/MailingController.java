package ru.gtatarnikov.skyeng.testtask.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gtatarnikov.skyeng.testtask.model.dto.MailingDto;
import ru.gtatarnikov.skyeng.testtask.model.dto.MovementDto;

@Tag(name = "Почтовые отправления", description = "API для работы с почтовыми отправлениями")
public interface MailingController {
    @PostMapping("/register")
    @Operation(
            summary = "Зарегистрировать почтовое отправление.",
            description = "Регистрирует почтовое отправление в системе по переданной модели.\n " +
                    "Допустимые значения для типа (type) посылки: LETTER, PARCEL, PACKAGE, POSTCARD.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MailingDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    ResponseEntity<?> registerMailing(@RequestBody MailingDto mailing);

    @Operation(
            summary = "Отправить посылку в почтовое отделение.",
            description = "Отправляет посылку в почтовое отделение по уникальным идентификаторм посылки и отделения.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MailingDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @PutMapping("/{mailingId}/arriving_to/{postalOfficeId}")
    ResponseEntity<?> arrivedToWaypoint(@PathVariable Long mailingId, @PathVariable Long postalOfficeId);

    @Operation(
            summary = "Отправить посылку из почтового отделения.",
            description = "Посылка, найденная по уникальному идентифиактору, покидает почтовое отделение.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MailingDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @PutMapping("/{mailingId}/left_waypoint")
    ResponseEntity<?> leftWaypoint(@PathVariable Long mailingId);

    @Operation(
            summary = "Зарегистрировать почтовое отправление.",
            description = "Регистрирует почтовое отправление в системе по переданной модели.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MailingDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @PutMapping("/{mailingId}/receiving")
    ResponseEntity<?> receiving(@PathVariable Long mailingId);

    @Operation(
            summary = "Зарегистрировать почтовое отправление.",
            description = "Регистрирует почтовое отправление в системе по переданной модели.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MovementDto.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{mailingId}/history")
    ResponseEntity<?> getHistory(@PathVariable Long mailingId);
}
