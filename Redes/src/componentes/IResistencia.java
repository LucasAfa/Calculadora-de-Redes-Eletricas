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

public class IResistencia extends IComponente {
	/**
	 * 
	 */
	private static final long serialVersionUID = -435172861320833801L;
	private static final Image Imagem = Toolkit.getDefaultToolkit().getImage(Programa.class.getResource("/Resistor.png"));

	public IResistencia(int x, int y) {
		super(x, y, 100, 32);
		No1 = new INo(x - 50, y+1);
		No2 = new INo(x + 50, y+1);
		enlace = new IEnlace(No1, No2);
		this.resistenscia=5;
		Programa.rep.inserir(this);
	}

	public void mover(int x, int y) {
		super.mover(x, y);
		No1.mover(x - 50, y+1);
		No2.mover(x + 50, y+1);
	}

	public void desenhar(Graphics G) {
		G.drawImage(Imagem, 0, 0, this);
		if (Selecionado) {
			G.setColor(Color.red);
			G.drawRect(0, 0, 99, 31);
		}
	}

	public String toString() {
		return String.valueOf(valor) + " omn";
	}

	@Override
	public void mostrarPopUp() {
		ResistorGeradorpop.mostrar(this);

	}

}
