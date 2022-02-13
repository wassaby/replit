package com.example.offerdaysongs.dto;

import com.example.offerdaysongs.model.Recording;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class RecordingDto {
    private long id;
    private String title;
    private String version;
    private ZonedDateTime releaseTime;
    private SingerDto singer;
    private List<CopyrightDto> copyrights;

    public static RecordingDto convertToDto(Recording recording) {
        var singer = recording.getSinger();
        return new RecordingDto(recording.getId(),
                recording.getTitle(),
                recording.getVersion(),
                recording.getReleaseTime(),
                singer != null ? new SingerDto(singer.getId(), singer.getName()) : null,
                recording.getCopyrights().stream().map(CopyrightDto::new).collect(Collectors.toList()));
    }
}
