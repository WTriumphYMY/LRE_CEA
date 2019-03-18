package cn.edu.nwpu.lre_cea.service.impl;

import cn.edu.nwpu.lre_cea.service.ProductGenerateService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ProductGenerateServiceImpl
 * @Author: wkx
 * @Date: 2019/2/28 19:55
 * @Version: v1.0
 * @Description: 生成产物的实现类
 */
@Service
public class ProductGenerateServiceImpl implements ProductGenerateService {
    /**
     * 调用b1b2b3.exe产生生成物
     * @param inputStr 反应物字符串
     * @return 返回生成物map
     */
    @Override
    public Map generateProduct(String inputStr) {
        inputStr += " \n" +
                " ENDR\n" +
                "   \n" +
                "    \n" +
                "    \n";
        System.out.println(inputStr);
        File inputFile = null;
        Map<String, String[]> productMap = new HashMap<>();//返回的map，key为b1,b2,b3
        try {
            inputFile = ResourceUtils.getFile("classpath:cea/input.txt");
            Path inputPath = inputFile.toPath();
            Files.deleteIfExists(inputPath);
            if (!inputFile.exists()){
                inputFile.createNewFile();
            }
            BufferedWriter bw = Files.newBufferedWriter(inputPath, StandardCharsets.US_ASCII);
            bw.write(inputStr);
            bw.close();
        }catch (IOException e){
            System.out.println("生成input.txt错误");
        }
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(inputFile.getParent()+"\\startb1b2b3.bat");
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            System.out.println("未找到b1b2b3.exe");
        }
        //向map中put结果
        try {
            productMap.put("b1", readFile(new File(inputFile.getParent()+"/B1.txt")).split("\\s+"));
            productMap.put("b2", readFile(new File(inputFile.getParent()+"/B2.txt")).split("\\s+"));
            productMap.put("b3", readFile(new File(inputFile.getParent()+"/B3.txt")).split("\\s+"));
        }catch (IOException e){
            System.out.println("b1,b2,b3.txt未找到");
        }
        return productMap;
    }

    /**
     * 一次读入文件内所有内容
     * @param file 需要读内容文件
     * @return 返回文件内内容
     * @throws IOException
     */
    private String readFile (File file) throws IOException{
        return new String((Files.readAllBytes(file.toPath())));
    }
}
