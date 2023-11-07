package com.gamertx.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String fieldName;
    private Integer valueField;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer valueField) {
        super(String.format("%s No encontrado con : %s : '%s'",resourceName,fieldName,valueField));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.valueField = valueField;
    }
}