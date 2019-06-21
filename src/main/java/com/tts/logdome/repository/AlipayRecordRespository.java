package com.tts.logdome.repository;

import com.tts.logdome.data.AlipayRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 支付纪录Respository
 */
public interface AlipayRecordRespository extends JpaRepository<AlipayRecord,String> {



}
