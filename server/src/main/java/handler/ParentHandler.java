package handler;

import service.ParentService;

public class ParentHandler {

    ParentService service;

    public ParentHandler() {
        service = new ParentService();
    }
}
