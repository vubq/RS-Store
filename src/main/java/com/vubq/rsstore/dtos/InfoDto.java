package com.vubq.rsstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoDto {

    private List<String> roles;
    private String name;
    private String avatar;
    private String introduction;
}
