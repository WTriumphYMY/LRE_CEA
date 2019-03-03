package cn.edu.nwpu.lre_cea.domain;

import lombok.Data;

import java.util.List;

/**
 * @ClassName RocketCondition
 * @Author: wkx
 * @Date: 2019/3/1 21:12
 * @Version: v1.0
 * @Description:
 */
@Data
public class RocketCondition {
    private String projectName;//项目名称（也是input文件名称）
    private double pressure;//燃烧室压强
    private double eps;//喷管膨胀比
    private double temperature;//预设燃烧室温度
    private String reactList;//反应物列表，包括标识，名称，含量和温度
    private String productList;//生成物列表
}
