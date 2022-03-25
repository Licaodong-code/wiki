package com.licaodong.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.licaodong.wiki.domain.Ebook;
import com.licaodong.wiki.domain.EbookExample;
import com.licaodong.wiki.mapper.EbookMapper;
import com.licaodong.wiki.req.EbookQueryReq;
import com.licaodong.wiki.req.EbookSaveReq;
import com.licaodong.wiki.resp.EbookQueryResp;
import com.licaodong.wiki.resp.PageResp;
import com.licaodong.wiki.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookQueryResp> list(EbookQueryReq ebookQueryReq) {


        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(ebookQueryReq.getName())){
            criteria.andNameLike("%" + ebookQueryReq.getName() + "%");
        }
        PageHelper.startPage(ebookQueryReq.getPage(), ebookQueryReq.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
//        ArrayList<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
////            EbookResp ebookResp = new EbookResp();
////
////            BeanUtils.copyProperties(ebook, ebookResp);
//            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//            respList.add(ebookResp);
//        }
//        return respList;
        List<EbookQueryResp> ebookQueryResps = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ebookQueryResps);

        return pageResp;
    }

    /**
     * 保存
     */
    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            ebookMapper.insert(ebook);
        } else {
            // 更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }


}
