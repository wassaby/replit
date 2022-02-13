package com.example.offerdaysongs.dto;

import com.example.offerdaysongs.model.Copyright;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CopyrightDto {
    private Long id;
    private String name;
    private LocalDate createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate expirationDate;
    private Double amount;
    private long companyId;
    private CompanyDto companyDto;

    public static Copyright toCopyright(CopyrightDto dto) {
        Copyright c = new Copyright();
        c.setName(dto.getName());
        c.setAmount(dto.getAmount());
        c.setExpirationDate(dto.getExpirationDate());
        return c;
    }

    public CopyrightDto(Copyright c) {
        this.id = c.getId();
        this.name = c.getName();
        this.amount = c.getAmount();
        this.createdAt = c.getCreatedAt();
        this.expirationDate = c.getExpirationDate();
        this.companyDto = new CompanyDto(c.getCompany().getId(), c.getCompany().getName());
    }
}
