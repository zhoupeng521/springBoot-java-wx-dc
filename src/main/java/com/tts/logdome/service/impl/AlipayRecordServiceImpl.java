package com.tts.logdome.service.impl;

import com.tts.logdome.data.AlipayRecord;
import com.tts.logdome.repository.AlipayRecordRespository;
import com.tts.logdome.service.AlipayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlipayRecordServiceImpl implements AlipayRecordService {

    @Autowired
    private AlipayRecordRespository alipayRecordRespository;

    @Override
    public void save(AlipayRecord alipayRecord) {
        alipayRecordRespository.save(alipayRecord);
    }

    @Override
    public Page<AlipayRecord> findAllList(Pageable pageable) {
        Page<AlipayRecord> alipayRecordPage = alipayRecordRespository.findAll(pageable);
        return alipayRecordPage;
    }
}
