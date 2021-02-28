package com.geekbrains.knigopoisk.dto.mappers;

import com.geekbrains.knigopoisk.dto.BookImageDto;
import com.geekbrains.knigopoisk.entities.BookImage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookImageMapper {
    BookImage getBookImageFromBookImageDto(BookImageDto bookImageDto);
    BookImageDto getBookImageDtoFromBookImage(BookImage bookImage);

    List<BookImage> getBookImageListFromBookImageDtoList(List<BookImageDto> bookImageDtoList);
    List<BookImageDto> getBookImageDtoListFromBookImageList(List<BookImage> bookImageList);

}
