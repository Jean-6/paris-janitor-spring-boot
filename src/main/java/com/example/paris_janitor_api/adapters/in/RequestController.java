package com.example.paris_janitor_api.adapters.in;


import com.example.paris_janitor_api.application.port.out.request.*;
import com.example.paris_janitor_api.core.model.Request;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/request")
@Tag(name = "Request API", description = "Client request management")
public class RequestController {

    private final DeleteByIdRequestPort deleteByIdRequestPort;
    private final UpdateRequestPort updateRequestPort;
    private final LoadRequestsPort loadRequestsPort;
    private final LoadRequestByIdPort loadRequestByIdPort;
    private final PersistRequestPort persistRequestPort;

    public RequestController(DeleteByIdRequestPort deleteByIdRequestPort, UpdateRequestPort updateRequestPort, LoadRequestsPort loadRequestsPort, LoadRequestByIdPort loadRequestByIdPort, PersistRequestPort persistRequestPort) {
        this.deleteByIdRequestPort = deleteByIdRequestPort;
        this.updateRequestPort = updateRequestPort;
        this.loadRequestsPort = loadRequestsPort;
        this.loadRequestByIdPort = loadRequestByIdPort;
        this.persistRequestPort = persistRequestPort;
    }

    @Operation(summary = "Create a new client request", description = "Save a new request in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Request>> save(@RequestBody Request request) {

      return persistRequestPort.saveRequest(request)
              .map(requestSaved ->{
                  log.info(requestSaved.toString());
                  return ResponseEntity.status(HttpStatus.CREATED)
                          .body(requestSaved);
              })
              .defaultIfEmpty(ResponseEntity.badRequest().build())
              .onErrorResume(err->{
                  log.error(err.toString());
                  return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
              });
    }

    @Operation(summary = "Retrieve a specific client request", description = "Get a request from the system using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Request not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Request>> getRequestById(@PathVariable("id") String id) {

        return loadRequestByIdPort.findById(id)
                .map(request -> {
                    log.info(request.toString());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(request);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(err->{
                    log.error(err.toString());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }


    @Operation(summary = "Retrieve client requests", description = "Get all requests stored in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requests retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<Request>> getRequests() {

        Flux<Request> requests = loadRequestsPort.findAll()
                .doOnNext(request -> {log.info(request.toString());})
                .onErrorResume(err->{
                    log.error(err.getMessage());
                    return Flux.error(err);
                });
        return ResponseEntity.status(HttpStatus.OK).body(requests);
    }


    @Operation(summary = "Update client request", description = "Update an existing request in the system using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request updated successfully"),
            @ApiResponse(responseCode = "404", description = "Request not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Request>> updateRequest(@RequestParam("id") String id, @RequestBody Request request) {

        return updateRequestPort.findByIdAndUpdate(id,request)
                .map(request1 -> {
                    log.info(request1.toString());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(request1);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorResume(err->{
                    log.error(err.toString());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @Operation(summary = "Delete client request", description = "Delete an existing request from the system using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Request not found",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Request>> deleteRequest(@RequestParam("id") String id) {

        return deleteByIdRequestPort.deleteById(id)
                .map(request -> {
                    log.debug(request.toString());
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(request);
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .onErrorResume(err->{
                    log.error(err.toString());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });

    }

}
