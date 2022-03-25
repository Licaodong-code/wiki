package com.licaodong.wiki.controller;

import com.licaodong.wiki.resp.CommonResp;
import com.licaodong.wiki.resp.EbookResp;
import com.licaodong.wiki.resp.PageResp;
import com.licaodong.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import req.EbookReq;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq ebookReq) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> pageResp = ebookService.list(ebookReq);
        resp.setContent(pageResp);
        return resp;
    }
}
