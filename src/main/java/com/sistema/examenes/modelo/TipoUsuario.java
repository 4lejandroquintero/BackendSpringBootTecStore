package com.sistema.examenes.modelo;

public enum TipoUsuario {
    ADMIN(1L),
    NORMAL(2L);


    private Long codigo;

    TipoUsuario(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

}
