package com.licaodong.wiki.controller;

import com.licaodong.wiki.resp.CommonResp;
import com.licaodong.wiki.resp.EbookQueryResp;
import com.licaodong.wiki.resp.PageResp;
import com.licaodong.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.licaodong.wiki.req.EbookQueryReq;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookQueryReq ebookQueryReq) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> pageResp = ebookService.list(ebookQueryReq);
        resp.setContent(pageResp);
        return resp;
    }
}
