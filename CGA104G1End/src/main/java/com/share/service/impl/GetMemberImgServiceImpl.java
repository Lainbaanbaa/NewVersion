package com.share.service.impl;

import com.article_identity.model.Article_identityDAO;
import com.share.service.GetMemberImgService;

public class GetMemberImgServiceImpl implements GetMemberImgService {

    private Article_identityDAO dao;

    public GetMemberImgServiceImpl() {
        dao = new Article_identityDAO();
    }

    @Override
    public boolean getMemPic(Integer memId) {
        try {
           dao.findByPrimaryKey(memId).getArticle_pic();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
