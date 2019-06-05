package com.fusion.manage.config;

import com.fusion.manage.entity.console.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by yaju.jiao on 2019/3/6.
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME = "md5";
    //自定义散列次数
    public static final int HASH_ITERATIONS = 2;

    public void encryptPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPwd = new SimpleHash(ALGORITHM_NAME, user.getPwd(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), HASH_ITERATIONS).toHex();
        user.setPwd(newPwd);
    }
}
