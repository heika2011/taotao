package cn.tt.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * JSON数据返回对象
 * @谢鑫
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class JSONResult implements Serializable {

    /* 成功 */
    public static final Integer CODE_TRUE=200;
    /* 验证码错误 */
    public static final Integer CODE_CODEFALSE=201;
    /* 参数错误 */
    public static final Integer CODE_ILLFALSE=202;
    /* 服务器错误 */
    public static final Integer CODE_UNNOFALSE=203;


    /* 标识码 */
    private Integer code=JSONResult.CODE_TRUE;
    /* 信息 */
    private String msg="OK";
    /* 数据 */
    private Object data;

}
