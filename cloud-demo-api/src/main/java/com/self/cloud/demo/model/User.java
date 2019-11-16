package com.self.cloud.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: LiRuiChuan
 * @Date: 2019/11/16 17:19
 * @Description: 测试用户表
 */
@Entity
@Data
public class User extends BaseEntity {

    @Column(nullable = false,length = 32)
    private String name;

    @Column(nullable = false,length = 11)
    private int age;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "interest")
    //@Convert(converter = InterestConvert.class)
    private Interest interest;

    @Column(length = 128)
    private String address;

    @Column(name = "mobile_no",nullable = false,length = 11)
    private String mobileNo;

    enum Interest{
        JAVA(0,"java"),
        C(1,"c"),
        NET(2,".net"),
        python(3,"python"),
        ;
        private Integer no;

        private String desc;

        Interest(int no, String desc) {
            this.no = no;
            this.desc = desc;
        }

        public static Interest forValue(Integer no){
            Interest[] interests = Interest.values();
            for(Interest interest : interests){
                if(interest.no.compareTo(no) == 0){
                    return interest;
                }
            }
            return null;
        }
    }

    /**
     * 枚举字段转换器
     */
    private static class InterestConvert implements AttributeConverter<Interest,Integer>{

        @Override
        public Integer convertToDatabaseColumn(Interest interest) {
            return interest.no;
        }

        @Override
        public Interest convertToEntityAttribute(Integer integer) {
            return Interest.forValue(integer);
        }
    }
}
