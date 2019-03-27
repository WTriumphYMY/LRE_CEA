package cn.edu.nwpu.lre_cea.domain;

import javax.persistence.*;

/**
 * @ClassName OutputResult
 * @Author: wkx
 * @Date: 2019/3/24 14:04
 * @Version: v1.0
 * @Description: 数据库存储热力学结果对应实体类
 */
@Entity
@Table(name = "cea_output")
public class OutputResult {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Integer pk_id;

    @Column(name = "rocket_name")
    private String rocketName;

    @Column(name = "thermal_result")
    private String thermalResult;

    public Integer getPk_id() {
        return pk_id;
    }

    public void setPk_id(Integer pk_id) {
        this.pk_id = pk_id;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getThermalResult() {
        return thermalResult;
    }

    public void setThermalResult(String thermalResult) {
        this.thermalResult = thermalResult;
    }
}
