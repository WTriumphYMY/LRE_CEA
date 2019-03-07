package cn.edu.nwpu.lre_cea.controller;

import cn.edu.nwpu.lre_cea.domain.ThermalResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CEACalculateController
 * @Author: wkx
 * @Date: 2019/3/7 22:40
 * @Version: v1.0
 * @Description: CEA计算Controller
 */
@RestController("/cea")
public class CEACalculateController {

    /**
     * 生成产物Controller
     * @param reacts 反应物列表
     * @return 生成物
     */
    @PostMapping("/product")
    public List<String> generateProduct(List<String> reacts){

        return null;
    }

    @PostMapping("/result")
    public ThermalResult generateResult(){

        return null;
    }
}
