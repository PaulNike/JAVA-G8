package com.codigo.retrofit.service;

import com.codigo.retrofit.aggregates.response.ResponseSunat;

import java.io.IOException;

public interface SunatService {

    ResponseSunat getInfoSunat(String ruc) throws IOException;
}
