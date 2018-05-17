package com.fuhaidev.app.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Response implements Serializable{
    ErrorResult err=new ErrorResult();
}
