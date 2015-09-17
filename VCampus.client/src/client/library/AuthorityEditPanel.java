package client.library;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xpath.internal.operations.Number;

import client.util.ClientMsgHelper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 提供权限设置的panel,用于设置最大借书数和借书天数
 * @author 陈石开
 * 
 *
 */
public class AuthorityEditPanel extends JFrame {

	private JPanel contentPane;
	private JTextField maxDaysText;
	private JTextField maxBookNumText;
	private int currentMaxDays;
	private int currentMaxNum;
	private JPanel panel;

	public AuthorityEditPanel(JPanel parentPanel) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 922, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 900, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(658, 73, 232, 467);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton submit = new JButton("保存");
		submit.setBounds(69, 140, 93, 23);
		panel_1.add(submit);
		
		JButton btnNewButton = new JButton("返回");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parentPanel.getComponent(0).setVisible(true);
				parentPanel.remove(getPanel());
			}
		});
		btnNewButton.setBounds(69, 303, 93, 23);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 73, 638, 467);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		maxBookNumText = new JTextField();
		maxBookNumText.setBounds(308, 305, 134, 21);
		panel_2.add(maxBookNumText);
		maxBookNumText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("借书数量上限");
		lblNewLabel_1.setBounds(136, 308, 92, 15);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("借书天数上限");
		lblNewLabel.setBounds(136, 145, 92, 15);
		panel_2.add(lblNewLabel);
		
		maxDaysText = new JTextField();
		maxDaysText.setBounds(308, 141, 134, 23);
		panel_2.add(maxDaysText);
		maxDaysText.setColumns(10);
		
		JLabel label = new JLabel("权限设置");
		label.setBounds(402, 10, 92, 53);
		panel.add(label);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 23));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editAuthorityAction()){
					JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "权限修改成功!", "权限设置", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "权限修改失败,未知错误!", "权限设置", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		updateCurrentAuthority();
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private void updateCurrentAuthority() {
		ClientMsgHelper cmh = new ClientMsgHelper();
		int[] numbers = {0,0};
		cmh.updateCurrentAuthority();	
		cmh.sendMsg();
		cmh.recieveMsg();
		numbers = (int[]) cmh.getDataInMsg();
		currentMaxDays = numbers[0];
		currentMaxNum = numbers[1];
		maxBookNumText.setText(currentMaxNum+"");
		maxDaysText.setText(currentMaxDays+"");
	}

	public static boolean isNumeric(String str){ //是否为纯数字
	 	if(str.matches("\\d*")){
	 		return true; 
	 	}
	 	else{
	 		return false;
	 	}
	 }
	
	
	protected boolean editAuthorityAction() {
		ClientMsgHelper cmh = new ClientMsgHelper();
		if(maxDaysText.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入借书天数上限!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(maxBookNumText.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "请输入借书数量上限!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(isNumeric(maxDaysText.getText()) != true){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书天数上限应为整数!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(isNumeric(maxBookNumText.getText()) != true){
			JOptionPane.showMessageDialog(contentPane.getParent().getParent(), "借书数量上限应为整数!", "输入错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		int maxDay = Integer.parseInt(maxDaysText.getText());
		int maxBookNum = Integer.parseInt(maxBookNumText.getText());
		cmh.updateAuthority(maxDay,maxBookNum);
		cmh.sendMsg();
		cmh.recieveMsg();
		return cmh.getState();
		
	}
}
