package com.check.ats;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Sample {
        public void read(){
        PDDocument doc = null;
        PDFTextStripper textDoc = new PDFTextStripper();
        try {
            File file = new File("C:\\Users\\vedal\\OneDrive\\Desktop\\Desk\\ats\\src\\main\\resources\\static\\pdfs\\1.pdf");
            doc = Loader.loadPDF(file);
            String content = textDoc.getText(doc);
            
            // You can now work with the document
            System.out.println("PDF loaded successfully!");
            System.out.println("Number of pages: " + doc.getNumberOfPages());
            System.out.println(content);
            
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            if (doc != null) {
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
