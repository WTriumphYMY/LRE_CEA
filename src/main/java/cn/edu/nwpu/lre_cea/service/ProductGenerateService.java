package cn.edu.nwpu.lre_cea.service;


import java.util.Map;

/**
 * @ClassName ProductGenerateService
 * @Author: wkx
 * @Date: 2019/2/28 19:10
 * @Version: v1.0
 * @Description: 产物生成服务接口
 */
public interface ProductGenerateService {
    /**
     *
     * @return 返回map，包括气相产物和凝相产物
     */
    Map generateProduct(String inputStr);
}
