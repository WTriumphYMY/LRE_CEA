package cn.edu.nwpu.lre_cea.service.impl;

import cn.edu.nwpu.lre_cea.domain.RocketCondition;
import org.junit.Test;

import java.util.Arrays;


public class ThermalResultGenerateServiceImplTest {

    @Test
    public void testGenerateThermalResult(){
        RocketCondition rocketCondition = new RocketCondition();
        rocketCondition.setProjectName("test");
        rocketCondition.setTemperature("3800");
        rocketCondition.setPressure(String.valueOf(Double.parseDouble("10")*10));
        rocketCondition.setEps("6.0");
        rocketCondition.setReactTypes(Arrays.asList("fuel", "oxid", "fuel"));
        rocketCondition.setReactNames(Arrays.asList("H2(L)", "O2(L)","C3H8(L)"));
        rocketCondition.setReactWeights(Arrays.asList("0.0337179", "0.0337179", "0.0337179"));
//        rocketCondition.setReactTemperatures(Arrays.asList("300", "300"));
//        rocketCondition.setProductList(Arrays.asList("CH3", "H2O(cr)", "CH3CHO,ethanal","CH3", "H2O(cr)", "CH3CHO,ethanal", "H2O(cr)", "CH3CHO,ethanal"));
        new ThermalResultGenerateServiceImpl().generateThermalResult(rocketCondition);

    }
}