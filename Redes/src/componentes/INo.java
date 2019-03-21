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
package componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Vector;

import criadores.CEnlace;
import programa.Programa;
import repositorios.ComponenteNaoEncontradoException;

public class INo extends Imagem {
	/**
	 * 
	 */
	private int Numero;
	private static int cont=0;
	private static final long serialVersionUID = 1809179180731120976L;
	private Vector<Imagem> enlaces = new Vector<Imagem>();

	public INo(int x, int y) {
		super(x, y, 10, 10);
		this.Numero=cont;
		cont++;
		Programa.repno.inserir(this);
	}

	public void adicionarEnlace(Imagem enlace) {
		enlaces.add(enlace);
	}

	public void removerEnlace(Imagem enlace) {
		enlaces.remove(enlace);
	}

	public void mover(int x, int y) {
		super.mover(x, y);
		for (int i = 0; i < enlaces.size(); i++)
			if (enlaces.get(i) instanceof IEnlace)
				((IEnlace) enlaces.get(i)).atualizar();
	}

	public void desenhar(Graphics G) {
		if (!Selecionado)
			G.setColor(Color.red);
		else
			G.setColor(Color.blue);
		G.fillOval(0, 0, 10, 10);
	}

	public void apagar() {
		super.apagar();
		try {
			Programa.repno.remover(this);
		} catch (ComponenteNaoEncontradoException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < enlaces.size(); i++)
			if (enlaces.get(i) instanceof IEnlace)
				((IEnlace) enlaces.get(i)).apagar();
	}

	public void mouseClicked(MouseEvent ME) {
		if (Programa.criador instanceof CEnlace) {
			((CEnlace) Programa.criador).enlacar(this);
			return;
		}
		if (Programa.criador != null)
			return;
		Programa.criador = new CEnlace(this);
	}
	public int getNum(){
		return this.Numero;
	}
}
