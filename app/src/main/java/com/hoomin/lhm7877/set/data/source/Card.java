package com.hoomin.lhm7877.set.data.source;

import android.widget.ImageView;

/**
 * <pre>
 * Created by lhm0805 on 2018-05-29.
 * </pre>
 */
public class Card {
    private int imgageRes;
    private int number;
    private int shape;
    private int shading;
    private int color;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTestResource() {
        return testResource;
    }

    public void setTestResource(String testResource) {
        this.testResource = testResource;
    }

    private String testResource;

    public Card(int imgageRes, int number, int shape, int shading, int color, String testResource) {
        this.imgageRes = imgageRes;
        this.number = number;
        this.shape = shape;
        this.shading = shading;
        this.color = color;
        this.testResource = testResource;
    }

    public int getImgageRes() {
        return imgageRes;
    }

    public void setImgageRes(int imgageRes) {
        this.imgageRes = imgageRes;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getShading() {
        return shading;
    }

    public void setShading(int shading) {
        this.shading = shading;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
