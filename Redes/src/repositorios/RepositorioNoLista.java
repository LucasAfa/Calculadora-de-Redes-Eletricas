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

import componentes.INo;

public class RepositorioNoLista implements RepositorioNo {
	public INo en;
	public RepositorioNoLista prox;

	public RepositorioNoLista() {
		this.en = null;
		this.prox = null;
	}

	public void inserir(INo en) {
		if (this.en == null) {
			this.en = en;
			this.prox = new RepositorioNoLista();
		} else {
			this.prox.inserir(en);
		}
	}

	public INo procurar(INo en) throws ComponenteNaoEncontradoException {
		INo resposta = null;
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

	public void remover(INo en) throws ComponenteNaoEncontradoException {
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

	public void atualizar(INo en) throws ComponenteNaoEncontradoException {
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

	public boolean existe(INo en) {
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

}
