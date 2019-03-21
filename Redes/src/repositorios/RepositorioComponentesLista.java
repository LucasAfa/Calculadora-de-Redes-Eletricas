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
import componentes.INo;

public class RepositorioComponentesLista implements RepositorioComponentes {

	private IComponente comp;
	private RepositorioComponentesLista prox;

	public RepositorioComponentesLista() {
		this.comp = null;
		this.prox = null;
	}

	public void inserir(IComponente comp) {
		if (this.comp == null) {
			this.comp = comp;
			this.prox = new RepositorioComponentesLista();
		} else {
			this.prox.inserir(comp);
		}
	}

	public IComponente procurar(int numero) throws ComponenteNaoEncontradoException {
		IComponente resposta = null;
		if (this.comp != null) {
			if (this.comp.getNumero()==numero) {
				resposta = this.comp;
			} else {
				resposta = this.prox.procurar(numero);
			}
		} else {
			throw new ComponenteNaoEncontradoException();
		}
		return resposta;
	}

	public void remover(int numero) throws ComponenteNaoEncontradoException {
		if (this.comp != null) {
			if (this.comp.getNumero()==numero) {
				this.comp = this.prox.comp;
				this.prox = this.prox.prox;
				renumber(this);
			} else {
				this.prox.remover(numero);
			}
		} else {
			throw new ComponenteNaoEncontradoException();
		}
	}

	public void renumber(RepositorioComponentesLista rep){
		if (rep.comp != null) {
			rep.comp.renumber();
			renumber(rep.prox);
		}
	}

	public void atualizar(IComponente comp) throws ComponenteNaoEncontradoException {
		if (this.comp != null) {
			if (this.comp.equals(comp)) {
				this.comp = comp;
			} else {
				this.prox.atualizar(comp);
			}
		} else {
			throw new ComponenteNaoEncontradoException();
		}
	}

	public boolean existe(int numero) {
		boolean resposta = false;
		if (this.comp != null) {
			if (this.comp.getNumero()==numero) {
				resposta = true;
			} else {
				resposta = this.prox.existe(numero);
			}
		} else {
			resposta = false;
		}
		return resposta;
	}

	public IComponente procurar(INo No1, INo No2) throws ComponenteNaoEncontradoException {
		IComponente resposta = null;
		if (this.comp != null) {
			if (this.comp.getNo1().equals(No1)&&this.comp.getNo2().equals(No2)) {
				resposta = this.comp;
			} else {
				resposta = this.prox.procurar(No1,No2);
			}
		} else {
			throw new ComponenteNaoEncontradoException();
		}
		return resposta;
	}
	public IComponente procurar(INo No1) throws ComponenteNaoEncontradoException {
		IComponente resposta = null;
		if (this.comp != null) {
			if (this.comp.getNo1().equals(No1)) {
				resposta = this.comp;
			} else {
				resposta = this.prox.procurar(No1);
			}
		} else {
			throw new ComponenteNaoEncontradoException();
		}
		return resposta;
	}
}
