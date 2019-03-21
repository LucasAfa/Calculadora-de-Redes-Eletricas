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
package criadores;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import componentes.IEnlace;
import componentes.INo;
import programa.Programa;

public class CEnlace extends Criador implements MouseMotionListener {
	private INo no1;
	private int dx, dy;
	private JPanel preEnlace;

	public CEnlace(final INo no1) {
		this.no1 = no1;
		Programa.Plano.setCursor(new Cursor(Cursor.HAND_CURSOR));
		preEnlace = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics G) {
				super.paintComponent(G);
				G.setColor(Color.blue);
				G.drawLine(no1.centro()[0], no1.centro()[1], dx, dy);
			}
		};
		preEnlace.setOpaque(false);
		preEnlace.setSize(Programa.Plano.getWidth(), Programa.Plano.getHeight());
		preEnlace.setLocation(0, 0);
		Programa.Plano.add(preEnlace, -1);
		Programa.Plano.addMouseMotionListener(this);
	}

	public void enlacar(INo no2) {
		IEnlace enlace = new IEnlace(no1, no2);
		Programa.Plano.add(enlace);
		no1.adicionarEnlace(enlace);
		no1.adicionarEnlace(enlace);
		no2.adicionarEnlace(enlace);
		no2.adicionarEnlace(enlace);
		remover();
	}

	public void mouseClicked(MouseEvent ME) {
		remover();
	}

	public void mouseMoved(MouseEvent ME) {
		dx = ME.getX();
		dy = ME.getY();
		preEnlace.repaint();
	}

	public void mouseDragged(MouseEvent ME) {
	}

	private void remover() {
		Programa.Plano.setCursor(Cursor.getDefaultCursor());
		Programa.Plano.remove(preEnlace);
		Programa.Plano.removeMouseListener(this);
		Programa.Plano.removeMouseMotionListener(this);
		Programa.criador = null;
		Programa.Plano.repaint();
	}
}
