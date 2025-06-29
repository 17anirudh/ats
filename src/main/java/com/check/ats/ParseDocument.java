package com.check.ats;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ParseDocument {
    public static PDDocument pdf = null;
    public static PDFTextStripper pdfText = new PDFTextStripper();
    public static XWPFDocument document = null;
    public static XWPFParagraph para = null;

    public static String pdf(String path){
        try{
            File location = new File(path);
            pdf = Loader.loadPDF(location);
            String text = pdfText.getText(pdf);
            return text;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                pdf.close();
            }
            catch (IOException e){
                
            }
        }
        return null;
    }

    public static String docx(String path){
        try{
            File location = new File(path);
            StringBuilder sb = new StringBuilder();
            FileInputStream fis = new FileInputStream(location);
            document = new XWPFDocument(fis);
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                sb.append(paragraph.getText());
            }
            return sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                document.close();
            }
            catch (Exception e){

            }
        }
        return null;
    }
    
    public String getFiletype(String path){
        String extension = "";
        int index = path.lastIndexOf('.');
        if (index > 0 && index < path.length() - 1) {
            extension = path.substring(index + 1);
        } 
        return extension;  
    }
    
}