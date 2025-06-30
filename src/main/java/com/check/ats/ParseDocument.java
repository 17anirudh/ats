package com.check.ats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;

@Service
public class ParseDocument {
    public static PDDocument pdf = null;
    public static PDFTextStripper pdfText = new PDFTextStripper();
    public static XWPFDocument document = null;
    public static XWPFParagraph para = null;

    public String parse(String path){
        String a = getFiletype(path).toLowerCase();
        switch (a) {
            case "pdf": return pdf(a);
            case "docx": return docx(a);
            case "txt": return txt(a);
            default: return null;
        }
    }

    public String pdf(String path){
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

    public String docx(String path){
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

    public String txt(String path){
        BufferedReader br = null;
        try{
            StringBuilder sb = new StringBuilder();
            br = new BufferedReader(new FileReader(path));
            while (br.ready()) {
                sb.append(br.readLine());
            }
            return sb.toString();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if (br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
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