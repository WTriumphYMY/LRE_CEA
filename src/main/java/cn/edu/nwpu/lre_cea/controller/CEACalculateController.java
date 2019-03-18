package cn.edu.nwpu.lre_cea.controller;

import cn.edu.nwpu.lre_cea.consts.ThermalDataConsts;
import cn.edu.nwpu.lre_cea.domain.ReactParameters;
import cn.edu.nwpu.lre_cea.domain.RocketCondition;
import cn.edu.nwpu.lre_cea.domain.ThermalResult;
import cn.edu.nwpu.lre_cea.service.ProductGenerateService;
import cn.edu.nwpu.lre_cea.service.ThermalResultGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName CEACalculateController
 * @Author: wkx
 * @Date: 2019/3/7 22:40
 * @Version: v1.0
 * @Description: CEA计算Controller
 */
@Controller
public class CEACalculateController {

    @Autowired
    ProductGenerateService productGenerateService;
    @Autowired
    ThermalResultGenerateService thermalResultGenerateService;

    @GetMapping("/CEAindex")
    public String showIndex() {
        return "CEAindex";
    }

    /**
     * 生成产物Controller
     * @param rocketCondition 热力学计算参数列表
     * @return 生成物
     */
    @ResponseBody
    @PostMapping("/cea/product")
    public Map generateProduct(RocketCondition rocketCondition){
        ReactParameters reactParameters = rocketCondition.getReactParameters();
        reactParameters.setReactTypes(reactParameters.getReactTypes().stream()
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList()));
        
        reactParameters.setReactNames(reactParameters.getReactNames().stream()
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList()));
        reactParameters.setReactWeights(reactParameters.getReactWeights().stream()
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList()));
        reactParameters.setReactTemperatures(reactParameters.getReactTemperatures().stream()
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList()));
        String inputReactString = "";
        for (String reactName : reactParameters.getReactNames()) {
            inputReactString += " " + reactName +" \n";
        }
        Map productMap = productGenerateService.generateProduct(inputReactString);
        return productMap;
    }

    @ResponseBody
    @PostMapping("/cea/result")
    public ThermalResult generateResult(RocketCondition rocketCondition){
        rocketCondition.getReactParameters().setReactTypes(rocketCondition.getReactParameters().getReactTypes().stream()
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList()));
        rocketCondition.getReactParameters().setReactNames(rocketCondition.getReactParameters().getReactNames().stream()
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList()));
        rocketCondition.getReactParameters().setReactWeights(rocketCondition.getReactParameters().getReactWeights().stream()
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList()));
        rocketCondition.getReactParameters().setReactTemperatures(rocketCondition.getReactParameters().getReactTemperatures().stream()
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList()));
        ThermalResult thermalResult = thermalResultGenerateService.generateThermalResult(rocketCondition);
        return thermalResult;
    }

    @ResponseBody
    @PostMapping("/cea/getreact")
    public Map<String, List<String>> getReacts(String partialName){
        if (partialName == null){
            partialName = "";
        }
        Map<String, List<String>> reactMap = new HashMap<>();
        List<String> b1Find = new ArrayList<>();
        List<String> b2Find = new ArrayList<>();
        List<String> b3Find = new ArrayList<>();
        for (String s : ThermalDataConsts.B1String) {
            if (s.indexOf(partialName) != -1){
                b1Find.add(s);
            }
        }
        for (String s : ThermalDataConsts.B2String) {
            if (s.indexOf(partialName) != -1){
                b2Find.add(s);
            }
        }
        for (String s : ThermalDataConsts.B3String) {
            if (s.indexOf(partialName) != -1){
                b3Find.add(s);
            }
        }
        reactMap.put("b1", b1Find);
        reactMap.put("b2", b2Find);
        reactMap.put("b3", b3Find);
        return reactMap;
    }
}
