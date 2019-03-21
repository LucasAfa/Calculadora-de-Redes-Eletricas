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

public interface RepositorioComponentes {
	void inserir(IComponente comp);

	IComponente procurar(int numero) throws ComponenteNaoEncontradoException;
	
	IComponente procurar(INo No1,INo No2) throws ComponenteNaoEncontradoException;
	
	IComponente procurar(INo No1) throws ComponenteNaoEncontradoException;

	void remover(int numero) throws ComponenteNaoEncontradoException;

	void atualizar(IComponente comp) throws ComponenteNaoEncontradoException;

	boolean existe(int numero);

}
