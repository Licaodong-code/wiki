package com.licaodong.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.licaodong.wiki.domain.Category;
import com.licaodong.wiki.domain.CategoryExample;
import com.licaodong.wiki.mapper.CategoryMapper;
import com.licaodong.wiki.req.CategoryQueryReq;
import com.licaodong.wiki.req.CategorySaveReq;
import com.licaodong.wiki.resp.CategoryQueryResp;
import com.licaodong.wiki.resp.PageResp;
import com.licaodong.wiki.utils.CopyUtil;
import com.licaodong.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public PageResp<CategoryQueryResp> list(CategoryQueryReq categoryQueryReq) {


        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        PageHelper.startPage(categoryQueryReq.getPage(), categoryQueryReq.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryQueryResp> categoryQueryResps = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());

        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(categoryQueryResps);

        return pageResp;
    }

    /**
     * 保存
     */
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            long id = snowFlake.nextId();
            id >>= 10;
            // Long类型与前端的Number的精度问题会导致id不一致现象
            category.setId(id);
            categoryMapper.insert(category);

        } else {
            // 更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    public void delete(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }


}
