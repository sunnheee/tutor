package com.sh.sys.constants;


//结果的枚举类
public enum ResultEnum {

    SUCCESS(0,"SUCCESS"),
    SYSTEM_ERROR(1,"系统错误"),
    USER_EXISTS(2,"该手机号已注册"),
    CAPTCHA_ERROR(3,"验证码错误"),
    USERNAME_OR_PASSWORD_ERROR(4,"用户名或密码错误"),
    TUTOR_EXISTS(5,"客服正在审核中，请不要重复提交"),
    PHONE_ERROR(6,"当前绑定手机号与您要修改号相同"),
    SAME_PASSWORD(7,"新旧密码不能一致"),
    PASSWORD_ERROR(8,"密码错误"),
    SHORT_PASSWORD(9,"密码不能少于六位"),
    PHONE_NONE(10,"请先输入手机号"),
    NOT_LOGIN(11,"请先登录"),
    COMMENT_NULL(12,"请输入评论内容"),
    CONTENT_ALREADY_EXISTS(13,"内容已存在");

    private int code;
    private String reason;

    public int getCode(){
        return code;
    }

    public String getReason(){
        return reason;
    }

    ResultEnum(int code, String reason){
        this.code = code;
        this.reason = reason;
    }


}
