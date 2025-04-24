package com.ne.sb.learn._5.sb_lrn.services;

import com.ne.sb.learn._5.sb_lrn.models.JsonToDo;
import com.ne.sb.learn._5.sb_lrn.wrapper.ToDoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FetchService {

    private final RestClient restClient;

    public FetchService(RestClient restClient) {
        this.restClient = restClient;
    }
//    Optional<List<JsonToDo>> optionalJsonToDo;
    public List<JsonToDo> outputFromFetchedData(String url){
//        orestClient.get().uri(url)
//                .retrieve()
//                .body(ToDoResponse.class)
//                .getToDos());

        Optional<List<JsonToDo>> optionalJsonToDo = Optional.ofNullable(restClient.get()
                .uri(url)
                .retrieve()
                .body(ToDoResponse.class).getTodos());

        return optionalJsonToDo.map(list -> list.stream()
                    .filter(todo -> todo.getTodo().startsWith("B"))
                    .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
