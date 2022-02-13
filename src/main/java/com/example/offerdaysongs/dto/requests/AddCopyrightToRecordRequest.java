package com.example.offerdaysongs.dto.requests;

import lombok.Data;

import java.util.List;

@Data
public class AddCopyrightToRecordRequest {
    private long recordId;
    private List<Long> copyrightIds;
}
