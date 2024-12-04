package com.example.spring.store.datasource.repository;

import com.example.spring.base.dto.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberVO> findAll();
}
