package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitiy.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.Data;

@Data
class Request {
    @JsonProperty("user_id")
    public Long userId;
    public String bio;
}

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/pdfs")
public class PDFController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public String create(@RequestBody Request request) {
        Document document = new Document();

        final Optional<User> user = userRepository.findById(request.userId);

        if (!user.isPresent()) {
            return "User not found";
        }

        String fileName = "generated/" + user.get().getUsername() + ".pdf";

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
        Font bigFont = FontFactory.getFont(FontFactory.COURIER, 36, BaseColor.BLACK);
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        try {
            // document.add(new Chunk(user.get().getUsername(), bigFont));
            document.addTitle(user.get().getUsername());
            document.add(new Chunk(request.bio, font));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        document.close();

        return fileName;
    }

}
