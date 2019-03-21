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

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import componentes.IGerador;
import programa.Programa;

public class CGerador extends Criador {
	public CGerador() {
		Programa.Plano.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	public void mouseReleased(MouseEvent ME) {
		IGerador IF = new IGerador(ME.getX(), ME.getY());
		Programa.Plano.add(IF);
		Programa.Plano.add(IF.getNo1());
		Programa.Plano.add(IF.getNo2());
		Programa.Plano.repaint();
		Programa.Plano.setCursor(Cursor.getDefaultCursor());
		Programa.Plano.removeMouseListener(this);
		Programa.criador = null;
	}
}
