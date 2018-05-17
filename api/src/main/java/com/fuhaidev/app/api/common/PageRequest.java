package com.fuhaidev.app.api.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PageRequest extends Request{
    int pageNo;
    int pageSize;
}
