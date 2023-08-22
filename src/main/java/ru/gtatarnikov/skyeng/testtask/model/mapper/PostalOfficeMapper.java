package ru.gtatarnikov.skyeng.testtask.model.mapper;

import org.mapstruct.Mapper;
import ru.gtatarnikov.skyeng.testtask.model.dto.PostalOfficeDto;
import ru.gtatarnikov.skyeng.testtask.model.entity.PostalOffice;

@Mapper(componentModel = "spring")
public interface PostalOfficeMapper {
    PostalOfficeDto toDto(PostalOffice entity);

    PostalOffice fromDto(PostalOfficeDto dto);
}
