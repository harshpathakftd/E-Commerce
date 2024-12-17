package com.ex.smt.Controller;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ex.smt.Service.CategeoryService;
import com.ex.smt.Service.FileService;
import com.ex.smt.dtos.CategeoryDto;
import com.ex.smt.dtos.ImageRes;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/v1/categeory")
public class CategeoryController {
    @Autowired
    private CategeoryService categeoryService;
    @Autowired
    private FileService fileService;

    @Value("${categeory.image.path}")
    private String imageUploadPath;

    private Logger logger = LoggerFactory.getLogger(CategeoryController.class);

    @PostMapping
    public ResponseEntity<CategeoryDto> create(@RequestBody CategeoryDto categeoryDto) {
        CategeoryDto categeory = categeoryService.createCategeory(categeoryDto);
        return new ResponseEntity<>(categeory, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CategeoryDto>> getAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "0") int pageSize) {
        Page<CategeoryDto> allRecords = categeoryService.getAllRecords(pageNumber, pageSize);
        return new ResponseEntity<>(allRecords, HttpStatus.OK);
    }

    @GetMapping("/{categeoryId}")
    public ResponseEntity<CategeoryDto> getSingleRecord(@PathVariable String categeoryId) {
        CategeoryDto singleCategeory = categeoryService.getSingleCategeory(categeoryId);
        return new ResponseEntity<>(singleCategeory, HttpStatus.OK);
    }

    @DeleteMapping("/{categeoryId}")
    public ResponseEntity<CategeoryDto> deleteRecord(@PathVariable String categeoryId) {
        categeoryService.deleteCategeory(categeoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{categeoryId}")
    public ResponseEntity<CategeoryDto> updateRecord(@RequestBody CategeoryDto categeoryDto,
            @PathVariable String categeoryId) {
        CategeoryDto categeory = categeoryService.updateCategeoryDto(categeoryId, categeoryDto);
        return new ResponseEntity<>(categeory, HttpStatus.OK);
    }

    @PostMapping("/image/{categeoryId}")
    public ResponseEntity<ImageRes> uploadCategeortImage(@RequestParam("categeoryImage") MultipartFile imagFile,
            @PathVariable String categeoryId) {
        String fileName = fileService.FileUpload(imagFile, imageUploadPath);
        CategeoryDto categeory = categeoryService.getSingleCategeory(categeoryId);
        categeory.setCategeoryCoverImage(fileName);
        CategeoryDto categeorySmt = categeoryService.updateCategeoryDto(categeoryId, categeory);
        ImageRes imageRes = ImageRes.builder().imgName(fileName).success(true).status(HttpStatus.CREATED)
                .message("Uploaded").build();
        return new ResponseEntity<>(imageRes, HttpStatus.OK);
    }

    // Serve Image
    @GetMapping("/image/{categeoryId}")
    public void serveUserImg(@PathVariable String categeoryId,
    HttpServletResponse response) throws IOException{
    CategeoryDto categeory = categeoryService.getSingleCategeory(categeoryId);
    logger.info("Categeory Image : {}" ,categeory.getCategeoryCoverImage());
    InputStream resource = fileService.getResource(imageUploadPath,
    categeory.getCategeoryCoverImage());
    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
    StreamUtils.copy(resource, response.getOutputStream());    
    }



    

    

}
