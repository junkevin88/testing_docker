package com.binar5.apps.controller.web;

import com.binar5.apps.entity.Karyawan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/v1/web/index/")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/halaman1", method = RequestMethod.GET)
    public String pricacy() {
        try {
            logger.info("sukses privacy");// sukses
            if (true) { // ada warning
                logger.warn("warning privacy"); // kuning
            }
        } catch (Exception e) {
            logger.error("warning privacy");// merah
        }
        return "index";
    }

    @RequestMapping(value = "/halaman2", method = RequestMethod.GET)
    public String pricacy(Model model) {
        List<Karyawan> data = new ArrayList<>();
        data.add(new Karyawan("nama1"));
        data.add(new Karyawan("nama2"));
        data.add(new Karyawan("nama2"));
        logger.info("sukses karyawan");
        model.addAttribute("listData", data);
        return "karyawan";
    }
}
