package org.example;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

public class CalculatorTest {

    @Test(dataProvider = "addDataProvider")
    public void add(int a, int b, int expected) {
        Assert.assertEquals(Calculator.summ(a, b), expected);
    }

    @DataProvider(name = "addDataProvider")
    public Object[][] provideAddData() {
        return new Object[][]{
                {2, 3, 5},
                {-1, 1, 0},
                {0, 0, 0},
                {100, 200, 300},
                {-5, -3, -8}
        };
    }

    @Test(dataProvider = "subtractDataProvider")
    public void subtract(int a, int b, int expected) {
        Assert.assertEquals(Calculator.subtract(a, b), expected);
    }

    @DataProvider(name = "subtractDataProvider")
    public Object[][] provideSubtractData() {
        return new Object[][]{
                {10, 3, 7},
                {5, 10, -5},
                {0, 5, -5},
                {-10, -3, -7},
                {100, 50, 50}
        };
    }

    @Test(dataProvider = "divideDataProvider")
    public void divideValid(int a, int b, double expected) {
        Assert.assertEquals(Calculator.divide(a, b), expected);
    }

    @DataProvider(name = "divideDataProvider")
    public Object[][] provideDivideData() {
        return new Object[][]{
                {10, 2, 5.0},
                {7, 2, 3.5},
                {-10, 2, -5.0},
                {10, -2, -5.0},
                {0, 5, 0.0}
        };
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDivideByZero() {
        Calculator.divide(10, 0);
    }

    @Test
    public void testDivideByZeroWithMessage() {
        try {
            Calculator.divide(10, 0);
            Assert.fail("Ожидалось IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Деление на ноль невозможно");
        }

    }

    @Test(dataProvider = "multiplyDataProvider")
    public void multiply(int a, int b, int expected) {
        Assert.assertEquals(Calculator.multiply(a, b), expected);
    }

    @DataProvider(name = "multiplyDataProvider")
    public Object[][] provideMultiplyData() {
        return new Object[][]{
                {2, 3, 6},
                {-2, 3, -6},
                {0, 5, 0},
                {10, 0, 0},
                {-4, -5, 20}
        };
    }
}