package owlhome.testexample.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import owlhome.testexample.models.DistanceInfo;
import owlhome.testexample.tools.DistanceInfoBuilder;
import owlhome.testexample.utils.FilePath;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;


@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getReq(){
        return "main";
    }

    @PostMapping
    public String postReq(Model model, MultipartFile file){
        if (!file.isEmpty()
                & file.getOriginalFilename().split("\\.")[1].equals("log")
        ) {
            BufferedOutputStream bos = null;

            try {
                byte[] bytesForUpload = file.getBytes();
                bos = new BufferedOutputStream(
                        new FileOutputStream(
                                new File(FilePath.LOCATION_FILE.getFilePath() + file.getOriginalFilename())));

                bos.write(bytesForUpload);
                bos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            DistanceInfoBuilder dib = new DistanceInfoBuilder(file.getOriginalFilename());
            Set<DistanceInfo> distanceInfoSet = dib.getDistanceInfo();
            model.addAttribute("distanceInfo", distanceInfoSet);
        }


        return "main";
    }
}