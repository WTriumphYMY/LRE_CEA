package cn.edu.nwpu.lre_cea.exception;

/**
 * @ClassName GenerateProductException
 * @Author: wkx
 * @Date: 2019/3/19 14:19
 * @Version: v1.0
 * @Description: 生成产物报错的异常类
 */
public class GenerateProductException extends RuntimeException {
    public GenerateProductException() {
        super();
    }

    public GenerateProductException(String message) {
        super(message);
    }
}
