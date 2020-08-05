package com.demo.dao;

import com.demo.vo.User;
import org.springframework.stereotype.Component;


@Component
public interface QRCodeMapper {

    User login(String userName, String passWord);
}
