package com.licaodong.wiki.controller;

import com.licaodong.wiki.req.CategoryQueryReq;
import com.licaodong.wiki.req.CategorySaveReq;
import com.licaodong.wiki.resp.CommonResp;
import com.licaodong.wiki.resp.CategoryQueryResp;
import com.licaodong.wiki.resp.PageResp;
import com.licaodong.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq categoryQueryReq) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> pageResp = categoryService.list(categoryQueryReq);
        resp.setContent(pageResp);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}
