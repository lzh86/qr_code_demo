package com.demo.utils;

import com.demo.constant.WebConst;
import com.demo.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaleUtils {
    /**
     * 获取保存文件的位置,jar所在目录的路径
     *
     * @return
     */
    public static String getUploadFilePath() {
        String path = TaleUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(1, path.length());
        try {
            path = java.net.URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int lastIndex = path.lastIndexOf("/") + 1;
        path = path.substring(0, lastIndex);
        File file = new File("");
        //return file.getAbsolutePath() + "/" + "src" + "/" + "main"+ "/" + "resources"+  "/" + "static" +  "/" + "codeFile" + "/" ;
        return file.getAbsolutePath() + "/" + "src" + "/" + "main"+ "/" + "resources"+  "/" + "static" +  "/" + "codeFile" + "/" ;
    }

    /**
     * 返回当前登录用户
     *
     * @return
     */
    public static Boolean getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return false;
        }
        return (Boolean) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
    }

    public static void main(String[] args) {
        String identityCard = "3756896515421452";
        String identityCardReplace = identityCard.replaceAll("(\\d{6})\\d{6}(\\d{6})", "$1******$2");
        //System.out.println(identityCardReplace);
        String s = hideAllIdCardNum(identityCard);
        System.out.println(s);

    }


    public static String hideAllIdCardNum(String contentStr){
        Pattern pattern = Pattern.compile("(\\d{6})(19|20)(\\d{2})(1[0-2]|0[1-9])(0[1-9]|[1-2][0-9]|3[0-1])(\\d{3})(\\d|X|x)");
        Matcher matcher = pattern.matcher(contentStr);
        StringBuffer sb = new StringBuffer();
        try {
            while(matcher.find()) {
                String idCardStr = matcher.group();
                int len=idCardStr.length();
                if(len>=9){
                    idCardStr =  idCardStr.replaceAll("(.{"+(len<12?3:6)+"})(.*)(.{4})", "$1" + "****" + "$3");
                }
                matcher.appendReplacement(sb,idCardStr);
            }
            matcher.appendTail(sb);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }




}
