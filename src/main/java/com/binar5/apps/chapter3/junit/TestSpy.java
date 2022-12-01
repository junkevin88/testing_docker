package com.binar5.apps.chapter3.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestSpy {


    @Mock
    private List<String> mockList = new ArrayList<>();

    @Spy
    private List<String> spyList = new ArrayList();

    @Test
    public void testMockList() {
        //by default, calling the methods of mock object will do nothing
        mockList.add("test");

        Mockito.verify(mockList).add("test");
        Assert.assertEquals(0, mockList.size());
        Assert.assertNull(mockList.get(0));
    }

    @Test
    public void testSpyList() {
        //spy object will call the real method when not stub
        spyList.add("test");

        Mockito.verify(spyList).add("test");
        Assert.assertEquals(1, spyList.size());
        Assert.assertEquals("test", spyList.get(0));
    }

}
