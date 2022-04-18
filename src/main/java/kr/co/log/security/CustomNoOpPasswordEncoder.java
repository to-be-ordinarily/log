package kr.co.log.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoOpPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {

        System.out.println("before encode :" + rawPassword);

        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        System.out.println("matches: " + rawPassword + ":" + encodedPassword);

        return rawPassword.toString().equals(encodedPassword);
    }

}