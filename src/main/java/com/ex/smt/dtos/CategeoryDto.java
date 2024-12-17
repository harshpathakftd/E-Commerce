package com.ex.smt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategeoryDto {
    private String categeoryId;
    private String categeoryName;
    private String categeoryDesc;
    private String categeoryCoverImage;
}
