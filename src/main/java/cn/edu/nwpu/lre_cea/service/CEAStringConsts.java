package cn.edu.nwpu.lre_cea.service;

/**
 * @InterfaceName CEAStringConsts
 * @Author: wkx
 * @Date: 2019/3/6 19:55
 * @Version: v1.0
 * @Description:
 */
public interface CEAStringConsts {
    String inpFileModel = "problem   \n" +
            "      rocket  equilibrium  frozen  nfz=1  tcest,k={0}\n" +
            "  p,bar={1},\n" +
            "  pi/p={2},\n" +
            "  sup,ae/at={3},\n" +
            "react  \n";
    String PINF_P = "Pinf/P "; //压强比
    String P_BAR = "P, BAR"; //压强
    String T_K = "T, K"; //温度
    String RHO_KG_CUM = "RHO, KG/CU M"; //密度
    String H_KJ_KG = "H, KJ/KG"; //焓
    String U_KJ_KG = "U, KJ/KG";//内能
    String G_KJ_KG = "G, KJ/KG"; //吉布斯能
    String S_KJ_KG_K = "S, KJ/(KG)(K)"; //熵
    String MOLE = "M, (1/n)"; //摩尔质量
    String DLV_DLP = "(dLV/dLP)t"; //偏导数
    String DLV_DLT = "(dLV/dLT)p"; //偏导数
    String CP_KJ_KG_G = "Cp, KJ/(KG)(K)"; //定压指数
    String GAMMAS = "GAMMAs"; //比热比
    String SONVEL_M_SEC = "SON VEL,M/SEC"; //声速
    String MACH = "MACH NUMBER"; //马赫数
    String AEAT = "Ae/At"; //面积比
    String CSTAR_M_SEC = "CSTAR, M/SEC"; //特征速度
    String CF = "CF"; //推理系数
    String IVAC_M_SEC = "Ivac, M/SEC"; //真空比冲
    String ISP_M_SEC = "Isp, M/SEC"; //比冲
}
