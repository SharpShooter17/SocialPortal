package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.SocialPortalUtils;
import com.ujazdowski.SocialPortal.model.forms.NewPhotoForm;
import com.ujazdowski.SocialPortal.model.tables.Pictrue;
import com.ujazdowski.SocialPortal.model.tables.User;
import com.ujazdowski.SocialPortal.repository.PictruesRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
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
    public ModelAndView addPhoto(@ModelAttribute("newPhoto")NewPhotoForm newPhoto, BindingResult result, RedirectAttributes model) throws SQLException {
        logger.info("Work");
        User logged = SocialPortalUtils.getLoggedUser();

        if (result.hasErrors()) {
            logger.error(new String("adduser error") );
            for (ObjectError error: result.getAllErrors()) {
                logger.error(error.getDefaultMessage());
            }
            return new ModelAndView("redirect:/home/profile/" + logged.getUserId().toString(), result.getModel());
        }

        Pictrue pictrue = new Pictrue();
        pictrue.setDate(new Timestamp(new Date().getTime()));
        pictrue.setUser(logged);
        pictrue.setImage( new SerialBlob(newPhoto.getImage()));

        this.pictruesRepository.save(pictrue);

        return new ModelAndView("redirect:/home/profile/" + logged.getUserId().toString());
    }

}
