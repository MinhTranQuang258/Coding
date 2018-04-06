/*
 * Class: Coordination
 *
 * Created on Mar 7, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn;

public class Coordinate {

    private String content;
    
    private String startContent;
    
    private double x;
    
    private double y;

    public Coordinate(String content, String startContent, double x, double y) {
        super();
        this.content = content;
        this.startContent = startContent;
        this.x = x;
        this.y = y;
    }

    public Coordinate() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartContent() {
        return startContent;
    }

    public void setStartContent(String startContent) {
        this.startContent = startContent;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }  
    
    @Override
    public String toString() {
        String string= String.format("Content: %s   -%s-   [ X: %.2f , Y: %.2f ]\n", content, startContent, x , y);
        return string;
    }
}
