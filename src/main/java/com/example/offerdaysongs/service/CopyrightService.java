package com.example.offerdaysongs.service;

import com.example.offerdaysongs.controller.RecordingController;
import com.example.offerdaysongs.dto.CopyrightDto;
import com.example.offerdaysongs.dto.RecordingDto;
import com.example.offerdaysongs.dto.requests.AddCopyrightToRecordRequest;
import com.example.offerdaysongs.model.Company;
import com.example.offerdaysongs.model.Copyright;
import com.example.offerdaysongs.model.Recording;
import com.example.offerdaysongs.repository.CompanyRepository;
import com.example.offerdaysongs.repository.CopyrightRepository;
import com.example.offerdaysongs.repository.RecordingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CopyrightService {
    @Autowired
    CopyrightRepository repository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    RecordingRepository recordingRepository;

    public CopyrightDto create(CopyrightDto dto) {
        Copyright c = CopyrightDto.toCopyright(dto);
        Optional<Company> companyOptional = companyRepository.findById(dto.getCompanyId());
        if (companyOptional.isPresent())
            c.setCompany(companyOptional.get());
        return new CopyrightDto(repository.save(c));
    }

    public RecordingDto add(AddCopyrightToRecordRequest dto) {
        Optional<Recording> recordingOptional = recordingRepository.findById(dto.getRecordId());
        List<Copyright> copyrights = repository.findAllById(dto.getCopyrightIds());
        recordingOptional.get().setCopyrights(copyrights);
        return RecordingDto.convertToDto(recordingRepository.save(recordingOptional.get()));
    }

    public CopyrightDto update(long id, CopyrightDto dto) {
        Optional<Copyright> copyrightOptional = repository.findById(id);
        if (copyrightOptional.isEmpty())
            return null;
        Copyright c = copyrightOptional.get();
        if (dto.getExpirationDate() != null)
            c.setExpirationDate(dto.getExpirationDate());
        if (dto.getName() != null)
            c.setName(dto.getName());
        if (dto.getAmount() != null)
            c.setAmount(dto.getAmount());

        Optional<Company> companyOptional = companyRepository.findById(dto.getCompanyId());
        if (companyOptional.isPresent())
            c.setCompany(companyOptional.get());

        return new CopyrightDto(repository.save(c));
    }

    public List<CopyrightDto> getAllForPeriod(String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        Specification<Copyright> spec = Specification.where(CopyrightRepository.betweenDates(fromDate, toDate));
        List<Copyright> copyrights = repository.findAll(spec);
        return copyrights.stream().map(CopyrightDto::new).collect(Collectors.toList());
    }

    public List<CopyrightDto> getAllOfCompany(long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isEmpty())
            return null;
        Company c = companyOptional.get();
        return c.getCopyrights().stream().map(CopyrightDto::new).collect(Collectors.toList());
    }
}
