package com.fuhaidev.app.dao.entity.test1;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "user")
public class UserEntity {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 名字
     */
    private String name;

    private Integer age;

    private Date tm;

    private Long salary;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return tm
     */
    public Date getTm() {
        return tm;
    }

    /**
     * @param tm
     */
    public void setTm(Date tm) {
        this.tm = tm;
    }

    /**
     * @return salary
     */
    public Long getSalary() {
        return salary;
    }

    /**
     * @param salary
     */
    public void setSalary(Long salary) {
        this.salary = salary;
    }
}