package com.binar5.apps.chapter3.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestMockito {

//    @Mock
//    List<Barang> data;
//
//    @Before
//    public  void init(){
//        data = new ArrayList<>();
//        data.add(new Barang(1L,20.0));
//        data.add(new Barang(1L,20.0));
//        data.add(new Barang(1L,20.0));
//    }

//    @Test
//    public void getData(){
//        Mockito.any().equals(data);
//    }

    @Mock
    List<String> mockList;

    @Before
    public void setup() {
        //if we don't call below, we will get NullPointerException

        MockitoAnnotations.initMocks(this);
//
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {

//        mockList.add("cara biasa");

        Mockito.when(mockList.get(0)).thenReturn("JournalDev");
        Mockito.when(mockList.get(1)).thenReturn("riki");
        Mockito.when(mockList.get(2)).thenReturn("aldi");

        System.out.println("masuk ke mock");
        Assert.assertEquals("JournalDev", mockList.get(0));
        Assert.assertEquals("riki", mockList.get(1));
    }


}
