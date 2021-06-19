package com.test.bcrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestBcrypt {

    public static void main(String[] args) {

        /*for (int i = 0; i < 10; i++) {
            //得到盐
            String gensalt = BCrypt.gensalt();
            System.out.println("得到盐："+gensalt);
            String hashpw = BCrypt.hashpw("123456", gensalt);
            System.out.println("本次生成的密码："+hashpw);
        }*/

        //得到盐
        String gensalt = BCrypt.gensalt();
        System.out.println("得到盐："+gensalt);
        String hashpw = BCrypt.hashpw("123456", gensalt);
        System.out.println("本次生成的密码："+hashpw);

        //检验密码
        boolean checkpw = BCrypt.checkpw("123456", hashpw);

        System.out.println("校验结果为："+checkpw);
    }
}
