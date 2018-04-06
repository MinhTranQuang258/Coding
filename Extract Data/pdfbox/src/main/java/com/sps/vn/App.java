package com.sps.vn;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.pdfbox.pdmodel.PDDocument;


/**
 * Hello world!
 *
 */
public class App{

    private static final String PATH = "C:/Users/tqminh_1/Desktop/PDF/root";
    
    public static void main( String[] args ) throws Exception{
        long startTime= System.currentTimeMillis();
        try {
            final File folder = new File(PATH);
            final File[] files = folder.listFiles();
            
            Splitters splitters= new Splitters();
            
            for (File file : files) {
                PDDocument document = PDDocument.load(file);
                splitters.splitDocument(document);
            }
        }
        catch (Exception e) {
            throw e;
        }
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
    }
    
    public static void getCoordinates(PDDocument document) throws IOException {
        MyPDFTextStripper stripper= new MyPDFTextStripper();
        try {
            stripper.setSortByPosition(true);
            stripper.setStartPage(0);
            stripper.setEndPage(document.getNumberOfPages());
            
            Writer writer = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, writer);
            stripper.printCoordinates();
        }catch (IOException e) {
            throw e;
        }
        finally {
            if( document != null ) {
                document.close();
            }
        }
    }
    
}
