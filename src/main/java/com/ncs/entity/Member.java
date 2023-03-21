package com.ncs.entity;

import com.ncs.dto.MemberSaveRequest;
import lombok.*;
import javax.persistence.*;
import com.ncs.entity.BaseEntity;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    private int age;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    private String description;

    public static Member from (MemberSaveRequest memberSaveRequest){
        return Member.builder()
                .name(memberSaveRequest.getName())
                .nickname(memberSaveRequest.getNickname())
                .age(memberSaveRequest.getAge())
                .status(Status.WAIT)
                .zone(Zone.builder().id(memberSaveRequest.getZoneId()).build())
                .description(memberSaveRequest.getDescription())
                .build();
    }
}