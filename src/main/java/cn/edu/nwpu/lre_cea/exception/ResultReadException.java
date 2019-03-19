package cn.edu.nwpu.lre_cea.exception;

/**
 * @ClassName ResultReadException
 * @Author: wkx
 * @Date: 2019/3/19 11:09
 * @Version: v1.0
 * @Description: 热力学计算错误导致读文件报错异常
 */
public class ResultReadException extends RuntimeException {
    public ResultReadException() {
        super();
    }

    public ResultReadException(String message) {
        super(message);
    }
}
