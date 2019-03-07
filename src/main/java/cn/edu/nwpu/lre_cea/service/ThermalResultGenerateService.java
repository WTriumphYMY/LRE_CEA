package cn.edu.nwpu.lre_cea.service;

import cn.edu.nwpu.lre_cea.domain.RocketCondition;
import cn.edu.nwpu.lre_cea.domain.ThermalResult;

/**
 * @InterfaceName ThermalResultGenerateService
 * @Author: wkx
 * @Date: 2019/3/1 20:57
 * @Version: v1.0
 * @Description: 生成热力学计算结果
 */
public interface ThermalResultGenerateService {

    ThermalResult generateThermalResult(RocketCondition rocketCondition);
}
