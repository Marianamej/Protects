package com.gamertx.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExist extends RuntimeException{
    private static final long serialVersionUID = 2L;

    private String resourceName;
    private String message;

    public ResourceAlreadyExist(String resourceName, String message) {
        super(String.format("%s El usuario : %s ",resourceName,message));
        this.resourceName = resourceName;
        this.message = message;
    }
}
