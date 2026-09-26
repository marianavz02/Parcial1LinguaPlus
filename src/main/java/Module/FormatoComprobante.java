package model;

import model.Matricula;

public interface FormatoComprobante {
    String generar(Matricula matricula);
}