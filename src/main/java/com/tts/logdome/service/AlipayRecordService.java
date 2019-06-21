package com.tts.logdome.service;

import com.tts.logdome.data.AlipayRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlipayRecordService {

    /**
     * 保存支付记录
     * @param alipayRecord
     */
    void save(AlipayRecord alipayRecord);

    /**
     * 查询支付记录列表
     * @param pageable
     * @return
     */
    Page<AlipayRecord> findAllList(Pageable pageable);

}
