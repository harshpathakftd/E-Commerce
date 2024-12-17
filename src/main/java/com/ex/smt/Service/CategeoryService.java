package com.ex.smt.Service;

import org.springframework.data.domain.Page;

import com.ex.smt.dtos.CategeoryDto;

public interface CategeoryService {
     CategeoryDto createCategeory(CategeoryDto categeoryDto);
     CategeoryDto getSingleCategeory(String categeoryId);
     CategeoryDto updateCategeoryDto(String categeoryId , CategeoryDto categeoryDto);
     void deleteCategeory(String categeoryId);
     Page<CategeoryDto> getAllRecords(int pageNumber , int pageSize);
} 