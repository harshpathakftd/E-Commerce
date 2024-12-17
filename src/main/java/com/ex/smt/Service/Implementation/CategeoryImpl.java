package com.ex.smt.Service.Implementation;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ex.smt.Exception.ResourceNotFoundException;
import com.ex.smt.Repository.CategeoryRepo;
import com.ex.smt.Service.CategeoryService;
import com.ex.smt.dtos.CategeoryDto;
import com.ex.smt.entities.Categeory;
import com.ex.smt.Config.*;

@Service
public class CategeoryImpl implements CategeoryService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CategeoryRepo categeoryRepo;

    @Override
    public CategeoryDto createCategeory(CategeoryDto categeoryDto) {
        String categeoryId = UUID.randomUUID().toString();
        categeoryDto.setCategeoryId(categeoryId);
        Categeory categeory = mapper.map(categeoryDto, Categeory.class);
        Categeory save = categeoryRepo.save(categeory);
        return mapper.map(save, CategeoryDto.class);
    }

    @Override
    public CategeoryDto getSingleCategeory(String categeoryId) {
        Categeory categeory = categeoryRepo.findById(categeoryId).orElseThrow(()-> new ResourceNotFoundException(AppConfig.notFoudMessage));
        return mapper.map(categeory, CategeoryDto.class);
    }

    @Override
    public CategeoryDto updateCategeoryDto(String categeoryId, CategeoryDto categeoryDto) {
        Categeory categeory = categeoryRepo.findById(categeoryId).orElseThrow(()-> new ResourceNotFoundException(AppConfig.notFoudMessage));
        categeory.setCategeoryName(categeoryDto.getCategeoryName());
        categeory.setCategeoryDesc(categeoryDto.getCategeoryDesc());
        categeory.setCategeoryCoverImage(categeoryDto.getCategeoryCoverImage());
        Categeory save = categeoryRepo.save(categeory);
        return mapper.map(save, CategeoryDto.class);

    }

    @Override
    public void deleteCategeory(String categeoryId) {
        Categeory categeory = categeoryRepo.findById(categeoryId).orElseThrow(()-> new ResourceNotFoundException(AppConfig.notFoudMessage));
        categeoryRepo.delete(categeory);
    }

    @Override
    public Page<CategeoryDto> getAllRecords(int pageNumber, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<Categeory> all = categeoryRepo.findAll(pageable);
        Page<CategeoryDto> categeoryRecords = all.map(categeory -> mapper.map(categeory, CategeoryDto.class));
        return categeoryRecords;
    }
    
}
