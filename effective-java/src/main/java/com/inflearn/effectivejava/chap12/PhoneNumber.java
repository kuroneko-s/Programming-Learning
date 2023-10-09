package com.inflearn.effectivejava.chap12;

import java.util.Objects;

public class PhoneNumber {
    private final int firstNumber;
    private final int middleNumber;
    private final int lastNumber;

    public PhoneNumber(int firstNumber, int middleNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.middleNumber = middleNumber;
        this.lastNumber = lastNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return firstNumber == that.firstNumber && middleNumber == that.middleNumber && lastNumber == that.lastNumber;
    }

      // 모든 경우에 같은 해시코드를 반환. 이렇게 할 경우 해시테이블 성능이 많이 떨어진다. (HashMap이 아니라 그냥 LinkedList 사용하는 것과 별반 다를게 없어짐.)
      // 시간복잡도 O(1)에서 O(n)으로 느려짐.
//    @Override
//    public int hashCode() {
//        return 99;
//    }

    @Override
    public int hashCode() {
        // 해시코드 구현시 내부에서 소수 값을 가지고 해시코드를 만드는데 그 소수 값이 31.
        // 31 - 해시 충돌이 그나마 덜한 값. 어떤 위대하신분이 일일이 다 테스트 해보셧다고 함.
        return Objects.hash(firstNumber, middleNumber, lastNumber);
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "firstNumber=" + firstNumber +
                ", middleNumber=" + middleNumber +
                ", lastNumber=" + lastNumber +
                '}';
    }
}
