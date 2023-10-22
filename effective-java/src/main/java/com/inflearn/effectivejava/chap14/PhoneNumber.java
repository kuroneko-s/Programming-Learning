package com.inflearn.effectivejava.chap14;

import lombok.Getter;

import java.util.Comparator;
import java.util.Objects;

@Getter
public class PhoneNumber implements Comparable<PhoneNumber> {
    private final int firstNumber;
    private final int middleNumber;
    private final int lastNumber;

    public PhoneNumber(int firstNumber, int middleNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.middleNumber = middleNumber;
        this.lastNumber = lastNumber;
    }

    // 복사 생성자
    public PhoneNumber(PhoneNumber phoneNumber) {
        this.firstNumber = phoneNumber.getFirstNumber();
        this.middleNumber = phoneNumber.getMiddleNumber();
        this.lastNumber = phoneNumber.getLastNumber();
    }

    public static PhoneNumber newInstance(PhoneNumber phoneNumber) {
        return new PhoneNumber(phoneNumber);
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

    // IDE를 사용해서 만든 경우. 해당 클래스의 의미에 맞는 동작이 아님.
//    @Override
//    public String toString() {
//        return "PhoneNumber{" +
//                "firstNumber=" + firstNumber +
//                ", middleNumber=" + middleNumber +
//                ", lastNumber=" + lastNumber +
//                '}';
//    }

    // 의미에 맞게 수정한 경우.
    @Override
    public String toString() {
        return String.format("PhoneNumber - %03d-%03d-%04d", this.firstNumber, this.middleNumber, this.lastNumber);
    }

    @Override
    public PhoneNumber clone() {
        try {
            // super.clone()을 호출하지 않으면 하위 클래스에서 Cast에 문제가 생김.
            return (PhoneNumber) super.clone();
        } catch (CloneNotSupportedException exception) {
            throw new AssertionError();
        }
    }

//    @Override
//    public int compareTo(PhoneNumber pn) {
//        int result = Integer.compare(this.firstNumber, pn.getFirstNumber());
//        if (result == 0) {
//            result = Integer.compare(this.middleNumber, pn.getMiddleNumber());
//
//            if (result == 0) {
//                result = Integer.compare(this.lastNumber, pn.getLastNumber());
//            }
//        }
//
//        return result;
//    }

    @Override
    public int compareTo(PhoneNumber pn) {
        return COMPARTOR.compare(this, pn);
    }

    private static final Comparator<PhoneNumber> COMPARTOR =
            Comparator.comparingInt(PhoneNumber::getFirstNumber)
                    .thenComparingInt(PhoneNumber::getMiddleNumber)
                    .thenComparingInt(PhoneNumber::getLastNumber);

}
