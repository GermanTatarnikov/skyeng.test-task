package ru.gtatarnikov.skyeng.testtask.model.mapper;

import org.mapstruct.Mapper;
import ru.gtatarnikov.skyeng.testtask.model.dto.MailingDto;
import ru.gtatarnikov.skyeng.testtask.model.entity.Mailing;

@Mapper(componentModel = "spring")
public interface MailingMapper {
    MailingDto toDto(Mailing mailing);

    Mailing fromDto(MailingDto dto);
}
