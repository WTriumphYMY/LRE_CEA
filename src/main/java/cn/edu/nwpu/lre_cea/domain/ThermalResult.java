package cn.edu.nwpu.lre_cea.domain;

import java.util.Arrays;

/**
 * @ClassName ThermalResult
 * @Author: wkx
 * @Date: 2019/3/6 20:53
 * @Version: v1.0
 * @Description: 热力学计算结果实体类
 */
public class ThermalResult {
    String[] eq_pivsp = new String[5]; //压强比(平衡流)
    String[] eq_pressure = new String[5]; //压强(平衡流)
    String[] eq_temperature = new String[5]; //温度(平衡流)
    String[] eq_rho = new String[5]; //密度(平衡流)
    String[] eq_h = new String[5]; //焓(平衡流)
    String[] eq_u = new String[5];//内能(平衡流)
    String[] eq_g = new String[5]; //吉布斯能(平衡流)
    String[] eq_s = new String[5]; //熵(平衡流)
    String[] eq_mole = new String[5]; //摩尔质量(平衡流)
    String[] eq_dvdp = new String[5]; //偏导数(平衡流)
    String[] eq_dvdt = new String[5]; //偏导数(平衡流)
    String[] eq_cp = new String[5]; //定压指数(平衡流)
    String[] eq_gamma = new String[5]; //比热比(平衡流)
    String[] eq_sonic = new String[5]; //声速(平衡流)
    String[] eq_mach = new String[5]; //马赫数(平衡流)
    String[] eq_eps = new String[4]; //面积比(平衡流)
    String[] eq_cstar = new String[4]; //特征速度(平衡流)
    String[] eq_cf = new String[4]; //推理系数(平衡流)
    String[] eq_ivac = new String[4]; //真空比冲(平衡流)
    String[] eq_isp = new String[4]; //比冲(平衡流)

    String[] fr_pivsp = new String[5]; //压强比(冻结流)
    String[] fr_pressure = new String[5]; //压强(冻结流)
    String[] fr_temperature = new String[5]; //温度(冻结流)
    String[] fr_rho = new String[5]; //密度(冻结流)
    String[] fr_h = new String[5]; //焓(冻结流)
    String[] fr_u = new String[5];//内能(冻结流)
    String[] fr_g = new String[5]; //吉布斯能(冻结流)
    String[] fr_s = new String[5]; //熵(冻结流)
    String[] fr_mole = new String[5]; //摩尔质量(冻结流)
    String[] fr_cp = new String[5]; //定压指数(冻结流)
    String[] fr_gamma = new String[5]; //比热比(冻结流)
    String[] fr_sonic = new String[5]; //声速(冻结流)
    String[] fr_mach = new String[5]; //马赫数(冻结流)
    String[] fr_eps = new String[4]; //面积比(冻结流)
    String[] fr_cstar = new String[4]; //特征速度(冻结流)
    String[] fr_cf = new String[4]; //推理系数(冻结流)
    String[] fr_ivac = new String[4]; //真空比冲(冻结流)
    String[] fr_isp = new String[4]; //比冲(冻结流)

    public String[] getEq_pivsp() {
        return eq_pivsp;
    }

    public void setEq_pivsp(String[] eq_pivsp) {
        this.eq_pivsp = eq_pivsp;
    }

    public String[] getEq_pressure() {
        return eq_pressure;
    }

    public void setEq_pressure(String[] eq_pressure) {
        this.eq_pressure = eq_pressure;
    }

    public String[] getEq_temperature() {
        return eq_temperature;
    }

    public void setEq_temperature(String[] eq_temperature) {
        this.eq_temperature = eq_temperature;
    }

    public String[] getEq_rho() {
        return eq_rho;
    }

    public void setEq_rho(String[] eq_rho) {
        this.eq_rho = eq_rho;
    }

    public String[] getEq_h() {
        return eq_h;
    }

    public void setEq_h(String[] eq_h) {
        this.eq_h = eq_h;
    }

    public String[] getEq_u() {
        return eq_u;
    }

    public void setEq_u(String[] eq_u) {
        this.eq_u = eq_u;
    }

    public String[] getEq_g() {
        return eq_g;
    }

    public void setEq_g(String[] eq_g) {
        this.eq_g = eq_g;
    }

    public String[] getEq_s() {
        return eq_s;
    }

    public void setEq_s(String[] eq_s) {
        this.eq_s = eq_s;
    }

    public String[] getEq_mole() {
        return eq_mole;
    }

    public void setEq_mole(String[] eq_mole) {
        this.eq_mole = eq_mole;
    }

    public String[] getEq_dvdp() {
        return eq_dvdp;
    }

    public void setEq_dvdp(String[] eq_dvdp) {
        this.eq_dvdp = eq_dvdp;
    }

    public String[] getEq_dvdt() {
        return eq_dvdt;
    }

    public void setEq_dvdt(String[] eq_dvdt) {
        this.eq_dvdt = eq_dvdt;
    }

    public String[] getEq_cp() {
        return eq_cp;
    }

    public void setEq_cp(String[] eq_cp) {
        this.eq_cp = eq_cp;
    }

    public String[] getEq_gamma() {
        return eq_gamma;
    }

    public void setEq_gamma(String[] eq_gamma) {
        this.eq_gamma = eq_gamma;
    }

    public String[] getEq_sonic() {
        return eq_sonic;
    }

    public void setEq_sonic(String[] eq_sonic) {
        this.eq_sonic = eq_sonic;
    }

    public String[] getEq_mach() {
        return eq_mach;
    }

    public void setEq_mach(String[] eq_mach) {
        this.eq_mach = eq_mach;
    }

    public String[] getEq_eps() {
        return eq_eps;
    }

    public void setEq_eps(String[] eq_eps) {
        this.eq_eps = eq_eps;
    }

    public String[] getEq_cstar() {
        return eq_cstar;
    }

    public void setEq_cstar(String[] eq_cstar) {
        this.eq_cstar = eq_cstar;
    }

    public String[] getEq_cf() {
        return eq_cf;
    }

    public void setEq_cf(String[] eq_cf) {
        this.eq_cf = eq_cf;
    }

    public String[] getEq_ivac() {
        return eq_ivac;
    }

    public void setEq_ivac(String[] eq_ivac) {
        this.eq_ivac = eq_ivac;
    }

    public String[] getEq_isp() {
        return eq_isp;
    }

    public void setEq_isp(String[] eq_isp) {
        this.eq_isp = eq_isp;
    }

    public String[] getFr_pivsp() {
        return fr_pivsp;
    }

    public void setFr_pivsp(String[] fr_pivsp) {
        this.fr_pivsp = fr_pivsp;
    }

    public String[] getFr_pressure() {
        return fr_pressure;
    }

    public void setFr_pressure(String[] fr_pressure) {
        this.fr_pressure = fr_pressure;
    }

    public String[] getFr_temperature() {
        return fr_temperature;
    }

    public void setFr_temperature(String[] fr_temperature) {
        this.fr_temperature = fr_temperature;
    }

    public String[] getFr_rho() {
        return fr_rho;
    }

    public void setFr_rho(String[] fr_rho) {
        this.fr_rho = fr_rho;
    }

    public String[] getFr_h() {
        return fr_h;
    }

    public void setFr_h(String[] fr_h) {
        this.fr_h = fr_h;
    }

    public String[] getFr_u() {
        return fr_u;
    }

    public void setFr_u(String[] fr_u) {
        this.fr_u = fr_u;
    }

    public String[] getFr_g() {
        return fr_g;
    }

    public void setFr_g(String[] fr_g) {
        this.fr_g = fr_g;
    }

    public String[] getFr_s() {
        return fr_s;
    }

    public void setFr_s(String[] fr_s) {
        this.fr_s = fr_s;
    }

    public String[] getFr_mole() {
        return fr_mole;
    }

    public void setFr_mole(String[] fr_mole) {
        this.fr_mole = fr_mole;
    }

    public String[] getFr_cp() {
        return fr_cp;
    }

    public void setFr_cp(String[] fr_cp) {
        this.fr_cp = fr_cp;
    }

    public String[] getFr_gamma() {
        return fr_gamma;
    }

    public void setFr_gamma(String[] fr_gamma) {
        this.fr_gamma = fr_gamma;
    }

    public String[] getFr_sonic() {
        return fr_sonic;
    }

    public void setFr_sonic(String[] fr_sonic) {
        this.fr_sonic = fr_sonic;
    }

    public String[] getFr_mach() {
        return fr_mach;
    }

    public void setFr_mach(String[] fr_mach) {
        this.fr_mach = fr_mach;
    }

    public String[] getFr_eps() {
        return fr_eps;
    }

    public void setFr_eps(String[] fr_eps) {
        this.fr_eps = fr_eps;
    }

    public String[] getFr_cstar() {
        return fr_cstar;
    }

    public void setFr_cstar(String[] fr_cstar) {
        this.fr_cstar = fr_cstar;
    }

    public String[] getFr_cf() {
        return fr_cf;
    }

    public void setFr_cf(String[] fr_cf) {
        this.fr_cf = fr_cf;
    }

    public String[] getFr_ivac() {
        return fr_ivac;
    }

    public void setFr_ivac(String[] fr_ivac) {
        this.fr_ivac = fr_ivac;
    }

    public String[] getFr_isp() {
        return fr_isp;
    }

    public void setFr_isp(String[] fr_isp) {
        this.fr_isp = fr_isp;
    }

    @Override
    public String toString() {
        return "ThermalResult{" +
                "eq_pivsp=" + Arrays.toString(eq_pivsp) +
                ", eq_pressure=" + Arrays.toString(eq_pressure) +
                ", eq_temperature=" + Arrays.toString(eq_temperature) +
                ", eq_rho=" + Arrays.toString(eq_rho) +
                ", eq_h=" + Arrays.toString(eq_h) +
                ", eq_u=" + Arrays.toString(eq_u) +
                ", eq_g=" + Arrays.toString(eq_g) +
                ", eq_s=" + Arrays.toString(eq_s) +
                ", eq_mole=" + Arrays.toString(eq_mole) +
                ", eq_dvdp=" + Arrays.toString(eq_dvdp) +
                ", eq_dvdt=" + Arrays.toString(eq_dvdt) +
                ", eq_cp=" + Arrays.toString(eq_cp) +
                ", eq_gamma=" + Arrays.toString(eq_gamma) +
                ", eq_sonic=" + Arrays.toString(eq_sonic) +
                ", eq_mach=" + Arrays.toString(eq_mach) +
                ", eq_eps=" + Arrays.toString(eq_eps) +
                ", eq_cstar=" + Arrays.toString(eq_cstar) +
                ", eq_cf=" + Arrays.toString(eq_cf) +
                ", eq_ivac=" + Arrays.toString(eq_ivac) +
                ", eq_isp=" + Arrays.toString(eq_isp) +
                ", fr_pivsp=" + Arrays.toString(fr_pivsp) +
                ", fr_pressure=" + Arrays.toString(fr_pressure) +
                ", fr_temperature=" + Arrays.toString(fr_temperature) +
                ", fr_rho=" + Arrays.toString(fr_rho) +
                ", fr_h=" + Arrays.toString(fr_h) +
                ", fr_u=" + Arrays.toString(fr_u) +
                ", fr_g=" + Arrays.toString(fr_g) +
                ", fr_s=" + Arrays.toString(fr_s) +
                ", fr_mole=" + Arrays.toString(fr_mole) +
                ", fr_cp=" + Arrays.toString(fr_cp) +
                ", fr_gamma=" + Arrays.toString(fr_gamma) +
                ", fr_sonic=" + Arrays.toString(fr_sonic) +
                ", fr_mach=" + Arrays.toString(fr_mach) +
                ", fr_eps=" + Arrays.toString(fr_eps) +
                ", fr_cstar=" + Arrays.toString(fr_cstar) +
                ", fr_cf=" + Arrays.toString(fr_cf) +
                ", fr_ivac=" + Arrays.toString(fr_ivac) +
                ", fr_isp=" + Arrays.toString(fr_isp) +
                '}';
    }
}
