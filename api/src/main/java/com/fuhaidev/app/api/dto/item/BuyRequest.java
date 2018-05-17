package com.fuhaidev.app.api.dto.item;

import com.fuhaidev.app.api.common.CommonRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by xumingxun on 2018/5/12.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BuyRequest extends CommonRequest{
    Integer itemId;
}
