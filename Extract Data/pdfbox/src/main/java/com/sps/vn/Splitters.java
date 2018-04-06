/*
 * Class: Splitter
 *
 * Created on Mar 12, 2018
 *
 * (c) Copyright Swiss Post Solution, unpublished work
 * All use, disclosure, and/or reproduction of this material is prohibited
 * unless authorized in writing.  All Rights Reserved.
 * Rights in this program belong to:
 * Swiss Post Solution.
 * Floor 4-5-8, ICT Tower, Quang Trung Software City
 */
package com.sps.vn;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

public class Splitters {

    private final static String PATH = "C:/Users/tqminh_1/Desktop/PDF/generation";

    public Splitters() {
        super(); 
    }

    public void splitDocument(final PDDocument document) throws IOException {

        mergeDocument(document);
        splitPage(document);
        splitHeader(document);
    }
    
    private void mergeDocument(PDDocument document) throws IOException {
        String footer= splitFooter(document);
        String header= splitHeader(document);
        
        File fileFooter= new File(footer);
        File fileHeader= new File(header);
        
        PDFMergerUtility pdfMerger= new PDFMergerUtility();
        pdfMerger.setDestinationFileName(PATH + "/footer_header.pdf");
        
        pdfMerger.addSource(fileHeader);
        pdfMerger.addSource(fileFooter);
        
        
        pdfMerger.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
    }

    private String splitFooter(final PDDocument document) throws IOException{
        
        String path= PATH + "/footer.pdf";
        
        Splitter splitter = new Splitter();
        
        splitter.setStartPage(document.getNumberOfPages());
        splitter.setSplitAtPage(document.getNumberOfPages());
        splitter.setEndPage(document.getNumberOfPages());
        
        final List<PDDocument> pages = splitter.split(document);

        final Iterator<PDDocument> iterator = pages.listIterator();
        
        while (iterator.hasNext()) {
            final PDDocument page = iterator.next();
            page.save(path);
        }
        return path;
    }

    private String splitHeader(final PDDocument document) throws IOException {
        
        String path= PATH + "/header.pdf";
        
        Splitter splitter = new Splitter();
        splitter.setEndPage(1);

        final List<PDDocument> pages = splitter.split(document);

        final Iterator<PDDocument> iterator = pages.listIterator();

        while (iterator.hasNext()) {
            final PDDocument page = iterator.next();
            page.save(path);
        }
        return path;
    }

    private void splitPage(final PDDocument document) throws IOException {

        Splitter splitter = new Splitter();
        splitter.setStartPage(1);
        splitter.setSplitAtPage(2);
        splitter.setEndPage(document.getNumberOfPages());

        final List<PDDocument> Pages = splitter.split(document);

        final Iterator<PDDocument> iterator = Pages.listIterator();

        int i = 0;
        while (iterator.hasNext()) {
            final PDDocument pd = iterator.next();
            pd.save(PATH + "/part_" + ++i + ".pdf");
        }
    }
}
