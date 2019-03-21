/*******************************************************************************
 * Copyright (C) 2017 Albuquerque Lucas
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package repositorios;

import componentes.IComponente;
import componentes.IEnlace;
import programa.Programa;

public class RepositorioEnlacesLista implements RepositorioEnlaces {
	private IEnlace en;
	private RepositorioEnlacesLista prox;

	public RepositorioEnlacesLista() {
		this.en = null;
		this.prox = null;
	}

	public void inserir(IEnlace en) {
		if (this.en == null) {
			this.en = en;
			this.prox = new RepositorioEnlacesLista();
		} else {
			this.prox.inserir(en);
		}
	}

	public IEnlace procurar(IEnlace en) throws ComponenteNaoEncontradoException {
		IEnlace resposta = null;
		if (this.en != null) {
			if (this.en.equals(en)) {
				resposta = this.en;
			} else {
				resposta = this.prox.procurar(en);
			}
		} else {
			throw new ComponenteNaoEncontradoException();
		}
		return resposta;
	}

	public void remover(IEnlace en) throws ComponenteNaoEncontradoException {
		if (this.en != null) {
			if (this.en.equals(en)) {
				this.en = this.prox.en;
				this.prox = this.prox.prox;
			} else {
				this.prox.remover(en);
			}
		} else {
			throw new ComponenteNaoEncontradoException();
		}
	}

	public void atualizar(IEnlace en) throws ComponenteNaoEncontradoException {
		if (this.en != null) {
			if (this.en.equals(en)) {
				this.en = en;
			} else {
				this.prox.atualizar(en);
			}
		} else {
			throw new ComponenteNaoEncontradoException();
		}
	}

	public boolean existe(IEnlace en) {
		boolean resposta = false;
		if (this.en != null) {
			if (this.en.equals(en)) {
				resposta = true;
			} else {
				resposta = this.prox.existe(en);
			}
		} else {
			resposta = false;
		}
		return resposta;
	}
	public String getEnlaces(RepositorioEnlacesLista repen){
		String enlaces="";
		while(repen.en!=null){
			enlaces+=repen.en.getNo1()+" "+repen.en.getNo2()+"\r\n";
			repen=repen.prox;
		}
		return enlaces;
	}
	public void MatrizNos(RepositorioEnlacesLista repen,double[][]Dados){
		int i =0;
		while(repen.en!=null){
			Dados[i][0]=repen.en.getintNo1();
			Dados[i][1]=repen.en.getintNo2();
			repen=repen.prox;
			i++;
		}
	}
	public int getTam(RepositorioEnlacesLista repen){
		int enlaces=0;
		while(repen.en!=null){
			enlaces++;
			repen=repen.prox;
		}
		return enlaces;
	}
	public void MatrizConstantes(RepositorioEnlacesLista repen,double[][]Dados){
		int i =0;
		while(repen.en!=null){
			double resistencia=0;
			double ftensao=0;
			try {
				IComponente ic=Programa.rep.procurar(repen.en.getNo1(),repen.en.getNo2());
				resistencia=ic.getResistenscia();
				ftensao=ic.getFEM();
			} catch (ComponenteNaoEncontradoException e) {
				try {
					IComponente ic2=Programa.rep.procurar(repen.en.getNo2(),repen.en.getNo1());
					resistencia=-ic2.getResistenscia();
					ftensao=-ic2.getFEM();
				} catch (ComponenteNaoEncontradoException e1) {
					
				}
			}
			Dados[i][2]=resistencia;
			Dados[i][3]=ftensao;
			repen=repen.prox;
			i++;
		}
	}
}
