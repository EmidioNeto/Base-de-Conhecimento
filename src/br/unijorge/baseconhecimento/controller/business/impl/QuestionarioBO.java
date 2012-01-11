package br.unijorge.baseconhecimento.controller.business.impl;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import br.unijorge.baseconhecimento.excessao.BusinessExceptions;
import br.unijorge.baseconhecimento.model.entity.Assunto;
import br.unijorge.baseconhecimento.model.entity.Questionario;
import br.unijorge.baseconhecimento.persistence.dao.QuestionarioDao;
import br.unijorge.baseconhecimento.util.FacesUtil;

public class QuestionarioBO{
	QuestionarioDao questionarioDao = new QuestionarioDao();	
	public boolean save(Questionario questionario) {
		try {
			if(questionario.getAssunto()==null){
				FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
						"Cadastrar question�rio", "Assunto � um campo requerido!");
				return false;
			}else if(questionario.getDescricao().equals("")){
				FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
						"Cadastrar question�rio", "Descri��o � um campo requerido!");
				return false;
			}else {
				questionarioDao.insert(questionario);
				return true;
			}			
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Cadastrar question�rio", "N�o foi poss�vel cadastrar!");
			return false;
		}
	}

	public boolean edit(Questionario questionario) {
		try {
			questionarioDao.insert(questionario);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Novo question�rio", "Question�rio editado com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Editar question�rio", "N�o foi poss�vel editar!");
			return false;
		}
	}
	
	public boolean excluir(Questionario questionario) {
		try {
			questionarioDao.delete(questionario);
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir question�rio", "Question�rio exclu�do com sucesso!");
			return true;
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN,
					"Excluir question�rio", "N�o foi poss�vel excluir!");
			return false;
		}		
	}

	
	public Questionario obter(Long id) {
		try {
			return questionarioDao.findById(id);
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}

	
	public ArrayList<Questionario> listar() {
		try {
			return (ArrayList<Questionario>) questionarioDao.findAll();
		} catch (BusinessExceptions e) {
			FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_WARN, "Erro!",
					e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Questionario> listarPorFiltro(Questionario questionario) {
		return (ArrayList<Questionario>) questionarioDao.findbyFilter(questionario);
	}
	
	public ArrayList<Questionario> listarPorAssunto(Assunto assunto) {
		Questionario q = new Questionario();
		q.setAssunto(assunto);
		return (ArrayList<Questionario>) questionarioDao.findbyFilter(q);
	}
}
