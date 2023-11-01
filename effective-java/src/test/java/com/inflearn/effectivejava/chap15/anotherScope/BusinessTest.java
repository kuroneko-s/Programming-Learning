package com.inflearn.effectivejava.chap15.anotherScope;

import com.inflearn.effectivejava.chap15.innerScope.SampleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BusinessTest {
    transient SampleService sampleService;

    @Test
    public void test() {
        Business business = new Business(sampleService);
        assertNotNull(business);
        assertNotNull(business.sampleService);
    }

}