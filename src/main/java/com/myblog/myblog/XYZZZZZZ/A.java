package com.myblog.myblog.XYZZZZZZ;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class A {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String test = passwordEncoder.encode("abc");
        System.out.println(test);
    }
}
