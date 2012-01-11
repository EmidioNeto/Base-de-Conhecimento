package br.unijorge.baseconhecimento.controller.business.impl;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Questao;
import br.unijorge.baseconhecimento.persistence.dao.QuestaoDao;
import br.unijorge.baseconhecimento.util.FacesUtil;

public class QuestaoBO {
	QuestaoDao questaoDao = new QuestaoDao();	
	
	public boolean save(Questao questao){
		try {
			questaoDao.insert(questao);
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Cadastrar Quest�o", "N�o foi possivel cadastrar!");
			return false;
		}
	}
	
	public boolean edit(Questao questao){
		try {
			questaoDao.insert(questao);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Nova Quest�o", "Quest�o editada com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar Quest�o", "N�o foi possivel editar!");
			return false;
		}
	}
	
	public boolean excluir(Questao questao) {
		try {
			questaoDao.delete(questao);		
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir Quest�o", "Quest�o exclu�da com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir Quest�o", "N�o foi possivel excluir!");
			return false;
		}	
	}

	
	public Questao obter(Long id) {
		try {
			return questaoDao.findById(id);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	
	public ArrayList<Questao> listar() {
		try {
			return (ArrayList<Questao>) questaoDao.findAll();
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Questao> listarPorFiltro(Questao questao) {
		return (ArrayList<Questao>) questaoDao.findbyFilter(questao);
	}
	
}
