package com.example.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PdfMaker {

    @Autowired
    UserRepository userRepository;

    @Value("${pdf.path}")
    String pdfOutputPath;

    public Response make(Request request) {
        Document document = new Document();

        final Optional<User> user = userRepository.findById(request.userId);

        if (!user.isPresent()) {
            return new Response(null);
        }

        String fileName = pdfOutputPath + "/" + user.get().getUsername() + ".pdf";

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        try {
            document.addTitle(user.get().getUsername());
            document.add(new Chunk(request.bio, font));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        document.close();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new Response(fileName);
    }
}
