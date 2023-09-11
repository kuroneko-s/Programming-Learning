package com.inflearn.effectivejava.chap7;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.text.DateFormat;

@Setter
@Getter
@ToString
public class CacheKey {
    private Integer key;
    private DateFormat createAt;

    public CacheKey(Integer key) {
        this.key = key;
    }
}
