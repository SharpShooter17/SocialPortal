package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.SocialPortalUtils;
import com.ujazdowski.SocialPortal.exceptions.FileNotValidException;
import com.ujazdowski.SocialPortal.model.forms.NewPhotoForm;
import com.ujazdowski.SocialPortal.model.tables.Pictrue;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.PictruesRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class FileController {
    private final PictruesRepository pictruesRepository;
    private final Logger logger = LoggerFactory.getLogger(FileController.class);

    public FileController(PictruesRepository pictruesRepository) {
        this.pictruesRepository = pictruesRepository;
    }

    @RequestMapping(value = "/user/pictrue/{img}", method = RequestMethod.GET)
    public byte[] getUserImage(@PathVariable("img") Long img, HttpServletResponse response) throws SQLException, Exception {
        Blob image = this.pictruesRepository.findOne(img).getImage();
        IOUtils.copy(image.getBinaryStream(), response.getOutputStream());
        response.flushBuffer();
        response.setContentType("image/png");

        return image.getBytes(1, (int) image.length());
    }

    @RequestMapping(value = "/user/pictrue/", method = RequestMethod.POST)
    public ModelAndView addPhoto(@RequestParam("file") MultipartFile file) throws SQLException, IOException, FileNotValidException {
        if (file.isEmpty()){
            throw new FileNotValidException();
        }

        User logged = SocialPortalUtils.getLoggedUser();

        Pictrue pictrue = new Pictrue();
        pictrue.setDate(new Timestamp(new Date().getTime()));
        pictrue.setUser(logged);
        pictrue.setImage(new SerialBlob(file.getBytes()));

        this.pictruesRepository.save(pictrue);

        return new ModelAndView("redirect:/home/profile/" + logged.getUserId().toString());
    }

}
