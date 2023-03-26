package com.intecap.api.tienda.response;

public class MakerResponseRest extends ResponseRest{

    private MakerResponse makerResponse = new MakerResponse();

    public MakerResponse getMakerResponse() {
        return makerResponse;
    }

    public void setMakerResponse(MakerResponse makerResponse) {
        this.makerResponse = makerResponse;
    }
}
