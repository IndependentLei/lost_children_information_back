package com.lry.lostchildinfo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description :
 * @ClassName : EchartsVo
 * @Author : jdl
 * @Create : 2022-04-14 15:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EchartsVo {
    private List<String> threeDate;
    private List<Long> threeSum;
}
