package com.sioeye.sidecar.model;

/**
 * 
 * @author zhouyou
 * @cktEmail:jinx.zhou@ck-telecom.com
 * @date 2018年4月23日
 * @fieleName Face.java
 * @TODO 搜脸
 */
public class User {

    private Long id;
    private String name;
    private Long teacherId;
    private Long courseId;
    private String desc;
    private String addr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}
