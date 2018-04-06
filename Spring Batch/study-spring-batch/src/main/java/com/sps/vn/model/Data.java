/*
 * Class: Data
 *
 * Created on Mar 2, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn.model;

import java.io.Serializable;

public class Data implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private String cardName;
    
    private double duration;

    public Data() {
        super();
    }

    public Data(String cardName, double duration) {
        super();
        this.cardName = cardName;
        this.duration = duration;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

}
