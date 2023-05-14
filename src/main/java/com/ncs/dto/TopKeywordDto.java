package com.ncs.dto;

import com.ncs.entity.TopKeyword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDateTime;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TopKeywordDto {

    private String keyword;
    private int count;
    private LocalDateTime createdAt;

    public static ModelMapper modelMapper = new ModelMapper();

    public TopKeywordDto() {

    }

    public TopKeyword creatTopKeyword() {
        return modelMapper.map(this, TopKeyword.class);
    }

}
