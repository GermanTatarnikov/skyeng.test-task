package ru.gtatarnikov.skyeng.testtask.model.mapper;

import org.mapstruct.Mapper;
import ru.gtatarnikov.skyeng.testtask.model.dto.MovementDto;
import ru.gtatarnikov.skyeng.testtask.model.entity.Movement;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    MovementDto toDto(Movement movement);

    Movement fromDto(MovementDto dto);

    List<MovementDto> toDtoList(List<Movement> movementList);
}
