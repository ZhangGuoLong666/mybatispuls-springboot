package com.zgl.modules.token.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_token")
public class TokenEntity {
    @TableId
    @ApiModelProperty(value = "id")
    private int tokenId;
    @ApiModelProperty(value = "用户id",required = true)
    private int userId;
    @ApiModelProperty(value = "创建时间",required = false)
    private int bulidtime;
    @ApiModelProperty(value = "token值",required = false)
    private String token;

}
