package com.wang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JWANG477@ford.com
 * @date 2023/5/23 10:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;

    private String sex;

    private Integer age;
}
