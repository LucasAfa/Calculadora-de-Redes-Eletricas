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

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import programa.Programa;
import repositorios.ComponenteNaoEncontradoException;

public abstract class IComponente extends Imagem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected INo No1, No2;
	protected IEnlace enlace;
	private int dx, dy;
	public double valor;
	protected double resistenscia;
	protected double FEM;
	protected double tensao;
	protected double corrente;
	protected static int total=0;
	protected int numero;

	public abstract void mostrarPopUp();

	public IComponente(int x, int y, int largura, int altura) {
		super(x, y, largura, altura);
		dx = x;
		dy = y;
		this.numero=total;
		total++;
	}

	public INo getNo1() {
		return No1;
	}

	public INo getNo2() {
		return No2;
	}

	public void apagar() {
		super.apagar();
		No1.apagar();
		No2.apagar();
		try {
			Programa.rep.remover(this.getNumero());
		} catch (ComponenteNaoEncontradoException e) {
			e.printStackTrace();
		}
		total--;
		Programa.Plano.repaint();
	}

	public void keyReleased(KeyEvent KE) {
		if (KE.getKeyCode() == KeyEvent.VK_DELETE)
			apagar();
	}

	public void mouseClicked(MouseEvent ME) {
		if (ME.getClickCount() == 2) {
			mostrarPopUp();
		}
	}

	public void mousePressed(MouseEvent ME) {
		dx = ME.getX();
		dy = ME.getY();
	}

	public void mouseDragged(MouseEvent ME) {
		int nx = x + ME.getX() - dx;
		int ny = y + ME.getY() - dy;
		if (nx < (int) (largura / 2))
			nx = (int) (largura / 2);
		else if (nx + (int) (largura / 2) > getParent().getWidth())
			nx = getParent().getWidth() - (int) (largura / 2);
		if (ny < (int) (altura / 2))
			ny = (int) (altura / 2);
		else if (ny + (int) (altura / 2) > getParent().getHeight())
			ny = getParent().getHeight() - (int) (altura / 2);
		mover(nx, ny);
	}
	public double getCorrente() {
		return corrente;
	}

	public void setCorrente(double corrente) {
		this.corrente = corrente;
		try {
			Programa.rep.atualizar(this);
		} catch (ComponenteNaoEncontradoException e) {
			e.printStackTrace();
		}
	}

	public double getResistenscia() {
		return resistenscia;
	}

	public void setResistenscia(double resistenscia) {
		this.resistenscia = resistenscia;
		try {
			Programa.rep.atualizar(this);
		} catch (ComponenteNaoEncontradoException e) {
			e.printStackTrace();
		}
	}

	public double getFEM() {
		return FEM;
	}

	public void setFEM(double fem) {
		this.FEM = fem;
		try {
			Programa.rep.atualizar(this);
		} catch (ComponenteNaoEncontradoException e) {
			e.printStackTrace();
		}
	}

	public void setNo1(INo no1) {
		this.No1 = no1;
	}

	public void setNo2(INo no2) {
		this.No2 = no2;
	}

	public int getNumero() {
		return numero;
	}

	public double getTensao() {
		return tensao;
	}

	public void setTensao(double tensao) {
		this.tensao = tensao;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void renumber() {
		this.numero=this.numero-1;
	}

}
