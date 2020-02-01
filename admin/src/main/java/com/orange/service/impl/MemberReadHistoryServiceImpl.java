package com.orange.service.impl;

import com.orange.nosql.mongodb.document.MemberReadHistory;
import com.orange.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.orange.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {
    @Autowired
    MemberReadHistoryRepository repository;

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        MemberReadHistory save = repository.save(memberReadHistory);
        return null == save ? 0 : 1;
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> memberReadHistories = new ArrayList<>();
        for (String id : ids) {
            MemberReadHistory history = new MemberReadHistory();
            history.setId(id);
            memberReadHistories.add(history);
        }
        repository.deleteAll(memberReadHistories);
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return repository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
