package com.licaodong.wiki.service;

import com.licaodong.wiki.domain.Ebook;
import com.licaodong.wiki.domain.EbookExample;
import com.licaodong.wiki.mapper.EbookMapper;
import com.licaodong.wiki.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import req.EbookReq;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq ebookReq) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + ebookReq.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
        ArrayList<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();

            BeanUtils.copyProperties(ebook, ebookResp);
            ebookResp.setId(123L);
            respList.add(ebookResp);
        }
        return respList;
    }
}
