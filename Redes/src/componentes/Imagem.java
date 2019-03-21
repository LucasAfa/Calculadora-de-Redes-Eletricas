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

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import programa.Programa;

public abstract class Imagem extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4341140805834838352L;
	protected int x, y, largura, altura;
	protected boolean Selecionado;

	public Imagem(int x, int y, int largura, int altura) {
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setOpaque(false);
		setLocation(x - (int) (largura / 2), y - (int) (altura / 2));
		setSize(largura, altura);
		repaint();
	}

	public abstract void desenhar(Graphics G);

	public void setSelecionado(boolean valor) {
		Selecionado = valor;
		if (Selecionado)
			requestFocus();
		repaint();
	}

	public int[] centro() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}

	public void mover(int x, int y) {
		this.x = x;
		this.y = y;
		setLocation(x - (int) (largura / 2), y - (int) (altura / 2));
		repaint();
	}

	public void apagar() {
		Programa.Plano.remove(this);
	}

	public void mouseClicked(MouseEvent ME) {
	}

	public void mouseReleased(MouseEvent ME) {
	}

	public void mouseEntered(MouseEvent ME) {
		setSelecionado(true);
	}

	public void mouseExited(MouseEvent ME) {
		setSelecionado(false);
	}

	public void mouseMoved(MouseEvent ME) {
	}

	public void mousePressed(MouseEvent ME) {
	}

	public void mouseDragged(MouseEvent ME) {
	}

	public void keyPressed(KeyEvent KE) {
	}

	public void keyReleased(KeyEvent KE) {
	}

	public void keyTyped(KeyEvent KE) {
	}

	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		desenhar(G);
	}
}
