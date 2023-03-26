package com.intecap.api.tienda.response;

import com.intecap.api.tienda.model.MakerModel;

import java.util.List;

public class MakerResponse {
    List<MakerModel> makerModels;

    public List<MakerModel> getMakerModels() {
        return makerModels;
    }

    public void setMakerModels(List<MakerModel> makerModels) {
        this.makerModels = makerModels;
    }
}
