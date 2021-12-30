package com.zgl.modules.token.service;

import com.zgl.modules.token.entity.TokenEntity;
import com.zgl.utils.R;

public interface TokenService {
    R addToken(TokenEntity token);
    R updataToken(TokenEntity token);
    R findByUserId(int userid);
}
