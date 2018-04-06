/*
 * Class: MyPDFTextStripper
 *
 * Created on Mar 8, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class MyPDFTextStripper extends PDFTextStripper{
    
    private List<Coordinate> lines;

    public MyPDFTextStripper() throws IOException {
        super();
        lines = new ArrayList<Coordinate>();
        setAddMoreFormatting(true);
    }

    @Override
    protected void writeString(String text, List<TextPosition> textPositions)
            throws IOException {
        TextPosition position= textPositions.get(0);
        lines.add(new Coordinate(text, position.getUnicode(), position.getX(), position.getY()));
    }

    public void printCoordinates() {
        for (Coordinate coordinate : lines) {
            System.out.println(coordinate.toString());
        }
    }
}
