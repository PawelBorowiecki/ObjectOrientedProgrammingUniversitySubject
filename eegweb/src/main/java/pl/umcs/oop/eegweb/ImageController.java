package pl.umcs.oop.eegweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class ImageController {
    //aplikacje ze springa generujemy ze strony spring initializr

    @GetMapping("/image")
    public String image(Model model) throws IOException {
        String base64;
        BufferedReader reader = new BufferedReader(new FileReader("/tmp/data.txt"));
        base64 = reader.readLine();
        model.addAttribute("image", base64);
        return "eegimage";
    }
}
