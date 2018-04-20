package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.SocialPortalUtils;
import com.ujazdowski.SocialPortal.exceptions.FileNotValidException;
import com.ujazdowski.SocialPortal.model.tables.ImgType;
import com.ujazdowski.SocialPortal.model.tables.Pictrue;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.ImgTypesRepository;
import com.ujazdowski.SocialPortal.repository.PictruesRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private final PictruesRepository pictruesRepository;
    private final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final ImgTypesRepository imgTypesRepository;

    public FileController(PictruesRepository pictruesRepository, ImgTypesRepository imgTypesRepository) {
        this.pictruesRepository = pictruesRepository;
        this.imgTypesRepository = imgTypesRepository;
    }

    @RequestMapping(value = "/user/pictrue/{img}", method = RequestMethod.GET)
    public void getUserImage(@PathVariable("img") Long img, HttpServletResponse response) throws Exception {
        Pictrue pic = this.pictruesRepository.findOne(img);
        Blob image = pic.getImage();
        InputStream in = image.getBinaryStream();
        try {
            response.setContentType("image/".concat(pic.getImgType().getType().toLowerCase()));
            IOUtils.copy(in, response.getOutputStream());
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    @RequestMapping(value = "/user/pictrue/", method = RequestMethod.POST)
    public ModelAndView addPhoto(@RequestParam("file") MultipartFile file) throws SQLException, IOException, FileNotValidException {
        ImgType format = this.getFormat(file.getOriginalFilename());
        if (file.isEmpty() || ( format == null)){
            throw new FileNotValidException();
        }

        User logged = SocialPortalUtils.getLoggedUser();


        Pictrue pictrue = new Pictrue();
        pictrue.setDate(new Timestamp(new Date().getTime()));
        pictrue.setUser(logged);
        pictrue.setImage(new SerialBlob(file.getBytes()));
        pictrue.setImgType(format);

        this.pictruesRepository.save(pictrue);

        return new ModelAndView("redirect:/home/profile/" + logged.getUserId().toString());
    }

    private ImgType getFormat(String filename){
        filename = filename.toUpperCase();
        List<ImgType> types = this.imgTypesRepository.findAll();

        for (ImgType type: types ) {
            if (filename.contains("." + type.getType())){
                return type;
            }
        }

        return null;
    }
}