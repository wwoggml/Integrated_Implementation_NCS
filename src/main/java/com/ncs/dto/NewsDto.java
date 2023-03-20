package com.ncs.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class NewsDto {

    String url;
    String title;
    String author;
    String date;
    ArrayList imageURL;
    String text;
}
