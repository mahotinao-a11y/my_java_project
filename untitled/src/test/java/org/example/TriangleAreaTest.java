package org.example;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

public class TriangleAreaTest {

    @Test
    public void testAreaByBaseAndHeight() {
        double result = TriangleArea.calculateSide(5, 4);
        Assert.assertEquals(result, 10.0, 0.0001);
    }

    public void testAreaByBaseAndHeightParameterized(double a, double h, double expected) {
        double result = TriangleArea.calculateSide(a, h);
        Assert.assertEquals(result, expected, 0.0001);
    }

    @DataProvider(name = "areaDataProvider")
    public Object[][] provideAreaData() {
        return new Object[][]{
                {5.0, 4.0, 10.0},
                {3.0, 6.0, 9.0},
                {2.5, 4.0, 5.0},
                {-10.0, 0.5, 2.5}   // тест должен упасть
        };
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeBase() {
        TriangleArea.calculateSide(-5, 4);
    }
}


