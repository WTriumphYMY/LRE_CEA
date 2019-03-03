package cn.edu.nwpu.lre_cea.service.impl;


import cn.edu.nwpu.lre_cea.service.ProductGenerateService;
import org.junit.Test;

public class ProductGenerateServiceImplTest {

    @Test
    public void testProductGenerate(){
        ProductGenerateService productGenerateService = new ProductGenerateServiceImpl();
        System.out.println(productGenerateService.generateProduct(" H2(L) \n" + " O2(L) \n"));
    }

}