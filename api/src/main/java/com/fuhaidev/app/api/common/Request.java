package com.fuhaidev.app.api.common;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class Request implements Serializable{
    @NotNull
    @NotEmpty
    String appId;
    @NotNull
    @NotEmpty
    String language;
    String traceId;
    @NotNull
    @NotEmpty
    String sign;
    @NotNull
    @NotEmpty
    String timeZone;
    @NotNull
    Long timestamp;
}
