package com.example.paris_janitor_api.dto;

import com.example.paris_janitor_api.framework.entity.PropertyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertySearchDto {

    public String type;
    public Long minBudget;
    public Long maxBudget;
    public String location;
    public PropertyStatus state;
}
