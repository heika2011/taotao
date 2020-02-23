package cn.tt.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("user")
//用户公共信息(包括商家)
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer uid;        //用户ID
    private String username;    //用户名
    private String password;    //用户密码
    private String sald;        //用户加密盐
    private String email;       //用户邮箱
    private Boolean enable;      //用户是否被验证
    private Date createTime;    //用户创建时间



}
