package com.ncs.dto;

import com.ncs.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCondition {

    private Long id;

    private String name;

    private String nickname;

    private int age;

    private Status status;

    private Long zoneId;

}
