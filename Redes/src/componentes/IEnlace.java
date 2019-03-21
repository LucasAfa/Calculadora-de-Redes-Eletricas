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

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import programa.Programa;
import repositorios.ComponenteNaoEncontradoException;

public class IEnlace extends Imagem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5695198765704097989L;
	private INo no1, no2;

	public IEnlace(INo no1, INo no2) {
		super(0, 0, 0, 0);
		this.no1 = no1;
		this.no2 = no2;
		atualizar();
		Programa.repe.inserir(this);
	}

	public void atualizar() {
		if (no1 == null || no2 == null)
			return;
		x = Math.min(no1.x, no2.x);
		y = Math.min(no1.y, no2.y);
		largura = Math.max(no1.x, no2.x) - x;
		altura = Math.max(no1.y, no2.y) - y;
		if (largura == 0)
			largura = 1;
		if (altura == 0)
			altura = 1;
		setSize(largura, altura);
		setLocation(x, y);
		repaint();
	}

	public void desenhar(Graphics G) {
		Graphics2D g2 = (Graphics2D) G;
	    g2.setStroke(new BasicStroke(3));
		if (no1 == null || no2 == null)
			return;
		if (no1.x <= no2.x) {
			if (no1.y <= no2.y) {
				g2.drawLine(0, 0, largura, altura);
			} else {
				g2.drawLine(0, altura, largura, 0);
			}
		} else {
			if (no1.y <= no2.y){
				g2.drawLine(largura, 0, 0, altura);
			}
			else{
				g2.drawLine(largura, altura, 0, 0);
			}
		}
	}

	public void mover(int x, int y) {
	}

	public void apagar() {
		super.apagar();
		try {
			Programa.repe.remover(this);
		} catch (ComponenteNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		no1.removerEnlace(this);
		no2.removerEnlace(this);
		no1 = null;
		no2 = null;
	}

	public int getintNo1() {
		return no1.getNum();
	}

	public int getintNo2() {
		return no2.getNum();
	}

	public INo getNo1() {
		return no1;
	}

	public INo getNo2() {
		return no2;
	}
}
