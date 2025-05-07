package com.example.paris_janitor_api.infrastructure.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class HttpPropertyAttachmentSender {

    private final WebClient webClient;
    private final String attachmentServiceUrl;


    public HttpPropertyAttachmentSender(WebClient.Builder webClientBuilder,
                                        @Value("${external.attachment-service.url}") String attachmentServiceUrl
                                        ) {
        this.attachmentServiceUrl = attachmentServiceUrl;
        this.webClient = webClientBuilder.build();
    }

    public Mono<Void> sendPictures(String propertyId, Flux<FilePart> files) {
        log.debug("send pictures");
        return sendFiles(propertyId, files,"/api/attachment/upload/img","pictures", MediaType.IMAGE_JPEG);
    }

    public Mono<Void> sendDocuments(String propertyId, Flux<FilePart> files) {
        log.debug("send documents");
        return sendFiles(propertyId,files,"/api/attachment/upload/doc","documents", MediaType.APPLICATION_PDF);
    }
    public Mono<Void> sendFiles(String propertyId, Flux<FilePart> files, String path, String partName, MediaType mediaType) {

        log.debug(attachmentServiceUrl + path + "/" + propertyId);
        return files.collectList().flatMap(fileList -> {
            log.debug("fileList : "+fileList.toString());
            MultipartBodyBuilder builder = new MultipartBodyBuilder();



            for (FilePart file : fileList) {
                builder.part(partName, file)
                        .filename(file.filename()) // Important
                        .headers(h -> h.setContentDispositionFormData(partName, file.filename()))
                        .contentType(mediaType); // facultatif si déjà connu
            }

            MultiValueMap<String, HttpEntity<?>> multipartData = builder.build();

            return webClient.post()
                    .uri(attachmentServiceUrl + path + "/" + propertyId)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(multipartData))
                    .retrieve()
                    .bodyToMono(Void.class);
        });
    }

}
