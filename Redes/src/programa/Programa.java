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
package programa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import criadores.CGerador;
import criadores.CResistencia;
import criadores.Criador;
import repositorios.ComponenteNaoEncontradoException;
import repositorios.RepositorioComponentes;
import repositorios.RepositorioComponentesLista;
import repositorios.RepositorioEnlacesLista;
import repositorios.RepositorioNo;
import repositorios.RepositorioNoLista;

public class Programa {

	public static final JFrame Janela = new JFrame("Circuitos");
	public static JPanel Plano = new JPanel(null);
	public static Criador criador;
	public static RepositorioComponentes rep = new RepositorioComponentesLista();
	public static RepositorioEnlacesLista repe = new RepositorioEnlacesLista();
	public static RepositorioNo repno = new RepositorioNoLista();
	private static final double epsilon = 0.00001;
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Janela.setBounds(100, 100, 450, 300);
		Janela.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 104, 278);
		panel.setLayout(null);
		JButton Resistor = new JButton("Resistor");
		Resistor.setForeground(Color.BLACK);
		Resistor.setBounds(6, 95, 93, 64);
		panel.add(Resistor);
		JButton Gerador = new JButton("Gerador");
		Gerador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criador = new CGerador() {
				};
			}
		});
		Gerador.setBounds(6, 19, 93, 64);
		panel.add(Gerador);
		Gerador.setHorizontalAlignment(SwingConstants.LEFT);
		Resistor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent AE) {
				criador = new CResistencia();
			}
		});
		JButton Atualizar = new JButton("Atualizar");
		Atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double Dados[][] = this.gerarMatriz();
				for (int i = 0; i < Dados.length; i++) {
					System.out.print(Dados[i][0] + " ");
					System.out.print(Dados[i][1] + " ");
					System.out.print(Dados[i][2] + " ");
					System.out.println(Dados[i][3]);
				}
				System.out.println("");
				this.gerarMatrizCir(Dados);
				for (int i = 0; i < Dados.length; i++) {
					System.out.print(Dados[i][0] + " ");
					System.out.print(Dados[i][1] + " ");
					System.out.print(Dados[i][2] + " ");
					System.out.println(Dados[i][3]);
				}
				System.out.println("");
				this.getNo(Dados);
				for (int i = 0; i < Dados.length; i++) {
					System.out.print(Dados[i][0] + " ");
					System.out.print(Dados[i][1] + " ");
					System.out.print(Dados[i][2] + " ");
					System.out.println(Dados[i][3]);
				}
				System.out.println("");
				double Limpa[][] = limparMatriz(Dados);
				for (int i = 0; i < Limpa.length; i++) {
					System.out.print(Limpa[i][0] + " ");
					System.out.print(Limpa[i][1] + " ");
					System.out.print(Limpa[i][2] + " ");
					System.out.println(Limpa[i][3]);
				}
				System.out.println("");
				String[] Comps = this.getComponentes(Limpa);
				for (int i = 0; i < Comps.length; i++) {
					System.out.println(Comps[i]);
				}
				System.out.println("");
				int n = Limpa.length;
				double voltages[] = new double[n];
				double correntes[] = new double[n];
				double powers[] = new double[n];
				this.solveCircuit(Limpa, n, voltages, correntes, powers);
				for (int i = 0; i < n; i++) {
					try {
						rep.procurar(i).setTensao(voltages[i]);
						rep.procurar(i).setCorrente(correntes[i]);
					} catch (ComponenteNaoEncontradoException e1) {
						e1.printStackTrace();
					}
					System.out.printf("%d: Vol:%.3fV, Cur:%.3fA, Pwr:%.3fW\n", i, voltages[i], correntes[i], powers[i]);
				}
			}

			private String[] getComponentes(double[][] Circuito) {
				String[] Componentes = new String[Circuito.length + 1];
				Componentes[0] = Integer.toString(Circuito.length);
				for (int i = 1; i < Componentes.length; i++) {
					String Comp = this.makeComp(Circuito[i - 1]);
					Componentes[i] = Comp;
				}
				return Componentes;
			}

			private String makeComp(double[] Circuito) {
				String Comp = "";
				if (Circuito[2] > 0) {
					Comp = Integer.toString((int) Circuito[2]) + " " + "R" + " " + Integer.toString((int) Circuito[0])
							+ " " + Integer.toString((int) Circuito[1]);
				}
				if (Circuito[3] > 0) {
					Comp = Integer.toString((int) Circuito[3]) + " " + "G" + " " + Integer.toString((int) Circuito[0])
							+ " " + Integer.toString((int) Circuito[1]);
				}
				return Comp;
			}

			private double[][] limparMatriz(double[][] Dados) {
				for (int i = 0; i < Dados.length; i++) {
					if (Dados[i][2] == 0.0 && Dados[i][3] == 0.0) {
						return limparMatriz(removerow(Dados, i));
					}
				}
				return Dados;
			}

			public double[][] removerow(double mat[][], int ren) {
				int rengre = ren;
				double mat2[][] = new double[mat.length - 1][mat[0].length];
				int p = 0;
				for (int i = 0; i < mat.length; ++i) {
					if (i == rengre)
						continue;
					int q = 0;
					for (int j = 0; j < mat[0].length; ++j) {
						mat2[p][q] = mat[i][j];
						++q;
					}
					++p;
				}
				return mat2;
			}

			private void getNo(double[][] Dados) {
				double nos[] = new double[Dados.length * 2];
				for (int i = 0, num = 0; i < Dados.length * 2; i += 2, num++) {
					nos[i] = Dados[num][0];
					nos[i + 1] = Dados[num][1];
				}
				double[] unicos = new double[nos.length];
				int qtd = 0;
				for (int i = 0; i < nos.length; i++) {
					boolean existe = false;
					for (int j = 0; j < qtd; j++) {
						if (unicos[j] == nos[i]) {
							existe = true;
							break;
						}
					}
					if (!existe) {
						unicos[qtd++] = nos[i];
					}
				}
				unicos = Arrays.copyOf(unicos, qtd);
				double subNo[][] = new double[unicos.length][2];
				for (int i = 0; i < unicos.length; i++) {
					subNo[i][0] = unicos[i];
					subNo[i][1] = i;
				}
				for (int i = 0; i < subNo.length; i++) {
					this.setNo(Dados, subNo[i][0], subNo[i][1]);
				}
			}

			private void gerarMatrizCir(double[][] Dados) {
				for (int i = 0; i < Dados.length; i++) {
					if (Dados[i][2] == 0.0 && Dados[i][3] == 0.0) {
						setNo(Dados, Dados[i][0], Dados[i][1]);
					}
				}
			}

			private void setNo(double[][] Dados, double nore, double nonovo) {
				for (int i = 0; i < Dados.length; i++) {
					if (Dados[i][0] == nore) {
						Dados[i][0] = nonovo;
					}
					if (Dados[i][1] == nore) {
						Dados[i][1] = nonovo;
					}
				}
			}

			private double[][] gerarMatriz() {
				double Dados[][] = new double[repe.getTam(repe)][4];
				repe.MatrizNos(repe, Dados);
				repe.MatrizConstantes(repe, Dados);
				return Dados;
			}

			private void solveCircuit(double[][] circuit, int n, double voltages[], double currents[],
					double powers[]) {
				int nodes = numNos(circuit, n) + 1;
				int vSources = 0;
				for (int i = 0; i < n; i++) {
					if (circuit[i][3] != 0) {
						vSources++;
					}
				}
				int dimension = nodes + vSources + 1;
				double matrix[][] = new double[dimension][dimension];
				setCircuitEquations(circuit, n, dimension, matrix, nodes);
				for (int l = 0; l < dimension; l++) {
					for (int c = 0; c < dimension; c++) {
						System.out.print(matrix[l][c] + " ");
					}
					System.out.println("");
				}
				System.out.println("");
				this.rref(matrix);
				for (int l = 0; l < dimension; l++) {
					for (int c = 0; c < dimension; c++) {
						System.out.print(matrix[l][c] + " ");
					}
					System.out.println("");
				}
				for (int i = 0; i < n; i++) {
					int beg = (int) (circuit[i][0] + 0.5d);
					int end = (int) (circuit[i][1] + 0.5d);
					if (circuit[i][2] > 0) {
						voltages[i] = matrix[beg][dimension - 1] - matrix[end][dimension - 1];
					} else if (circuit[i][3] > 0) {
						voltages[i] = circuit[i][3];
					} else
						voltages[i] = matrix[end][dimension - 1] - matrix[beg][dimension - 1];
				}

				int vSourceIndex = 0;
				for (int i = 0; i < n; i++) {
					int beg = (int) (circuit[i][0] + 0.5d);
					int end = (int) (circuit[i][1] + 0.5d);
					if (circuit[i][2] > 0) {
						currents[i] = (matrix[beg][dimension - 1] - matrix[end][dimension - 1]) / circuit[i][2];
					} else if (circuit[i][3] > 0) {
						currents[i] = matrix[nodes + vSourceIndex][dimension - 1];
						vSourceIndex++;
					} else
						currents[i] = circuit[i][3];
				}

				for (int i = 0; i < n; i++)
					powers[i] = voltages[i] * currents[i];
			}

			private void setCircuitEquations(double[][] circuit, int n, int dimension, double matrix[][], int nodes) {
				int vSourceIndex = 0;
				for (int i = 0; i < n; i++) {
					int beg = (int) (circuit[i][0] + 0.5d);
					int end = (int) (circuit[i][1] + 0.5d);
					System.out.print(beg + " ");
					System.out.println(end);
					if (circuit[i][2] > 0) {
						double value = circuit[i][2];
						matrix[beg][beg] += 1.0 / value;
						matrix[beg][end] += -(1 / value);
						matrix[end][end] += 1.0 / value;
						matrix[end][beg] += -(1.0 / value);
					} else if (circuit[i][3] > 0) {
						double value = circuit[i][3];
						matrix[beg][nodes + vSourceIndex] += 1.0;
						matrix[end][nodes + vSourceIndex] += -1.0;
						matrix[nodes + vSourceIndex][end] = 1.0;
						matrix[nodes + vSourceIndex][beg] = -1.0;
						matrix[nodes + vSourceIndex][dimension - 1] = value;
						vSourceIndex++;
					}
				}
				matrix[dimension - 1][0] = 1.0;
			}

			private int numNos(double[][] circuit, int n) {
				int nodes = 0;
				for (int i = 0; i < n; i++) {
					if (circuit[i][0] > nodes)
						nodes = (int) circuit[i][0];
					if (circuit[i][1] > nodes)
						nodes = (int) circuit[i][1];
				}
				return nodes++;
			}

			private void rref(double[][] matrix) {
				int pivotRow = 0;
				int cols=matrix[0].length;
				int rows=matrix.length;
			    for (int c = 0; c < cols; c++) {
			        int r = pivotRow;
			        while (r < rows && Math.abs(matrix[r][c]) < epsilon)
			            r++;
			        if (r == rows) continue;
			        swapRows(matrix, pivotRow, r);
			        multiplyRow(matrix, pivotRow, 1.0 / matrix[pivotRow][c]);
			        for (r = 0; r < rows; r++) {
			            if (r == pivotRow) continue;
			            subtractRows(matrix, pivotRow, r, matrix[r][c]);
			        }
			        pivotRow++;
			    }

			}
			private void swapRows(double[][] m, int row1, int row2) {
				double[] swap = new double[m[0].length];

				for (int c1 = 0; c1 < m[0].length; c1++)
					swap[c1] = m[row1][c1];

				for (int c1 = 0; c1 < m[0].length; c1++) {
					m[row1][c1] = m[row2][c1];
					m[row2][c1] = swap[c1];
				}
			}

			private void multiplyRow(double[][] m, int row, double scalar) {
				for (int c1 = 0; c1 < m[0].length; c1++)
					m[row][c1] *= scalar;
			}

			private void subtractRows(double[][] m,  int subtract_scalar_times_this_row,
					int from_this_row,double scalar) {
				for (int c1 = 0; c1 < m[0].length; c1++)
					m[from_this_row][c1] -= scalar * m[subtract_scalar_times_this_row][c1];
			}

		});
		Atualizar.setBounds(6, 202, 95, 62);
		panel.add(Atualizar);
		Plano.setBounds(116, 0, 334, 272);
		Plano.setBackground(Color.LIGHT_GRAY);

		JSplitPane JSP = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(panel), new JScrollPane(Plano));

		JSP.setResizeWeight(0.25);
		JSP.setOneTouchExpandable(true);
		JSP.setDividerLocation(0.50);
		Janela.getContentPane().add(JSP, BorderLayout.CENTER);
		ResistorGeradorpop gera = new ResistorGeradorpop();
		Janela.setVisible(true);
	}
}
