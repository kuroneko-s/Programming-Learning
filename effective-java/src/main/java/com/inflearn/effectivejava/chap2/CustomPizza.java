package com.inflearn.effectivejava.chap2;

import java.util.ArrayList;
import java.util.List;

public class CustomPizza extends Pizza {
    private List<String> customList;

    CustomPizza(Builder builder) {
        super(builder);
        customList = new ArrayList<>(builder.customList);
    }

    static class Builder extends Pizza.Builder<Builder> {
        private List<String> customList = new ArrayList<>();

        public Builder customToppings(String custom) {
            customList.add(custom);
            return this;
        }

        public Builder addCustom(String custom) {
            this.customList.add(custom);
            return this;
        }

        public Builder() {
            // 커스텀할 내용 넣을 수 있다.
            new CustomPizza(this);
        }

        @Override
        Pizza build() {
            return new CustomPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    @Override
    public String toString() {
        return "CustomPizza{" +
                "custom='" + customList + '\'' +
                ", toppings=" + toppings +
                '}';
    }
}
