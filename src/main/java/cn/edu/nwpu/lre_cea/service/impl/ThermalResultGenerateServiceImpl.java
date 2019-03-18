package cn.edu.nwpu.lre_cea.service.impl;

import cn.edu.nwpu.lre_cea.domain.RocketCondition;
import cn.edu.nwpu.lre_cea.domain.ThermalResult;
import cn.edu.nwpu.lre_cea.consts.CEAStringConsts;
import cn.edu.nwpu.lre_cea.service.ThermalResultGenerateService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.MessageFormat;

/**
 * @ClassName ThermalResultGenerateServiceImpl
 * @Author: wkx
 * @Date: 2019/3/2 09:49
 * @Version: v1.0
 * @Description: 获得热力学计算结果的实现类
 */
@Service
public class ThermalResultGenerateServiceImpl implements ThermalResultGenerateService {

    /**
     *
     * @param rocketCondition 热力学计算条件
     * @return
     */
    @Override
    public ThermalResult generateThermalResult(RocketCondition rocketCondition) {
        createInpFile(rocketCondition);
        try {
            File batCEAFile = new File(getResourceFile(), "executeCEA.bat");
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(batCEAFile.getAbsolutePath());
            OutputStream localOutputStream = process.getOutputStream();
            PrintWriter localPrintWriter = new PrintWriter(localOutputStream);
            localPrintWriter.println(rocketCondition.getProjectName() + "\n");
            localPrintWriter.flush();
            localPrintWriter.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("调用FCEA2失败");
            e.printStackTrace();
        }
        ThermalResult thermalResult = null;
        try {
            thermalResult = translateOutFile(rocketCondition.getProjectName());

        } catch (IOException e) {
            System.out.println("翻译结果失败");
            e.printStackTrace();
        }
        return thermalResult;
    }

    /**
     * 生成inp文件
     * @param rocketCondition
     */
    private void createInpFile(RocketCondition rocketCondition){
        String[] conditionArguments = {rocketCondition.getTemperature(), rocketCondition.getPressure(),
                rocketCondition.getPressure(), rocketCondition.getEps()};
        String actualInp = MessageFormat.format(CEAStringConsts.inpFileModel, conditionArguments);
        for (int i = 0; i < rocketCondition.getReactParameters().getReactTypes().size(); i++) {
            actualInp += "  " + rocketCondition.getReactParameters().getReactTypes().get(i)+ "=" +
                    rocketCondition.getReactParameters().getReactNames().get(i) + " wt=" + rocketCondition.getReactParameters().getReactWeights().get(i) + "  ";
            if (rocketCondition.getReactParameters().getReactTemperatures() != null && !rocketCondition.getReactParameters().getReactTemperatures().isEmpty()){
                actualInp += "t(k)=" + rocketCondition.getReactParameters().getReactTemperatures().get(i);
            }
            actualInp += "\n";
        }
        if (rocketCondition.getReactParameters().getProductList() != null && !rocketCondition.getReactParameters().getProductList().isEmpty()){
            int lineCount = 1;
            actualInp += "only \n";
            for (String product : rocketCondition.getReactParameters().getProductList()) {
                actualInp += " " + product;
                if (lineCount%6 == 0){
                    actualInp += "\n";
                }
                lineCount++;
            }
            if ( (lineCount-1)%6 != 0){
                actualInp += "\n";
            }
        }
        actualInp += "end\n";
        try {
            File inpFile = new File(getResourceFile(), rocketCondition.getProjectName()+".inp");
            Files.deleteIfExists(inpFile.toPath());
            BufferedWriter bw = Files.newBufferedWriter(inpFile.toPath(), StandardCharsets.US_ASCII);
            bw.write(actualInp);
            bw.close();
        }catch (IOException e){
            System.out.println("inp文件生成失败！");
            throw new RuntimeException("inp文件生成失败！");
        }
    }

    /**
     *
     * @param outName 热力计算项目名
     * @return 热力学计算结果domain类，包括平衡流与冻结流
     * @throws IOException
     */
    private ThermalResult translateOutFile(String outName)throws IOException{
        File outFile = new File(getResourceFile(), outName+".out");
        BufferedReader br = Files.newBufferedReader(outFile.toPath());
        String lineRead = null;
        ThermalResult thermalResult = new ThermalResult();
        boolean flag = true; //判断是否是平衡流
        while ((lineRead=br.readLine()) != null){
            if (lineRead.indexOf(CEAStringConsts.PINF_P) != -1){
                if (flag){
                    thermalResult.getEq_pivsp()[0] = CEAStringConsts.zh_PINF_P;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_pivsp()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_pivsp()[0] = CEAStringConsts.zh_PINF_P;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_pivsp()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.P_BAR) != -1){
                if (flag){
                    thermalResult.getEq_pressure()[0] = CEAStringConsts.zh_P_BAR;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_pressure()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_pressure()[0] = CEAStringConsts.zh_P_BAR;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_pressure()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.T_K) != -1){
                if (flag) {
                    thermalResult.getEq_temperature()[0] = CEAStringConsts.zh_T_K;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_temperature()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_temperature()[0] = CEAStringConsts.zh_T_K;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_temperature()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.RHO_KG_CUM) != -1){
                if (flag){
                    thermalResult.getEq_rho()[0] = CEAStringConsts.zh_RHO_KG_CUM;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_rho()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_rho()[0] = CEAStringConsts.zh_RHO_KG_CUM;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_rho()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.H_KJ_KG) != -1){
                if (flag){
                    thermalResult.getEq_h()[0] = CEAStringConsts.zh_H_KJ_KG;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_h()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_h()[0] = CEAStringConsts.zh_H_KJ_KG;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_h()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.U_KJ_KG) != -1){
                if (flag){
                    thermalResult.getEq_u()[0] = CEAStringConsts.zh_U_KJ_KG;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_u()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_u()[0] = CEAStringConsts.zh_U_KJ_KG;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_u()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.G_KJ_KG) != -1){
                if (flag){
                    thermalResult.getEq_g()[0] = CEAStringConsts.zh_G_KJ_KG;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_g()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_g()[0] = CEAStringConsts.zh_G_KJ_KG;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_g()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.S_KJ_KG_K) != -1){
                thermalResult.getEq_s()[0] = CEAStringConsts.S_KJ_KG_K;
                if (flag){
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_s()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_s()[0] = CEAStringConsts.S_KJ_KG_K;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_s()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.MOLE) != -1){
                if (flag){
                    thermalResult.getEq_mole()[0] = CEAStringConsts.zh_MOLE;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_mole()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_mole()[0] = CEAStringConsts.zh_MOLE;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_mole()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.DLV_DLP) != -1){
                if (flag){
                    thermalResult.getEq_dvdp()[0] = CEAStringConsts.zh_DLV_DLP;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_dvdp()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.DLV_DLT) != -1){
                if (flag){
                    thermalResult.getEq_dvdt()[0] = CEAStringConsts.zh_DLV_DLT;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_dvdt()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.CP_KJ_KG_G) != -1){
                if (flag){
                    thermalResult.getEq_cp()[0] = CEAStringConsts.zh_CP_KJ_KG_G;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_cp()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_cp()[0] = CEAStringConsts.zh_CP_KJ_KG_G;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_cp()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.GAMMAS) != -1){
                if (flag){
                    thermalResult.getEq_gamma()[0] = CEAStringConsts.zh_GAMMAS;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_gamma()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_gamma()[0] = CEAStringConsts.zh_GAMMAS;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_gamma()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.SONVEL_M_SEC) != -1){
                if (flag){
                    thermalResult.getEq_sonic()[0] = CEAStringConsts.zh_SONVEL_M_SEC;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_sonic()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_sonic()[0] = CEAStringConsts.zh_SONVEL_M_SEC;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_sonic()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.MACH) != -1){
                if (flag){
                    thermalResult.getEq_mach()[0] = CEAStringConsts.zh_MACH;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getEq_mach()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_mach()[0] = CEAStringConsts.zh_MACH;
                    for (int i = 1; i < 5; i++){
                        thermalResult.getFr_mach()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.AEAT) != -1){
                if (flag){
                    thermalResult.getEq_eps()[0] = CEAStringConsts.zh_AEAT;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getEq_eps()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_eps()[0] = CEAStringConsts.zh_AEAT;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getFr_eps()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.CSTAR_M_SEC) != -1){
                if (flag){
                    thermalResult.getEq_cstar()[0] = CEAStringConsts.zh_CSTAR_M_SEC;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getEq_cstar()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_cstar()[0] = CEAStringConsts.zh_CSTAR_M_SEC;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getFr_cstar()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.CF) != -1){
                if (flag){
                    thermalResult.getEq_cf()[0] = CEAStringConsts.zh_CF;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getEq_cf()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_cf()[0] = CEAStringConsts.zh_CF;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getFr_cf()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.IVAC_M_SEC) != -1){
                if (flag){
                    thermalResult.getEq_ivac()[0] = CEAStringConsts.zh_IVAC_M_SEC;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getEq_ivac()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_ivac()[0] = CEAStringConsts.zh_IVAC_M_SEC;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getFr_ivac()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
            }
            if (lineRead.indexOf(CEAStringConsts.ISP_M_SEC) != -1){
                if (flag){
                    thermalResult.getEq_isp()[0] = CEAStringConsts.zh_ISP_M_SEC;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getEq_isp()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }else {
                    thermalResult.getFr_isp()[0] = CEAStringConsts.zh_ISP_M_SEC;
                    for (int i = 1; i < 4; i++){
                        thermalResult.getFr_isp()[i] = lineRead.substring(15).trim().split("\\s+")[i-1];
                    }
                }
                flag = false;
            }
        }

        return thermalResult;
    }

    private File getResourceFile(){
        try {
            return ResourceUtils.getFile("classpath:cea");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
