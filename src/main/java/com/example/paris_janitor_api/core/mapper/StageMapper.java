package com.example.paris_janitor_api.core.mapper;


import com.example.paris_janitor_api.core.model.Stage;
import com.example.paris_janitor_api.framework.db.StageDocument;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StageMapper {

    public StageDocument toDocument(Stage stage) {
        if(stage == null) return null;
        return new StageDocument(
        stage.getStatus(),
        stage.getCreatedAt());
    }

    public Stage toModel(StageDocument stageDocument) {
        if (stageDocument == null)return null;

        return new Stage(
                stageDocument.getStatus(),
                stageDocument.getCreatedAt()
        );
    }

    public List<StageDocument> toDocumentArray(List<Stage> stages) {
        if(stages == null) return null;
        return stages.stream()
                .map(this::toDocument)
                .collect(Collectors.toList());
    }

    public List<Stage> toModelArray(List<StageDocument> stageDocuments) {
        if(stageDocuments == null) return null;
        return stageDocuments.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
