package com.example.paris_janitor_api.core.mapper;


import com.example.paris_janitor_api.core.model.Stage;
import com.example.paris_janitor_api.framework.db.StageDocument;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StageMapper {

    public StageDocument toDocument(Stage stage) {
        if(stage == null) return null;
        return new StageDocument(
        stage.getStatus(),
        stage.getCreatedAt());
    }

    public Stage toModel(StageDocument stageDocument) {
        if (stageDocument == null) {
            return null;
        }
        return new Stage(
                stageDocument.getStatus(),
                stageDocument.getCreatedAt()
        );
    }

    public StageDocument[] toDocumentArray(Stage[] stages) {
        if(stages == null) return null;
        return Arrays.stream(stages)
                .map(this::toDocument)
                .toArray(StageDocument[]::new);
    }



    public Stage[] toModelArray(StageDocument[] stageDocuments) {
        if(stageDocuments == null) return null;
        return Arrays.stream(stageDocuments)
                .map(this::toModel)
                .toArray(Stage[]::new);
    }
}
