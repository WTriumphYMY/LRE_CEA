package cn.edu.nwpu.lre_cea.service.impl;

import cn.edu.nwpu.lre_cea.domain.RocketCondition;
import cn.edu.nwpu.lre_cea.service.ThermalResultGenerateService;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName ThermalResultGenerateServiceImpl
 * @Author: wkx
 * @Date: 2019/3/2 09:49
 * @Version: v1.0
 * @Description:
 */
public class ThermalResultGenerateServiceImpl implements ThermalResultGenerateService {
    @Override
    public String generateThermalResult(RocketCondition rocketCondition) {

        return null;
    }

    private String translateOutFile()throws Exception{
        File outFile = ResourceUtils.getFile("classpath:cea/StandardRocket.out");
        BufferedReader br = Files.newBufferedReader(outFile.toPath());

        return null;
    }
}
