package ru.stqa.pft.sandbox;

public class MyFirst {

    public static void main(String[] args) {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(2, 3);
        //для 3 пункта задания
        distance(p1, p2);
        //для 4 пункта задания
        System.out.println("Расстояние между точками = " + p1.distance(p2));
    }

    public static void distance(Point p1, Point p2) {
        System.out.println("Расстояние между точками = " + Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)));
    }
}