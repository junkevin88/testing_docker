package com.binar5.apps.chapter3.junit;

import com.binar5.apps.entity.Barang3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestMockito2 {

    @Mock
    List<Barang3> mockList;

    @Before
    public void setup() {
        //if we don't call below, we will get NullPointerException
       
        MockitoAnnotations.initMocks(this);
//
    }

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        // cara menambahkannya, dummy request
        Mockito.when(mockList.get(0)).thenReturn(new Barang3(1L,20.0));
        //hasil akhir pengecekan : input dan output sesuia/ tergantung test case
        // dummy res
        Assert.assertEquals(new Barang3(1L,20.0), mockList.get(0));

        Assert.assertEquals(Optional.of(20.0), Optional.ofNullable(mockList.get(0).getHarga()));
    }


}
