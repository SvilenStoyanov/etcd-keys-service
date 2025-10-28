package com.svistoyanov.eks.api.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.NONE,
        getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        setterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY
)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        property = "@class"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EksErrorDto.class, name = "PksErrorDto")
})
public abstract class AbstractDto implements Serializable {

    protected AbstractDto() {
        // POJO
    }
}
