/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.xti.ouvidoria.dao;

import java.util.Calendar;

import javax.ejb.Stateless;

import br.com.xti.ouvidoria.exception.InfrastructureException;
import br.com.xti.ouvidoria.model.TbParametro;
import br.com.xti.ouvidoria.model.enums.ParametroEnum;

/**
 *
 * @author renato
 */
@Stateless
public class ParametroDAO extends AbstractDAO<TbParametro> {

    public ParametroDAO() {
        super(TbParametro.class);
    }

    @Override
    public String getNomeEntidade() {


        return "Parâmetro";
    }

    public synchronized Integer getProximoNumeroManifestacao() throws InfrastructureException {
        Integer anoAtual = getAnoAtual();
        TbParametro parametroSequencial = find(ParametroEnum.NUMERO_SEQUENCIAL_MANIFESTACAO.getId());

        Integer numero = Integer.parseInt(parametroSequencial.getVlrParametro());
        numero++;

        parametroSequencial.setVlrParametro(numero.toString());
        edit(parametroSequencial);
        return Integer.parseInt(String.format("%s%06d", anoAtual, numero));
    }

    public synchronized Integer getAnoAtual() throws InfrastructureException {
        TbParametro parametroAno = find(ParametroEnum.ANO_SEQUENCIAL_MANIFESTACAO.getId());
        Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        if (parametroAno != null) {
            Integer anoBanco = Integer.parseInt(parametroAno.getVlrParametro());
            if (!(anoAtual.equals(anoBanco))) {
                parametroAno.setVlrParametro(anoAtual + "");
                edit(parametroAno);
                TbParametro parametroSequencial = find(ParametroEnum.NUMERO_SEQUENCIAL_MANIFESTACAO.getId());
                parametroSequencial.setVlrParametro("0");
                edit(parametroSequencial);
            }
        } else {
            parametroAno = new TbParametro();
            parametroAno.setIdParametro(ParametroEnum.ANO_SEQUENCIAL_MANIFESTACAO.getId());
            parametroAno.setNmParametro(ParametroEnum.ANO_SEQUENCIAL_MANIFESTACAO.getDescricao());
            parametroAno.setVlrParametro(anoAtual + "");
            create(parametroAno);

            TbParametro parametroSequencial = new TbParametro();
            parametroSequencial.setIdParametro(ParametroEnum.NUMERO_SEQUENCIAL_MANIFESTACAO.getId());
            parametroSequencial.setNmParametro(ParametroEnum.NUMERO_SEQUENCIAL_MANIFESTACAO.getDescricao());
            parametroSequencial.setVlrParametro("0");
            create(parametroSequencial);
        }

        return anoAtual;
    }

    public String getDiretorioAnexo() throws InfrastructureException {
        TbParametro diretorioAnexo = find(ParametroEnum.DIRETORIO_ANEXOS.getId());
        if(diretorioAnexo == null){
            diretorioAnexo = new TbParametro();
            diretorioAnexo.setIdParametro(ParametroEnum.DIRETORIO_ANEXOS.getId());
            diretorioAnexo.setNmParametro(ParametroEnum.DIRETORIO_ANEXOS.getDescricao());
            diretorioAnexo.setVlrParametro("/tmp/");
            create(diretorioAnexo);
        }
        return diretorioAnexo.getVlrParametro();
    }
    
    public String getUrlBase() throws InfrastructureException {
        TbParametro urlBase = find(ParametroEnum.URL_SISTEMA.getId());
        if(urlBase == null){
            urlBase = new TbParametro();
            urlBase.setIdParametro(ParametroEnum.URL_SISTEMA.getId());
            urlBase.setNmParametro(ParametroEnum.URL_SISTEMA.getDescricao());
            urlBase.setVlrParametro("http://127.0.0.1:8080/");
            create(urlBase);
        }
        return urlBase.getVlrParametro();
    }
}
