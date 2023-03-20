package com.ncs.dto;

import com.ncs.entity.Status;
import com.ncs.entity.Zone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponse {
    private Long id;

    private String name;

    private String nickname;

    private int age;

    private Status status;

    private Zone zone;

    private String description;

    private LocalDateTime createdAt;

    public static MemberResponse from(MemberDocument memberDocument){
        return MemberResponse.builder()
                .id(memberDocument.getId())
                .name(memberDocument.getName())
                .nickname(memberDocument.getNickname())
                .age(memberDocument.getAge())
                .status(memberDocument.getStatus())
                .zone(memberDocument.getZone())
                .description(memberDocument.getDescription())
                .createdAt(memberDocument.getCreatedAt())
                .build();
    }
}
