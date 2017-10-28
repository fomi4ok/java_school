package ru.stqa.school.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance() {

    Point p1 = new Point(1.0, 3.0);
    Point p2 = new Point(4.0, 7.0);
    Assert.assertEquals(p1.distance(p2), 5.0);

  }

  @Test

  public void testDistanceOfThePointToItself() {

    Point p1 = new Point(2.0, 3.0);
    Assert.assertEquals(p1.distance(p1), 0.0);

  }

  @Test

  public void testWithNegativeAttributes() {

    Point p1 = new Point(-2.0, -3.0);
    Point p2 = new Point(1.0, 1.0);
    Assert.assertEquals(p1.distance(p2), 5.0);

  }


}
