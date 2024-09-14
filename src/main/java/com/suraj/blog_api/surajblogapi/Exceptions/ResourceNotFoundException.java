package com.suraj.blog_api.surajblogapi.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String ResourceName;
    String feildName;
    long feildValue;
    public ResourceNotFoundException(String resourceName, String feildName, long feildValue) {
        super(String.format("%s not found with %s : %l ",resourceName,feildName,feildValue));
        ResourceName = resourceName;
        this.feildName = feildName;
        this.feildValue = feildValue;
    }
    

}
