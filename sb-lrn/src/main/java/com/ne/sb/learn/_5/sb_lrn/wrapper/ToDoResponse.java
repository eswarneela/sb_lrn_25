package com.ne.sb.learn._5.sb_lrn.wrapper;

import com.ne.sb.learn._5.sb_lrn.models.JsonToDo;

import java.util.List;


public class ToDoResponse {

    private List<JsonToDo> todos;
    private int total;
    private int skip;
    private int limit;

    public List<JsonToDo> getTodos() {
        return todos;
    }

    public void setToDos(List<JsonToDo> todos) {
        this.todos = todos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "ToDoResponse{" +
                "toDos=" + todos +
                ", total=" + total +
                ", skip=" + skip +
                ", limit=" + limit +
                '}';
    }


}
