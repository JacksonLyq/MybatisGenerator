package com.lyq.entity;

import com.lyq.model.User;

public class UserVo {
    private int id;
    private String name;
    private String pwd;

    /**
     * 存储于数据库中的model（实体类）为持久层对象：Po；
     * 从前台传入的实体类对象为：Vo；
     * 对Vo存入数据库model中的过程，我们首先要将Vo转为Po（调用initDbValue()），然后再进行数据库存储；
     * Vo与Po的存在有利于解耦与理解，Vo用于传输实体类信息，可以显示数据库中表中不存在的字段；
     * Po转Vo：
     * UserVo为前台传入的实体类对象，userVo.initDbValue()之后：Vo转为Po；
     */
    //① Vo转Po，当数据库中无对应Po时，new出一个Po
    public User initDbValue(){
        User userPo = new User();
        return initDbValue(userPo);
    }

    //② userVo.initDbValue()之后，userVo的值会set进Po（User）中，我们得到的就是一个和数据库完全对应的model
    public User initDbValue(User user){
        if (user!=null){
            user.setId(this.getId());
            user.setName(this.getName());
            user.setPwd(this.getPwd());
        }
        return user;
    }

    public UserVo(){

    }

    public UserVo(User user){
        this.setId(user.getId());
        this.setName(user.getName());
        this.setPwd(user.getPwd());
    }

    public UserVo(int id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
