package org.example;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

public class APPTest {

    @Test
    public void compareFirstGreater() {
        int a = 5;
        int b = 3;
        String expected = "5 больше 3";
        String actual = NumberComparator.compare(a, b);
        Assert.assertEquals(actual, expected);
    }

       @Test
    public void compareFirstLess() {
        String actual = NumberComparator.compare(-5, 0);
        Assert.assertEquals(actual, "-5 меньше 0");
    }

    @Test(dataProvider = "equalNumbersData")
    public void compareEqual(int a, int b, int expected) {
        int actual = NumberComparator.compareToInt(a, b);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "equalNumbersData")
    public Object[][] provideEqualNumbers() {
        return new Object[][]{
                {5, 5, 0},
                {10, 10, 0},
                {-4, -4, 0}
        };
    }

    @Test
    public void compareToIntFirstGreater() {
        Assert.assertEquals(NumberComparator.compareToInt(5, 3), 1);
        Assert.assertTrue(NumberComparator.compareToInt(10, 0) > 0);
    }

    @Test
    public void compareToIntFirstLess() {
        Assert.assertEquals(NumberComparator.compareToInt(3, 5), -1);
        Assert.assertTrue(NumberComparator.compareToInt(0, 10) < 0);
    }

    @Test
    public void compareToIntEqual() {
        Assert.assertEquals(NumberComparator.compareToInt(5, 5), 0);
        Assert.assertEquals(NumberComparator.compareToInt(0, 0), 0);
        Assert.assertEquals(NumberComparator.compareToInt(-10, -10), 0);
    }

}
