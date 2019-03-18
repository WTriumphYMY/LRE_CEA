package cn.edu.nwpu.lre_cea.domain;

import java.util.List;

/**
 * @ClassName ReactParameters
 * @Author: wkx
 * @Date: 2019/3/10 21:14
 * @Version: v1.0
 * @Description:
 */
public class ReactParameters {
    private List<String> reactTypes;//反应物类型
    private List<String> reactNames;//反应物名称
    private List<String> reactWeights;//反应物含量
    private List<String> reactTemperatures;//反应物温度
    private List<String> productList;//生成物列表

    public List<String> getReactTypes() {
        return reactTypes;
    }

    public void setReactTypes(List<String> reactTypes) {
        this.reactTypes = reactTypes;
    }

    public List<String> getReactNames() {
        return reactNames;
    }

    public void setReactNames(List<String> reactNames) {
        this.reactNames = reactNames;
    }

    public List<String> getReactWeights() {
        return reactWeights;
    }

    public void setReactWeights(List<String> reactWeights) {
        this.reactWeights = reactWeights;
    }

    public List<String> getReactTemperatures() {
        return reactTemperatures;
    }

    public void setReactTemperatures(List<String> reactTemperatures) {
        this.reactTemperatures = reactTemperatures;
    }

    public List<String> getProductList() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "ReactParameters{" +
                "reactTypes=" + reactTypes +
                ", reactNames=" + reactNames +
                ", reactWeights=" + reactWeights +
                ", reactTemperatures=" + reactTemperatures +
                ", productList=" + productList +
                '}';
    }
}
