package cn.edu.nwpu.lre_cea.service;

import cn.edu.nwpu.lre_cea.domain.RocketCondition;

/**
 * @InterfaceName ThermalResultGenerateService
 * @Author: wkx
 * @Date: 2019/3/1 20:57
 * @Version: v1.0
 * @Description: 生成热力学计算结果
 */
public interface ThermalResultGenerateService {
    String inpFileModel = "problem   \n" +
            "      rocket  equilibrium  frozen  nfz=1  tcest,k={0}\n" +
            "  p,bar={1},\n" +
            "  pi/p={2},\n" +
            "  sup,ae/at={3},\n" +
            "react  \n";

    String generateThermalResult(RocketCondition rocketCondition);
}
