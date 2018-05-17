package com.fuhaidev.app.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DataResponse extends Response{
    Object data;
}
