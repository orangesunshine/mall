package com.orange.service;

import com.orange.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

public interface MemberReadHistoryService {
    /**
     * 新增浏览记录
     *
     * @return
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 批量删除浏览记录
     *
     * @return
     */
    int delete(List<String> ids);

    /**
     * 获取用户浏览记录
     *
     * @param memberId
     * @return
     */
    List<MemberReadHistory> list(Long memberId);
}
