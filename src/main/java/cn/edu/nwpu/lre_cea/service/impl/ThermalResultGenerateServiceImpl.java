package cn.edu.nwpu.lre_cea.service.impl;

import cn.edu.nwpu.lre_cea.domain.RocketCondition;
import cn.edu.nwpu.lre_cea.domain.ThermalResult;
import cn.edu.nwpu.lre_cea.service.CEAStringConsts;
import cn.edu.nwpu.lre_cea.service.ThermalResultGenerateService;
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
public class ThermalResultGenerateServiceImpl implements ThermalResultGenerateService {

    @Override
    public String generateThermalResult(RocketCondition rocketCondition) {
        createInpFile(rocketCondition);
        try {
            File batCEAFile = ResourceUtils.getFile("classpath:cea/executeCEA.bat");
            Runtime runtime = Runtime.getRuntime();
            String[] cmds = {batCEAFile.getAbsolutePath(), rocketCondition.getProjectName()};
            Process process = runtime.exec(cmds);
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
        return null;
    }

    /**
     * 生成inp文件
     * @param rocketCondition
     */
    private void createInpFile(RocketCondition rocketCondition){
        String[] conditionArguments = {rocketCondition.getTemperature(), rocketCondition.getPressure(),
                rocketCondition.getPressure(), rocketCondition.getEps()};
        String actualInp = MessageFormat.format(CEAStringConsts.inpFileModel, conditionArguments);
        for (int i = 0; i < rocketCondition.getReactTypes().size(); i++) {
            actualInp += "  " + rocketCondition.getReactTypes().get(i)+ "=" +
                    rocketCondition.getReactNames().get(i) + " wt=" + rocketCondition.getReactWeights().get(i) + "  ";
            if (rocketCondition.getReactTemperatures() != null && !rocketCondition.getReactTemperatures().isEmpty()){
                actualInp += "t(k)=" + rocketCondition.getReactTemperatures().get(i);
            }
            actualInp += "\n";
        }
        if (rocketCondition.getProductList() != null && !rocketCondition.getProductList().isEmpty()){
            int lineCount = 1;
            actualInp += "only \n";
            for (String product : rocketCondition.getProductList()) {
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
            File parentFile = ResourceUtils.getFile("classpath:cea");
            File inpFile = new File(parentFile, rocketCondition.getProjectName()+".inp");
            Files.deleteIfExists(inpFile.toPath());
            BufferedWriter bw = Files.newBufferedWriter(inpFile.toPath(), StandardCharsets.US_ASCII);
            bw.write(actualInp);
            bw.close();
        }catch (IOException e){
            System.out.println("inp文件生成失败！");
            throw new RuntimeException("inp文件生成失败！");
        }
    }

    private String translateOutFile(String outName)throws Exception{
        File outFile = new File(getResourceFile(), outName+".out");
        BufferedReader br = Files.newBufferedReader(outFile.toPath());
        String lineRead = null;
        ThermalResult thermalResult = new ThermalResult();
        //TODO 将热力学计算结果读入持久层
        while ((lineRead=br.readLine()) != null){
            if (lineRead.indexOf(CEAStringConsts.PINF_P) != -1){
                for (int i = 0; i < 4; i++){
                    thermalResult.getEq_pivsp()[i] = lineRead.trim().split("\\s+")[i+1];
                }
            }
        }

        return null;
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
