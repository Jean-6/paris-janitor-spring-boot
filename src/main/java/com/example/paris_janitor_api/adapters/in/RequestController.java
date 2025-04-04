package com.example.paris_janitor_api.adapters.in;


import com.example.paris_janitor_api.application.port.out.request.*;
import com.example.paris_janitor_api.core.model.Request;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RequestMapping("/api/request")
public class RequestController {


    private final DeleteByIdRequestPort deleteByIdRequestPort;
    private final UpdateRequestPort updateRequestPort;
    private final LoadRequestsPort loadRequestsPort;
    private final LoadRequestByIdPort loadRequestByIdPort;
    private final PersistRequestPort persistRequestPort;


    static Logger logger = LoggerFactory.getLogger(RequestController.class);

    public RequestController(DeleteByIdRequestPort deleteByIdRequestPort, UpdateRequestPort updateRequestPort, LoadRequestsPort loadRequestsPort, LoadRequestByIdPort loadRequestByIdPort, PersistRequestPort persistRequestPort) {
        this.deleteByIdRequestPort = deleteByIdRequestPort;
        this.updateRequestPort = updateRequestPort;
        this.loadRequestsPort = loadRequestsPort;
        this.loadRequestByIdPort = loadRequestByIdPort;
        this.persistRequestPort = persistRequestPort;
    }

    @PostMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Request>> save(@RequestBody Request request) {

      return persistRequestPort.saveRequest(request)
              .map(requestSaved ->{
                  logger.info(requestSaved.toString());
                  return ResponseEntity.status(HttpStatus.CREATED)
                          .body(requestSaved);
              })
              .defaultIfEmpty(ResponseEntity.badRequest().build())
              .onErrorResume(err->{
                  logger.error(err.toString());
                  return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
              });
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Request>> getRequestById(@PathVariable("id") String id) {

        //if(id.isBlank()) return Mono.just(ResponseEntity.badRequest().build());
        return loadRequestByIdPort.findById(id)
                .map(request -> {
                    logger.info(request.toString());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(request);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(err->{
                    logger.error(err.toString());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Request>> getRequests() {

        Flux<Request> requests = loadRequestsPort.findAll()
                .doOnNext(request -> {logger.info(request.toString());})
                .onErrorResume(err->{
                    logger.error(err.getMessage());
                    return Flux.error(err);
                });
        return ResponseEntity.status(HttpStatus.OK).body(requests);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Request>> updateRequest(@RequestParam("id") String id, @RequestBody Request request) {

        return updateRequestPort.findByIdAndUpdate(id,request)
                .map(request1 -> {
                    logger.info(request1.toString());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(request1);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(err->{
                    logger.error(err.toString());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Request>> deleteRequest(@RequestParam("id") String id) {

        return deleteByIdRequestPort.deleteById(id)
                .map(request -> {
                    logger.debug(request.toString());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(request);
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .onErrorResume(err->{
                    logger.error(err.toString());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });

    }

}
