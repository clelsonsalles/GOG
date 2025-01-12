/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xti.ouvidoria.model.enums;

/**
 *
 * @author renato
 */
public enum ParametroEnum {
    NUMERO_SEQUENCIAL_MANIFESTACAO(1,"Sequencial da Manifestação"),
    ANO_SEQUENCIAL_MANIFESTACAO(2,"Ano atual"),
    DIRETORIO_ANEXOS(3,"Diretório para onde serão enviados os arquivos anexados"),
    URL_SISTEMA(4,"URL base do Sistema");
    
    private Integer id;
    private String descricao;

    private ParametroEnum(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
}
