package com.ujazdowski.SocialPortal.controller;

import com.ujazdowski.SocialPortal.repository.PictruesRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
@RequestMapping("/file")
public class FileController {
    private final PictruesRepository pictruesRepository;

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
}
