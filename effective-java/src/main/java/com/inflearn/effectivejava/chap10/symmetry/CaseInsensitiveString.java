package com.inflearn.effectivejava.chap10.symmetry;

import java.util.Objects;

public class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CaseInsensitiveString && ((CaseInsensitiveString) obj).s.equalsIgnoreCase(s);
    }

    // 대칭성 위반.
//    @Override
//    public boolean equals(Object o) {
//        if (o instanceof CaseInsensitiveString) return s.equalsIgnoreCase(((CaseInsensitiveString) o).s);
//        if (o instanceof String) return s.equalsIgnoreCase((String) o);
//        return false;
//    }
}
