package com.daqsoft.commons.reponseEntity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
@JsonSerialize(contentAs = Map.class)
@JsonDeserialize(contentAs = Foo.class)
public class Foo {
    private String name;
    private String addr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Foo() {
    }

    public Foo(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
}
