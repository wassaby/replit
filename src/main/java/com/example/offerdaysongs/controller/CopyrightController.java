package com.example.offerdaysongs.controller;

import com.example.offerdaysongs.dto.CopyrightDto;
import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.requests.AddCopyrightToRecordRequest;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.service.CopyrightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/copyrights")
public class CopyrightController {

    @Autowired
    private CopyrightService service;

    @PostMapping
    public CopyrightDto create(@RequestBody CopyrightDto dto) {
        return service.create(dto);
    }

    @PostMapping("/record")
    public RecordingDto add(@RequestBody AddCopyrightToRecordRequest dto) {
        return service.add(dto);
    }

    @PutMapping("/{id}")
    public CopyrightDto update(@PathVariable long id,
                               @RequestBody CopyrightDto dto) {
        return service.update(id, dto);
    }

    @GetMapping("/period")
    public List<CopyrightDto> getAllForPeriod(@RequestParam(defaultValue = "") String from,
                                           @RequestParam(defaultValue = "") String to) {
        return service.getAllForPeriod(from, to);
    }

    @GetMapping("/company/{id}")
    public List<CopyrightDto> getAllOfCompany(@PathVariable long id) {
        return service.getAllOfCompany(id);
    }

}
