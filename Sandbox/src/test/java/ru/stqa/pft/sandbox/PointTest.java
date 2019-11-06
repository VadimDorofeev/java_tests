package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance() {
        Point p1 = new Point (2,5);
        Point p2 = new Point (-1,1);
        Assert.assertEquals(p1.distance(p2), 5.0);

    }
}
