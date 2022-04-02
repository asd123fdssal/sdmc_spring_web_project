package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.dto.CrawlingDto;
import com.arsud.sdmc_spring_web_project.entity.Company;
import com.arsud.sdmc_spring_web_project.entity.Title;
import com.arsud.sdmc_spring_web_project.service.CompanyService;
import com.arsud.sdmc_spring_web_project.service.TitleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@AllArgsConstructor
public class CrawlingController {

    private final TitleService titleService;
    private final CompanyService companyService;

    @GetMapping("/game/title/getchu")
    public String getchu(
            Model model
    ){
        model.addAttribute("CrawlingDto", new CrawlingDto());
        return "/game/title/getchu";
    }

    @PostMapping("/game/title/getchu")
    public String newTitle(
            @ModelAttribute("CrawlingDto") CrawlingDto crawlingDto
    ){
        System.out.println(crawlingDto.getUrl());
        Title title = getTitle(crawlingDto.getUrl(), crawlingDto.getKorName(), crawlingDto.getReleaseDate());

        if (title != null) {
            String comName = title.getCompany_name();
            Company company = companyService.findCompanyByKorNameOrOrgName(comName, comName);
            if(company == null){
                company = companyService.save(comName, comName);
            }
            title.setCompany(company);
            titleService.save(
                    title.getCompany(),
                    null,
                    title.getPicture(),
                    title.getReleaseDate(),
                    title.getKorName(),
                    title.getOrgName(),
                    "",
                    ""
            );
        }
        return "redirect:/game/title/list";
    }

    public static Title getTitle(String url, String korName, String date) {

        Connection conn = Jsoup.connect(url);

        try {
            Document document = conn.get();
            Elements images = document.selectXpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div/label/div/img");

            String imgUrl = images.get(0).attr("abs:src");
            byte[] image = Jsoup.connect(imgUrl)
                    .userAgent("Mozilla/5.0")
                    .ignoreContentType(true)
                    .execute().bodyAsBytes();

            Elements title = document.selectXpath("//*[@id=\"maincontent\"]/div[2]/h2");
            String name = title.get(0).childNode(0).toString();

            String company_name = document.selectXpath("//*[@id=\"maincontent\"]/div[2]/div[1]/table/tbody/tr[4]/td[2]/a").get(0).childNode(0).toString();

            return Title.builder()
                    .picture(image)
                    .releaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(date))
                    .korName(korName)
                    .orgName(name)
                    .hookCode("")
                    .etc("")
                    .company_name(company_name)
                    .build();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] downloadImage(String strImageURL){

        //get file name from image path
        String strImageName =
                strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );

        try {

            //open the stream from URL
            URL urlImage = new URL(strImageURL);
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
