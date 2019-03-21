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
import java.awt.Image;
import java.awt.Toolkit;

import programa.Programa;
import programa.ResistorGeradorpop;

public class IGerador extends IComponente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 931227515473519521L;
	private static final Image Imagem = Toolkit.getDefaultToolkit().getImage(Programa.class.getResource("/Gerador.png"));;

	public IGerador(int x, int y) {
		super(x, y, 45, 45);
		this.No1 = new INo(x - 27, y);
		this.No2 = new INo(x + 27, y);
		enlace = new IEnlace(No1, No2);
		this.FEM=10;
		Programa.rep.inserir(this);
	}

	public void mover(int x, int y) {
		super.mover(x, y);
		No1.mover(x - 27, y);
		No2.mover(x + 27, y);
	}

	public void desenhar(Graphics G) {
		G.drawImage(Imagem, 0, 0, this);
		if (Selecionado) {
			G.setColor(Color.red);
			G.drawRect(0, 0, 44, 44);
		}
	}

	public void mostrarPopUp() {
		ResistorGeradorpop.mostrar(this);

	}
}
