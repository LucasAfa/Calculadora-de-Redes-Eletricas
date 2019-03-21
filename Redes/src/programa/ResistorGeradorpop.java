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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import componentes.IComponente;
import componentes.IGerador;
import componentes.IResistencia;

import javax.swing.SwingConstants;

public class ResistorGeradorpop {
	public static JDialog PopUp;
	private final JPanel contentPanel = new JPanel();
	private static IComponente RAtual;
	private static JTextField Const;
	private static JTextField Numero;
	private static JTextField corrente;
	private static JTextField tensao;
	public ResistorGeradorpop() {
		PopUp = new JDialog(Programa.Janela, "Componente", true);
		PopUp.setBounds(100, 100, 311, 206);
		PopUp.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		PopUp.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("Constante");
			label.setBounds(30, 50, 64, 16);
			contentPanel.add(label);
		}
		{
			Const = new JTextField(10);
			Const.setBounds(106, 45, 130, 26);
			contentPanel.add(Const);
		}
		{
			Numero = new JTextField();
			Numero.setEditable(false);
			Numero.setBounds(6, 6, 49, 26);
			Numero.setColumns(10);
			contentPanel.add(Numero);
		}
		{
		corrente = new JTextField();
		corrente.setEditable(false);
		corrente.setBounds(22, 113, 130, 26);
		contentPanel.add(corrente);
		corrente.setColumns(10);
		}
		{
		tensao = new JTextField();
		tensao.setEditable(false);
		tensao.setBounds(164, 113, 130, 26);
		contentPanel.add(tensao);
		tensao.setColumns(10);
		}
		{
		JLabel lblCorrente = new JLabel("Corrente");
		lblCorrente.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrente.setBounds(51, 95, 64, 16);
		contentPanel.add(lblCorrente);
		}
		{
		JLabel lblTensao = new JLabel("Tens\u00e3o");
		lblTensao.setHorizontalAlignment(SwingConstants.CENTER);
		lblTensao.setBounds(191, 95, 64, 16);
		contentPanel.add(lblTensao);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			PopUp.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(Double.parseDouble(Const.getText())!=0.0){
						if (RAtual instanceof IResistencia) {
							RAtual.setFEM(0);
							RAtual.setResistenscia(Double.parseDouble(Const.getText()));	
						}
						else if (RAtual instanceof IGerador) {
							RAtual.setFEM(Double.parseDouble(Const.getText()));
							RAtual.setResistenscia(0);	
						}
						PopUp.dispose();
						}
						else{
							JOptionPane.showMessageDialog(contentPanel, "A constante n\u00e3o pode ser zero!","Erro",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				PopUp.getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PopUp.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

			JButton Remover = new JButton("Remover");
			Remover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RAtual.apagar();
					PopUp.dispose();
				}
			});
			buttonPane.add(Remover);
		}
	}

	public static void mostrar(IComponente IR) {
		RAtual = IR;
		if (RAtual instanceof IResistencia) {
			Const.setText(Double.toString(RAtual.getResistenscia()));	
		}
		else if (RAtual instanceof IGerador) {
			Const.setText(Double.toString(RAtual.getFEM()));
		}
		Numero.setText(Integer.toString(RAtual.getNumero()));
		corrente.setText(Double.toString(RAtual.getCorrente()));
		tensao.setText(Double.toString(RAtual.getTensao()));
		PopUp.setVisible(true);
	}
}
