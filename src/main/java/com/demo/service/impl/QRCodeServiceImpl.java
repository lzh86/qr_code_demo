package com.demo.service.impl;

import com.demo.dao.QRCodeMapper;
import com.demo.service.QRCodeService;
import com.demo.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Resource
    public QRCodeMapper qrCodeDao;

    public Boolean login(String userName,String passWord){

        User user = qrCodeDao.login(userName, passWord);
        String name = user.getName();
        if(userName.equals(user.getName())&&passWord.equals(user.getPassWord())){
            return true;
        }
        System.out.println(user);

        return false;
    }

}
