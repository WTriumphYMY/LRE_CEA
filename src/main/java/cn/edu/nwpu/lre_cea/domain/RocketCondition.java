package cn.edu.nwpu.lre_cea.domain;

/**
 * @ClassName RocketCondition
 * @Author: wkx
 * @Date: 2019/3/1 21:12
 * @Version: v1.0
 * @Description:
 */

public class RocketCondition {
    private String projectName;//项目名称（也是input文件名称）
    private String pressure;//燃烧室压强
    private String eps;//喷管膨胀比
    private String temperature;//预设燃烧室温度
    private ReactParameters reactParameters;//反应物属性
//    private List<String> reactTypes;//反应物类型
//    private List<String> reactNames;//反应物名称
//    private List<String> reactWeights;//反应物含量
//    private List<String> reactTemperatures;//反应物温度
//    private List<String> productList;//生成物列表

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public ReactParameters getReactParameters() {
        return reactParameters;
    }

    public void setReactParameters(ReactParameters reactParameters) {
        this.reactParameters = reactParameters;
    }

    //    public List<String> getReactTypes() {
//        return reactTypes;
//    }
//
//    public void setReactTypes(List<String> reactTypes) {
//        this.reactTypes = reactTypes;
//    }
//
//    public List<String> getReactNames() {
//        return reactNames;
//    }
//
//    public void setReactNames(List<String> reactNames) {
//        this.reactNames = reactNames;
//    }
//
//    public List<String> getReactWeights() {
//        return reactWeights;
//    }
//
//    public void setReactWeights(List<String> reactWeights) {
//        this.reactWeights = reactWeights;
//    }
//
//    public List<String> getReactTemperatures() {
//        return reactTemperatures;
//    }
//
//    public void setReactTemperatures(List<String> reactTemperatures) {
//        this.reactTemperatures = reactTemperatures;
//    }
//
//    public List<String> getProductList() {
//        return productList;
//    }
//
//    public void setProductList(List<String> productList) {
//        this.productList = productList;
//    }

    @Override
    public String toString() {
        return "RocketCondition{" +
                "projectName='" + projectName + '\'' +
                ", pressure=" + pressure +
                ", eps=" + eps +
                ", temperature=" + temperature +
//                ", reactTypes=" + reactTypes +
//                ", reactNames=" + reactNames +
//                ", reactWeights=" + reactWeights +
//                ", reactTemperatures=" + reactTemperatures +
//                ", productList=" + productList +
                '}';
    }
}
