package com.self.cloud;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Auther: LiRuiChuan
 * @Date: 2020/4/3 14:56
 * @Description:
 */
@Entity
@Table(name = "tb_user", schema = "cloud_demo", catalog = "")
public class TbUserEntity {
    private int id;
    private Timestamp createAt;
    private Integer createBy;
    private Timestamp updateAt;
    private Integer updateBy;
    private String business;
    private String email;
    private String heaPicture;
    private String password;
    private String realName;
    private Integer state;
    private String userName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_at", nullable = false)
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "create_by", nullable = true)
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "update_at", nullable = false)
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Basic
    @Column(name = "update_by", nullable = true)
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "business", nullable = true, length = 45)
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "hea_picture", nullable = true, length = 45)
    public String getHeaPicture() {
        return heaPicture;
    }

    public void setHeaPicture(String heaPicture) {
        this.heaPicture = heaPicture;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "real_name", nullable = true, length = 45)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbUserEntity that = (TbUserEntity) o;
        return id == that.id &&
                Objects.equals(createAt, that.createAt) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateAt, that.updateAt) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(business, that.business) &&
                Objects.equals(email, that.email) &&
                Objects.equals(heaPicture, that.heaPicture) &&
                Objects.equals(password, that.password) &&
                Objects.equals(realName, that.realName) &&
                Objects.equals(state, that.state) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createAt, createBy, updateAt, updateBy, business, email, heaPicture, password, realName, state, userName);
    }
}
